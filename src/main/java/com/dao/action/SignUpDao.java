package com.dao.action;

import com.dao.connector.Connector;
import com.dao.connector.MySQLConnector;
import com.dao.exception.DaoException;
import com.model.user.state.AuthorizedUser;
import com.model.user.state.UnregisteredUser;

import java.sql.*;

/**
 * Created by Anastasia_Paramonova on 22.11.2016.
 */

public class SignUpDao extends Dao {

    private static String INSERT_USER_QUERY = "INSERT INTO ACCOUNTS(NICKNAME, EMAIL, PASSWORD, TYPE) VALUES (?, ?, ?, \"regular\")";
    private static String SELECT_USER_BY_NICKNAME = "SELECT ID, NICKNAME FROM ACCOUNTS WHERE NICKNAME = ?";
    private static String SELECT_USER_BY_EMAIL = "SELECT ID, EMAIL FROM ACCOUNTS WHERE EMAIL = ?";

    public SignUpDao(){
        this(new MySQLConnector());
    }

    public SignUpDao(Connector connector){
        super(connector);
    }

    public AuthorizedUser signUpUser(UnregisteredUser user) throws DaoException {

        if(user == null)
            throw new DaoException("SignUpDao: Unregistered user parameter is null");

        int addedUserId = insertUserIntoDatabase(user);
        return new AuthorizedUser(addedUserId, user.getNickname());
    }

    public boolean nicknameIsOccupied(String nickname) throws DaoException{

        if(nickname == null)
            throw new NullPointerException("nickname");
        return nicknameExistsInDatabase(nickname);
    }

    public boolean emailIsOccupied(String email) throws DaoException{
        if(email == null)
            throw new NullPointerException("nickname");
        return emailExistsInDatabase(email);
    }

    private int insertUserIntoDatabase(UnregisteredUser user) throws DaoException {

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
                return generatedKeys.getInt(1);

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

    private boolean nicknameExistsInDatabase(String nickname) throws DaoException {
        return checkItemIsOccupied(SELECT_USER_BY_NICKNAME, nickname, "SignUpDao: Error checking nickname");
    }


    private boolean emailExistsInDatabase(String email) throws DaoException {
        return checkItemIsOccupied(SELECT_USER_BY_EMAIL, email, "SignUpDao: Error checking email");
    }

    private boolean checkItemIsOccupied(String selectQuery, String parameter, String errorMessage) throws DaoException{
        Connection connection = null;
        PreparedStatement selectUserQuery = null;
        ResultSet selectResult = null;
        try{
            connection = connector.getConnection();
            selectUserQuery = connection.prepareStatement(selectQuery);
            selectUserQuery.setString(1, parameter);

            selectResult = selectUserQuery.executeQuery();
            return selectResult.next();
        }
        catch (SQLException e){
            throw new DaoException(errorMessage, e);
        }
        finally {
            closeResultSet(selectResult);
            closeStatement(selectUserQuery);
            closeConnection(connection);
        }
    }
}
