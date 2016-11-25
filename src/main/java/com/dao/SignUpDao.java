package com.dao;

import com.dao.connectors.IConnector;
import com.dao.connectors.MySQLConnector;
import com.dao.exceptions.DaoException;
import com.model.users.AuthorizedUser;
import com.model.users.UnregisteredUser;

import java.sql.*;

/**
 * Created by Anastasia_Paramonova on 22.11.2016.
 */

public class SignUpDao extends Dao{

    private static String INSERT_USER_QUERY = "INSERT INTO ACCOUNTS(NICKNAME, EMAIL, PASSWORD, TYPE, CREATION_DATE) VALUES (?, ?, ?, \"regular\", current_timestamp())";
    private static String SELECT_USER_NICKNAME = "SELECT ID, NICKNAME FROM ACCOUNTS WHERE NICKNAME = ?";
    private static String SELECT_USER_EMAIL = "SELECT ID, EMAIL FROM ACCOUNTS WHERE EMAIL = ?";

    public SignUpDao(){
        this(new MySQLConnector());
    }

    public SignUpDao(IConnector connector){
        super(connector);
    }

    public AuthorizedUser signUpNewUser(UnregisteredUser user) throws DaoException {

        if(user == null)
            throw new DaoException("SignUpDao: Unregistered user parameter is null");

        long addedUserId = addUserToDatabase(user);
        return new AuthorizedUser(addedUserId, user.getNickname());
    }

    public boolean nicknameIsBusy(String nickname) throws DaoException{

        if(nickname == null)
            throw new NullPointerException("nickname");
        return nicknameExistsInDatabase(nickname);
    }

    public boolean emailIsBusy(String email) throws DaoException{
        if(email == null)
            throw new NullPointerException("nickname");
        return emailExistsInDatabase(email);
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
                throw new DaoException("SignUpDao: users wasn't insert");

            generatedKeys =  insertUserQuery.getGeneratedKeys();
            if(generatedKeys.next())
                return generatedKeys.getLong(1);

            throw new DaoException("SignUpDao: Can't get users id from result set");
        }
        catch (SQLException e){
            throw new DaoException("SignUpDao: Error add users to database", e);
        }
        finally {
            closeResultSet(generatedKeys);
            closeStatement(insertUserQuery);
            closeConnection(connection);
        }
    }

    private boolean nicknameExistsInDatabase(String nickname) throws DaoException {
        return checkItemIsBusy(SELECT_USER_NICKNAME, nickname, "SignUpDao: Error checking nickname");
    }


    private boolean emailExistsInDatabase(String email) throws DaoException {
        return checkItemIsBusy(SELECT_USER_EMAIL, email, "SignUpDao: Error checking email");
    }

    private boolean checkItemIsBusy(String selectQuery, String parameter, String errorMessage) throws DaoException{
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
