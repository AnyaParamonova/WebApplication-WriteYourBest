package com.dao.action;

import com.dao.connector.Connector;
import com.dao.connector.MySQLConnector;
import com.dao.exception.DaoException;

import java.sql.*;

/**
 * Created by Anastasia_Paramonova on 28.11.2016.
 */
public class ThemeDao extends Dao {

    private static String INSERT_THEME_QUERY = "INSERT INTO THEMES(ADMIN_ID, BODY) VALUES (?, ?)";
    private static String SELECT_LAST_ADDED_THEME = "SELECT ID, BODY FROM THEMES ORDER BY CREATION_DATE DESC LIMIT 1";

    public ThemeDao(Connector connector) {
        super(connector);
    }

    public ThemeDao(){
        super(new MySQLConnector());
    }

    public boolean saveTheme(int adminId, String body) throws DaoException {
        if(body == null)
            throw new NullPointerException("body");

        return insertThemeIntoDatabase(adminId, body);
    }

    public String extractCurrentDateTheme() throws DaoException {
        return selectThemeFromDatabase();
    }

    private boolean insertThemeIntoDatabase(int adminId, String body) throws DaoException {
        Connection connection = null;
        PreparedStatement insertUserQuery = null;
        try{
            connection = connector.getConnection();
            insertUserQuery = connection.prepareStatement(INSERT_THEME_QUERY);
            insertUserQuery.setInt(1, adminId);
            insertUserQuery.setString(2, body);

            return (insertUserQuery.executeUpdate() != 0);
        }
        catch (SQLException e){
            throw new DaoException("ThemeDao: Error add theme to database", e);
        }
        finally {
            closeStatement(insertUserQuery);
            closeConnection(connection);
        }
    }

    private String selectThemeFromDatabase() throws DaoException {
        Connection connection = null;
        PreparedStatement selectUserQuery = null;
        ResultSet selectResult = null;
        try{
            connection = connector.getConnection();
            selectUserQuery = connection.prepareStatement(SELECT_LAST_ADDED_THEME);

            selectResult = selectUserQuery.executeQuery();
            if(selectResult.next()){
                return selectResult.getString("body");
            }
            return null;
        }
        catch (SQLException e){
            throw new DaoException("ThemeDao: Error select last theme", e);
        }
        finally {
            closeResultSet(selectResult);
            closeStatement(selectUserQuery);
            closeConnection(connection);
        }
    }
}
