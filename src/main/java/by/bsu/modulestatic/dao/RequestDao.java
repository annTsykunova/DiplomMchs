package by.bsu.modulestatic.dao;

import by.bsu.modulestatic.dao.util.DBColumnName;
import by.bsu.modulestatic.dao.util.DaoException;
import by.bsu.modulestatic.entity.CallReason;
import by.bsu.modulestatic.entity.Calls;
import by.bsu.modulestatic.entity.StatisticCalls;
import by.bsu.modulestatic.entity.VechicleType;
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
public class RequestDao {

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

    public List<StatisticCalls> getStatisticsCalls(String startDate, String endDate) throws DaoException {
        Connection connection = null;
        ResultSet resultSet;
        List<StatisticCalls> callses = new ArrayList<>();
        StatisticCalls call;
        PreparedStatement preparedStatement;
        String query = sqlQueries.getProperty("get.count.calls.by.id.reason");
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,startDate);
            preparedStatement.setString(2,endDate);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                call = initStatisticCalls(resultSet);
                callses.add(call);
            }
            preparedStatement.close();
            return callses;
        } catch (SQLException ex) {
            String exceptionMessage = EXCEPTION_ACT_MESSAGE + "during getting by id ";
            throw new DaoException(exceptionMessage, ex);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    public List<StatisticCalls> getStatisticsCallsReasonsByReason(String startDate, String endDate, int reasonId) throws DaoException {
        Connection connection = null;
        ResultSet resultSet;
        List<StatisticCalls> callses = new ArrayList<>();
        StatisticCalls call;
        PreparedStatement preparedStatement;
        String query = sqlQueries.getProperty("get.count.calls.reason.by.id.reason");
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,startDate);
            preparedStatement.setString(2,endDate);
            preparedStatement.setInt(3,reasonId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                call = initStatisticCalls(resultSet);
                callses.add(call);
            }
            preparedStatement.close();
            return callses;
        } catch (SQLException ex) {
            String exceptionMessage = EXCEPTION_ACT_MESSAGE + "during getting by id ";
            throw new DaoException(exceptionMessage, ex);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    public List<StatisticCalls> getStatisticsCallsReasonsByVecclass(String startDate, String endDate, int vecclassId) throws DaoException {
        Connection connection = null;
        ResultSet resultSet;
        List<StatisticCalls> callses = new ArrayList<>();
        StatisticCalls call;
        PreparedStatement preparedStatement;
        String query = sqlQueries.getProperty("get.count.calls.reason.by.id.vecclass");
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,startDate);
            preparedStatement.setString(2,endDate);
            preparedStatement.setInt(3,vecclassId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                call = initStatisticCalls(resultSet);
                callses.add(call);
            }
            preparedStatement.close();
            return callses;
        } catch (SQLException ex) {
            String exceptionMessage = EXCEPTION_ACT_MESSAGE + "during getting by id ";
            throw new DaoException(exceptionMessage, ex);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }


    public List<StatisticCalls> getStatisticsReasonsByRegion(String startDate, String endDate, int reasonId) throws DaoException {
        Connection connection = null;
        ResultSet resultSet;
        List<StatisticCalls> callses = new ArrayList<>();
        StatisticCalls call;
        PreparedStatement preparedStatement;
        String query = sqlQueries.getProperty("get.count.calls.reason.by.id.region");
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,startDate);
            preparedStatement.setString(2,endDate);
            preparedStatement.setInt(3,reasonId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                call = initStatisticCalls(resultSet);
                callses.add(call);
            }
            preparedStatement.close();
            return callses;
        } catch (SQLException ex) {
            String exceptionMessage = EXCEPTION_ACT_MESSAGE + "during getting by id ";
            throw new DaoException(exceptionMessage, ex);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    public List<StatisticCalls> getStatisticsCallsRegionsByReason(String startDate, String endDate) throws DaoException {
        Connection connection = null;
        ResultSet resultSet;
        List<StatisticCalls> callses = new ArrayList<>();
        StatisticCalls call;
        PreparedStatement preparedStatement;
        String query = sqlQueries.getProperty("get.count.calls.region.by.id.reason");
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,startDate);
            preparedStatement.setString(2,endDate);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                call = initStatisticCalls(resultSet);
                callses.add(call);
            }
            preparedStatement.close();
            return callses;
        } catch (SQLException ex) {
            String exceptionMessage = EXCEPTION_ACT_MESSAGE + "during getting by id ";
            throw new DaoException(exceptionMessage, ex);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }


    public List<StatisticCalls> getStatisticsCallByRegion(String startDate, String endDate, int reasonId) throws DaoException {
        Connection connection = null;
        ResultSet resultSet;
        List<StatisticCalls> callses = new ArrayList<>();
        StatisticCalls call;
        PreparedStatement preparedStatement;
        String query = sqlQueries.getProperty("get.count.calls.by.id.region");
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,startDate);
            preparedStatement.setString(2,endDate);
            preparedStatement.setInt(3,reasonId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                call = initStatisticCalls(resultSet);
                callses.add(call);
            }
            preparedStatement.close();
            return callses;
        } catch (SQLException ex) {
            String exceptionMessage = EXCEPTION_ACT_MESSAGE + "during getting by id ";
            throw new DaoException(exceptionMessage, ex);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    protected Calls initCalls(ResultSet resultSet) throws SQLException {
        Calls call = new Calls();
        call.setCallsId(resultSet.getInt(DBColumnName.CALLS_ID));
        call.setDate(resultSet.getTimestamp(DBColumnName.DATE));
        call.setReasonId(resultSet.getInt(DBColumnName.REASON_ID));
        call.setVecclassId(resultSet.getInt(DBColumnName.VECCLASS_ID));
        call.setReasonId(resultSet.getInt(DBColumnName.REASON_ID));
        call.setCount(resultSet.getInt(DBColumnName.COUNT));
        return call;
    }

    private StatisticCalls initStatisticCalls(ResultSet resultSet) throws SQLException{
        StatisticCalls calls = new StatisticCalls();
        calls.setCallsId(resultSet.getInt(DBColumnName.CALLS_ID));
        calls.setDate(resultSet.getTimestamp(DBColumnName.DATE));
        calls.setRegionName(resultSet.getString(DBColumnName.REGION_NAME));
        calls.setReasonName(resultSet.getString(DBColumnName.NAME));
        calls.setVechicleName(resultSet.getString(DBColumnName.CLASS_NAME));
        calls.setCount(resultSet.getInt(DBColumnName.COUNT));
        return calls;
    }
}
