package com.dao.action;

import com.dao.connector.Connector;
import com.dao.connector.MySQLConnector;
import com.dao.exception.DaoException;
import com.model.user.state.SelectedUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Anastasia_Paramonova on 23.11.2016.
 */
public class LogInDao extends Dao {

    private static String SELECT_USER_BY_NICKNAME = "SELECT ID, NICKNAME, PASSWORD FROM ACCOUNTS WHERE NICKNAME=?";

    public LogInDao(){
        this(new MySQLConnector());
    }

    public LogInDao(Connector connector){
        super(connector);
    }

    public SelectedUser findUser(String nickname) throws DaoException {
        if(nickname == null)
            throw new DaoException("LogInDao: Unregistered user parameter is null");

        return selectUserFromDatabase(nickname);
    }

    private SelectedUser selectUserFromDatabase(String nickname) throws DaoException {
        Connection connection = null;
        PreparedStatement selectUserQuery = null;
        ResultSet selectResult = null;
        try{
            connection = connector.getConnection();
            selectUserQuery = connection.prepareStatement(SELECT_USER_BY_NICKNAME);
            selectUserQuery.setString(1, nickname);

            selectResult = selectUserQuery.executeQuery();
            if(selectResult.next()){
                int id = selectResult.getInt("id");
                String name = selectResult.getString("nickname");
                String password = selectResult.getString("password");

                return new SelectedUser(id, name, password);
            }
            return null;
        }
        catch (SQLException e){
            throw new DaoException("LogInDao: Error select user", e);
        }
        finally {
            closeResultSet(selectResult);
            closeStatement(selectUserQuery);
            closeConnection(connection);
        }
    }
}
