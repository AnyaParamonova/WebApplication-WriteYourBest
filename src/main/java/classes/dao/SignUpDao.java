package classes.dao;

import classes.dao.connectors.IConnector;
import classes.dao.connectors.MySQLConnector;
import classes.dao.exceptions.DaoException;
import classes.model.user.RegisteredUser;
import classes.model.user.UnregisteredUser;
import java.sql.*;

/**
 * Created by Anastasia_Paramonova on 22.11.2016.
 */

public class SignUpDao {

    private static String INSERT_USER_QUERY = "INSERT INTO ACCOUNTS(NICKNAME, EMAIL, PASSWORD, TYPE, STATUS, CREATION_DATE) VALUES (?, ?, ?, \"regular\", \"basic\", current_timestamp())";
    private static String SELECT_USER_ID = "SELECT ID, NICKNAME FROM ACCOUNTS WHERE NICKNAME = ?";

    private IConnector connector;

    public SignUpDao(){
        this(new MySQLConnector());
    }

    public SignUpDao(IConnector connector){
        this.connector = connector;
    }

    public RegisteredUser signUpNewUser(UnregisteredUser user) throws DaoException{

        if(user == null)
            throw new DaoException("SignUpDao: Unregistered user is null");

        long addedUserId = addUserToDatabase(user);
        return new RegisteredUser(addedUserId, user.getNickname());
    }

    private long addUserToDatabase(UnregisteredUser user) throws DaoException {

        Connection connection = null;
        PreparedStatement insertUserQuery = null;
        ResultSet generatedKeys = null;
        try{
            connection = connector.getConnection();
            insertUserQuery = connection.prepareStatement(INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            insertUserQuery.setString(1, user.getNickname());
            insertUserQuery.setString(2, user.getEmail());
            insertUserQuery.setString(3, user.getPassword());

            if(insertUserQuery.executeUpdate() == 0)
                throw new DaoException("SignUpDao: user wasn't insert");

            generatedKeys =  insertUserQuery.getGeneratedKeys();
            if(generatedKeys.next())
                return generatedKeys.getLong(1);

            throw new DaoException("SignUpDao: Can't get user id from result set");
        }
        catch (SQLException e){
            throw new DaoException("SignUpDao: Error add user to database", e);
        }
        finally {
            closeResultSet(generatedKeys);
            closeStatement(insertUserQuery);
            closeConnection(connection);
        }
    }

    public boolean nicknameIsBusy(String nickname) throws DaoException{

        if(nickname == null)
            return false;

        Connection connection = null;
        PreparedStatement selectUserQuery = null;
        ResultSet selectResult = null;
        try{
            connection = connector.getConnection();
            selectUserQuery = connection.prepareStatement(SELECT_USER_ID);
            selectUserQuery.setString(1, nickname);

            selectResult = selectUserQuery.executeQuery();
            return selectResult.next();
        }
        catch (SQLException e){
            throw new DaoException("SignUpDao: Error checking nickname", e);
        }
        finally {
            closeConnection(connection);
            closeStatement(selectUserQuery);
            closeResultSet(selectResult);
        }
    }

    private void closeConnection(Connection connection) throws DaoException{
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException("SignUpDao: Error closing connection", e);
            }
        }
    }

    private void closeStatement(PreparedStatement statement) throws DaoException{
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("SignUpDao: Error closing statement", e);
            }
        }
    }

    private void closeResultSet(ResultSet resultSet) throws DaoException{
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DaoException("SignUpDao: Error closing result set", e);
            }
        }
    }

}
