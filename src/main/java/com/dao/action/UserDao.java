package com.dao.action;

import com.dao.connector.Connector;
import com.dao.connector.MySQLConnector;
import com.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Anastasia_Paramonova on 11.12.2016.
 */
public class UserDao extends Dao {

    private static String UPDATE_EMAIL_QUERY = "UPDATE accounts SET EMAIL = ? WHERE ID = ?";
    private static String UPDATE_NICKNAME_QUERY = "UPDATE accounts SET NICKNAME = ? WHERE ID = ?";
    private static String UPDATE_PASSWORD_QUERY = "UPDATE accounts SET PASSWORD = ? WHERE ID = ?";

    public UserDao(Connector connector) {
        super(connector);
    }

    public UserDao(){
        super(new MySQLConnector());
    }

    public void changeUserEmail(int userId, String newEmail) throws DaoException {
        updateEmailInDatabase(userId, newEmail);
    }

    public void changeUserNickname(int userId, String newNickname) throws DaoException {
        updateNicknameInDatabase(userId, newNickname);
    }

    public void changeUserPassword(int userId, String newPassword) throws DaoException {
        updatePasswordInDatabase(userId, newPassword);
    }

    private void updateEmailInDatabase(int userId, String newEmail) throws DaoException {
        performUpdate(userId, newEmail, UPDATE_EMAIL_QUERY);
    }

    private void updateNicknameInDatabase(int userId, String newNickname) throws DaoException {
        performUpdate(userId, newNickname, UPDATE_NICKNAME_QUERY);
    }

    private void updatePasswordInDatabase(int userId, String newPassword) throws DaoException {
        performUpdate(userId, newPassword, UPDATE_PASSWORD_QUERY);
    }

    private void performUpdate(int userId, String newParameter, String query) throws DaoException {
        Connection connection = null;
        PreparedStatement updateQuery = null;
        try{
            connection = connector.getConnection();
            updateQuery = connection.prepareStatement(query);
            updateQuery.setString(1, newParameter);
            updateQuery.setInt(2, userId);

            updateQuery.executeUpdate();
        }
        catch (SQLException e){
            throw new DaoException("UserDao: Error perform update.", e);
        }
        finally {
            closeStatement(updateQuery);
            closeConnection(connection);
        }
    }
}
