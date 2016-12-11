package com.dao.action;

import com.dao.connector.Connector;
import com.dao.connector.MySQLConnector;
import com.dao.exception.DaoException;
import com.model.composition.Theme;
import com.model.user.state.AuthorizedUser;
import com.model.user.state.RegisteredUser;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Anastasia_Paramonova on 11.12.2016.
 */
public class UserDao extends Dao {

    private static String UPDATE_EMAIL_QUERY = "UPDATE accounts SET EMAIL = ? WHERE ID = ?";
    private static String UPDATE_NICKNAME_QUERY = "UPDATE accounts SET NICKNAME = ? WHERE ID = ?";
    private static String UPDATE_PASSWORD_QUERY = "UPDATE accounts SET PASSWORD = ? WHERE ID = ?";
    private static String SELECT_ALL_USERS = "SELECT ID, CREATION_DATE, TYPE, NICKNAME, EMAIL FROM accounts ORDER BY TYPE, NICKNAME";

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

    public ArrayList<RegisteredUser> extractAllUsers() throws DaoException {
        return selectUserListFromDatabase();
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

    private ArrayList<RegisteredUser> selectUserListFromDatabase() throws DaoException {
        Connection connection = null;
        PreparedStatement selectThemesQuery = null;
        ResultSet selectResult = null;
        try{
            ArrayList<RegisteredUser> users = new ArrayList<RegisteredUser>();
            connection = connector.getConnection();
            selectThemesQuery = connection.prepareStatement(SELECT_ALL_USERS);

            selectResult = selectThemesQuery.executeQuery();
            while (selectResult.next()){
                int id = selectResult.getInt("id");
                String type = selectResult.getString("type");
                String nickname = selectResult.getString("nickname");
                String email = selectResult.getString("email");
                Timestamp creationDate = selectResult.getTimestamp("creation_date");

                users.add(new RegisteredUser(id, nickname, email, type, creationDate));
            }
            return users;
        }
        catch (SQLException e){
            throw new DaoException("ThemeDao: Error select users", e);
        }
        finally {
            closeResultSet(selectResult);
            closeStatement(selectThemesQuery);
            closeConnection(connection);
        }
    }
}
