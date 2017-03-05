package by.bsu.modulestatic.dao;

import by.bsu.modulestatic.dao.util.DBColumnName;
import by.bsu.modulestatic.dao.util.DaoException;
import by.bsu.modulestatic.entity.User;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

    @Repository
    public class UserDao {
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

        public Long getUserIdByLogin(String userLogin) throws DaoException {
            Connection connection = null;
            PreparedStatement preparedStatement;
            ResultSet resultSet ;
            String query = sqlQueries.getProperty("users.get.id.by.login");
            try {
                connection = DataSourceUtils.getConnection(dataSource);
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, userLogin);
                resultSet = preparedStatement.executeQuery();
                Long userId = null;
                if (resultSet.next()) {
                    userId = resultSet.getLong(DBColumnName.USER_ID);
                }
                preparedStatement.close();
                return userId;
            } catch (SQLException ex) {
                String exceptionMessage = EXCEPTION_ACT_MESSAGE +
                        "during getting id of user by login " + userLogin;
                throw new DaoException(exceptionMessage, ex);
            } finally {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }

        public String getPasswordById(Long id) throws DaoException {
            Connection connection = null;
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            String query = sqlQueries.getProperty("users.get.password.by.id");
            try {
                connection = DataSourceUtils.getConnection(dataSource);
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setLong(1, id);
                resultSet = preparedStatement.executeQuery();
                String userPassword = null;
                if (resultSet.next()) {
                    userPassword = resultSet.getString(DBColumnName.PASSWORD);
                }
                preparedStatement.close();
                return userPassword;
            } catch (SQLException ex) {
                String exceptionMessage = EXCEPTION_ACT_MESSAGE +
                        "during getting password of user with id " + id;
                throw new DaoException(exceptionMessage, ex);
            } finally {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }

        public String getPasswordByLogin(String userLogin) throws DaoException {
            Connection connection = null;
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            String query = sqlQueries.getProperty("users.get.password.by.login");
            try {
                connection = DataSourceUtils.getConnection(dataSource);
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, userLogin);
                resultSet = preparedStatement.executeQuery();
                String password = null;
                if (resultSet.next()) {
                    password = resultSet.getString(DBColumnName.PASSWORD);
                }
                preparedStatement.close();
                return password;
            } catch (SQLException ex) {
                String exceptionMessage = EXCEPTION_ACT_MESSAGE +
                        "during getting password of user by login " + userLogin;
                throw new DaoException(exceptionMessage, ex);
            } finally {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }

        public void deleteById(Long id) throws DaoException {
            Connection connection = null;
            PreparedStatement preparedStatement;
            String query = sqlQueries.getProperty("users.delete.by.id");
            try {
                connection = DataSourceUtils.getConnection(dataSource);
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setLong(1, id.intValue());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException ex) {
                String exceptionMessage = EXCEPTION_ACT_MESSAGE + "during deleting by id";
                throw new DaoException(exceptionMessage, ex);
            } finally {

                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }


        public User getById(Long id) throws DaoException {
            Connection connection = null;
            ResultSet resultSet;
            User user = null;
            PreparedStatement preparedStatement;
            String query = sqlQueries.getProperty("users.get.by.id");
            try {
                connection = DataSourceUtils.getConnection(dataSource);
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setLong(1, id.intValue());
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    user = initUser(resultSet);
                }
                preparedStatement.close();
                return user;
            } catch (SQLException ex) {
                String exceptionMessage = EXCEPTION_ACT_MESSAGE + "during getting by id ";
                throw new DaoException(exceptionMessage, ex);
            } finally {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }

        protected User initUser(ResultSet resultSet) throws SQLException {
            User user = new User();
            user.setUserId(resultSet.getInt(DBColumnName.USER_ID));
            user.setName(resultSet.getString(DBColumnName.USERNAME));
            user.setSurname(resultSet.getString(DBColumnName.SURNAME));
            user.setLogin(resultSet.getString(DBColumnName.LOGIN));
            user.setPassword(resultSet.getString(DBColumnName.PASSWORD));
            user.setRole(resultSet.getString(DBColumnName.ROLE));

            return user;
        }
    }


