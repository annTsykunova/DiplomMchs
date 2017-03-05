package by.bsu.modulestatic.dao;

import by.bsu.modulestatic.dao.util.DaoException;
import by.bsu.modulestatic.entity.CallReason;
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
public class MainDao {
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

    public List<String> getAllTables() throws DaoException {
        Connection connection = null;
        ResultSet resultSet;
        List<String> tables = new ArrayList<>();
        String table = null;
        PreparedStatement preparedStatement;
        String query = sqlQueries.getProperty("get.all.tables");
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                table = resultSet.getString("Tables_in_statisctics");
                tables.add(table);
            }
            preparedStatement.close();
            return tables;
        } catch (SQLException ex) {
            String exceptionMessage = EXCEPTION_ACT_MESSAGE + "during getting by id ";
            throw new DaoException(exceptionMessage, ex);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }
}
