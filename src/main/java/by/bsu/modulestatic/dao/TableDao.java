package by.bsu.modulestatic.dao;

import by.bsu.modulestatic.dao.util.DBColumnName;
import by.bsu.modulestatic.dao.util.DaoException;
import by.bsu.modulestatic.entity.*;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Repository
public class TableDao {

    private BasicDataSource dataSource;
    private Properties sqlQueries;
    private static final String EXCEPTION_ACT_MESSAGE = "Sql actions exception";

    @Autowired
    public void setDataSource(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    @Qualifier("sqlQueries")
    public void setSqlQueries(Properties sqlQueries) {
        this.sqlQueries = sqlQueries;
    }

    public List<CallReason> getAllCallReason() throws DaoException {
        Connection connection = null;
        ResultSet resultSet;
        List<CallReason> callReasons = new ArrayList<>();
        CallReason callReason = null;
        PreparedStatement preparedStatement;
        String query = sqlQueries.getProperty("call_reason.get.all");
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                callReason = initCallReason(resultSet);
                callReasons.add(callReason);
            }
            preparedStatement.close();
            return callReasons;
        } catch (SQLException ex) {
            String exceptionMessage = EXCEPTION_ACT_MESSAGE + "during getting by id ";
            throw new DaoException(exceptionMessage, ex);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    protected CallReason initCallReason(ResultSet resultSet) throws SQLException {
        CallReason callReason = new CallReason();
        callReason.setReasonId(resultSet.getInt(DBColumnName.REASON_ID));
        callReason.setVersion(resultSet.getInt(DBColumnName.VERSION));
        callReason.setNameReason(resultSet.getString(DBColumnName.NAME));
        return callReason;
    }

        public List<DictionaryRegions> getAllRegions() throws DaoException {
        Connection connection = null;
        ResultSet resultSet;
        List<DictionaryRegions> dictionaryRegionses = new ArrayList<>();
        DictionaryRegions dictionaryRegions = null;
        PreparedStatement preparedStatement;
        String query = sqlQueries.getProperty("dictionary_regions.get.all");
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                dictionaryRegions = initRegions(resultSet);
                dictionaryRegionses.add(dictionaryRegions);
            }
            preparedStatement.close();
            return dictionaryRegionses;
        } catch (SQLException ex) {
            String exceptionMessage = EXCEPTION_ACT_MESSAGE + "during getting by id ";
            throw new DaoException(exceptionMessage, ex);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }
    protected DictionaryRegions initRegions(ResultSet resultSet) throws SQLException {
        DictionaryRegions dictionaryRegions = new DictionaryRegions();
        dictionaryRegions.setRegionId(resultSet.getInt(DBColumnName.REGION_ID));
        dictionaryRegions.setRegionCode(resultSet.getInt(DBColumnName.REGION_CODE));
        dictionaryRegions.setRegionName(resultSet.getString(DBColumnName.REGION_NAME));
        return dictionaryRegions;
    }

    public List<VechicleClass> getAllVechicleClass() throws DaoException {
        Connection connection = null;
        ResultSet resultSet;
        List<VechicleClass> vechicleClasses = new ArrayList<>();
        VechicleClass vechicleClass = null;
        PreparedStatement preparedStatement;
        String query = sqlQueries.getProperty("vechicle_class.get.all");
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                vechicleClass = initVechicleClass(resultSet);
                vechicleClasses.add(vechicleClass);
            }
            preparedStatement.close();
            return vechicleClasses;
        } catch (SQLException ex) {
            String exceptionMessage = EXCEPTION_ACT_MESSAGE + "during getting by id ";
            throw new DaoException(exceptionMessage, ex);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }
    protected VechicleClass initVechicleClass(ResultSet resultSet) throws SQLException {
        VechicleClass vechicleClass = new VechicleClass();
        vechicleClass.setClassId(resultSet.getInt(DBColumnName.CLASS_ID));
        vechicleClass.setName(resultSet.getString(DBColumnName.NAME));
        vechicleClass.setVersion(resultSet.getInt(DBColumnName.VERSION));
        vechicleClass.setDescription(resultSet.getString(DBColumnName.DESCRIPTION));
        vechicleClass.setVechicleId(resultSet.getInt(DBColumnName.VECHICLE_ID));
        return vechicleClass;
    }

    public List<VechicleType> getAllVechicleType() throws DaoException {
        Connection connection = null;
        ResultSet resultSet;
        List<VechicleType> vechicleTypes = new ArrayList<>();
        VechicleType vechicleType ;
        PreparedStatement preparedStatement;
        String query = sqlQueries.getProperty("vechicle_type.get.all");
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                vechicleType = initVechicleType(resultSet);
                vechicleTypes.add(vechicleType);
            }
            preparedStatement.close();
            return vechicleTypes;
        } catch (SQLException ex) {
            String exceptionMessage = EXCEPTION_ACT_MESSAGE + "during getting by id ";
            throw new DaoException(exceptionMessage, ex);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }
    protected VechicleType initVechicleType(ResultSet resultSet) throws SQLException {
        VechicleType vechicleType = new VechicleType();
        vechicleType.setVersionId(resultSet.getInt(DBColumnName.VECHICLE_ID));
        vechicleType.setVersion(resultSet.getInt(DBColumnName.VERSION));
        vechicleType.setNameType(resultSet.getString(DBColumnName.NAME));
        return vechicleType;
    }

    public void deleteItem(int id,String table) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        String query = null;
        if(table.equals("call_reason")){
            query = sqlQueries.getProperty("call_reason.delete.by.id");
        }else if(table.equals("dictionary_regions")){
            query = sqlQueries.getProperty("dictionary_regions.delete.by.id");
        }else if(table.equals("vechicle_class")){
            query = sqlQueries.getProperty("vechicle_class.delete.by.id");
        }else if(table.equals("vechicle_type")){
            query = sqlQueries.getProperty("vechicle_type.delete.by.id");
        }
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            String exceptionMessage = EXCEPTION_ACT_MESSAGE + "during deleting by id ";
            throw new DaoException(exceptionMessage, ex);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }
    public void updateCallReason(CallReason callReason) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        String query = sqlQueries.getProperty("call_reason.update");
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(2, callReason.getNameReason());
            preparedStatement.setInt(1, callReason.getVersion());
            preparedStatement.setInt(3, callReason.getReasonId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            String exceptionMessage = EXCEPTION_ACT_MESSAGE + "during updating comment";
            throw new DaoException(exceptionMessage, ex);
        } finally {

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    public void updateVechicleType(VechicleType vechicleType) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        String query = sqlQueries.getProperty("vechicle_type.update");
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(2, vechicleType.getNameType());
            preparedStatement.setInt(1, vechicleType.getVersion());
            preparedStatement.setInt(3, vechicleType.getVersionId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            String exceptionMessage = EXCEPTION_ACT_MESSAGE + "during updating comment";
            throw new DaoException(exceptionMessage, ex);
        } finally {

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    public void updateVechicleClass(VechicleClass vechicleClass) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        String query = sqlQueries.getProperty("vechicle_class.update");
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(2, vechicleClass.getName());
            preparedStatement.setInt(1, vechicleClass.getVersion());
            preparedStatement.setString(3, vechicleClass.getDescription());
            preparedStatement.setInt(4,vechicleClass.getVechicleId());
            preparedStatement.setInt(5,vechicleClass.getClassId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            String exceptionMessage = EXCEPTION_ACT_MESSAGE + "during updating comment";
            throw new DaoException(exceptionMessage, ex);
        } finally {

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    public void updateDictionaryRegions(DictionaryRegions dictionaryRegions) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        String query = sqlQueries.getProperty("dictionary_regions.update");
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(2, dictionaryRegions.getRegionName());
            preparedStatement.setInt(1, dictionaryRegions.getRegionCode());
            preparedStatement.setInt(3, dictionaryRegions.getRegionId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            String exceptionMessage = EXCEPTION_ACT_MESSAGE + "during updating comment";
            throw new DaoException(exceptionMessage, ex);
        } finally {

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    public void insertDictionary(DictionaryRegions dictionaryRegions) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        String query = sqlQueries.getProperty("dictionary_regions.insert");
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(2, dictionaryRegions.getRegionName());
            preparedStatement.setInt(1, dictionaryRegions.getRegionCode());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            String exceptionMessage = EXCEPTION_ACT_MESSAGE + "during updating comment";
            throw new DaoException(exceptionMessage, ex);
        } finally {

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    public void insertVechicleClass(VechicleClass vechicleClass) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        String query = sqlQueries.getProperty("vechicle_class.insert");
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(2, vechicleClass.getName());
            preparedStatement.setInt(1, vechicleClass.getVersion());
            preparedStatement.setString(3, vechicleClass.getDescription());
            preparedStatement.setInt(4,vechicleClass.getVechicleId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            String exceptionMessage = EXCEPTION_ACT_MESSAGE + "during updating comment";
            throw new DaoException(exceptionMessage, ex);
        } finally {

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    public void insertVechicleType(VechicleType vechicleType) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        String query = sqlQueries.getProperty("vechicle_type.insert");
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(2, vechicleType.getNameType());
            preparedStatement.setInt(1, vechicleType.getVersion());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            String exceptionMessage = EXCEPTION_ACT_MESSAGE + "during updating comment";
            throw new DaoException(exceptionMessage, ex);
        } finally {

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    public void insertCallReason(CallReason callReason) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        String query = sqlQueries.getProperty("call_reason.insert");
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(2, callReason.getNameReason());
            preparedStatement.setInt(1, callReason.getVersion());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            String exceptionMessage = EXCEPTION_ACT_MESSAGE + "during updating comment";
            throw new DaoException(exceptionMessage, ex);
        } finally {

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }
    public String getNameCallReasonById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        String reason = null;
        ResultSet resultSet;
        String query = sqlQueries.getProperty("call_reason.get.by.id");
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                reason = resultSet.getString("name");
            }
            preparedStatement.close();
        } catch (SQLException ex) {
            String exceptionMessage = EXCEPTION_ACT_MESSAGE + "during updating comment";
            throw new DaoException(exceptionMessage, ex);
        } finally {

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
        return reason;
    }
}
