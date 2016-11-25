package com.dao;

import com.dao.connectors.IConnector;
import com.dao.connectors.MySQLConnector;
import com.dao.exceptions.DaoException;
import com.model.users.SelectedUser;
import com.model.users.UnauthorizedUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Anastasia_Paramonova on 23.11.2016.
 */
public class LogInDao extends Dao{

    private static String SELECT_USER_QUERY = "SELECT ID, NICKNAME, PASSWORD FROM ACCOUNTS WHERE NICKNAME=?";


    public LogInDao(){
        this(new MySQLConnector());
    }

    public LogInDao(IConnector connector){
        super(connector);
    }

    public SelectedUser findUser(String nickname) throws DaoException {
        if(nickname == null)
            throw new DaoException("LogInDao: Unregistered user parameter is null");

        return getUserFromDatabase(nickname);
    }

    private SelectedUser getUserFromDatabase(String nickname) throws DaoException {
        Connection connection = null;
        PreparedStatement selectUserQuery = null;
        ResultSet selectResult = null;
        try{
            connection = connector.getConnection();
            selectUserQuery = connection.prepareStatement(SELECT_USER_QUERY);
            selectUserQuery.setString(1, nickname);

            selectResult = selectUserQuery.executeQuery();
            if(selectResult.next()){
                long id = selectResult.getLong("id");
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
