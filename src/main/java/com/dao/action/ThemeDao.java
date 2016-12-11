package com.dao.action;

import com.dao.connector.Connector;
import com.dao.connector.MySQLConnector;
import com.dao.exception.DaoException;
import com.model.composition.Composition;
import com.model.composition.Theme;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Anastasia_Paramonova on 28.11.2016.
 */
public class ThemeDao extends Dao {

    private static String INSERT_THEME_QUERY = "INSERT INTO THEMES(ADMIN_ID, BODY) VALUES (?, ?)";
    private static String SELECT_LAST_ADDED_THEME = "SELECT ID, BODY FROM THEMES ORDER BY CREATION_DATE DESC LIMIT 1";
    private static String SELECT_ALL_THEMES = "SELECT ID, BODY, CREATION_DATE FROM themes ORDER BY CREATION_DATE DESC";

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

    public ArrayList<Theme> extractAllThemes() throws DaoException{
        return selectThemesFromDatabase();
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

    private ArrayList<Theme> selectThemesFromDatabase() throws DaoException {
        Connection connection = null;
        PreparedStatement selectThemesQuery = null;
        ResultSet selectResult = null;
        try{
            ArrayList<Theme> themes = new ArrayList<Theme>();
            connection = connector.getConnection();
            selectThemesQuery = connection.prepareStatement(SELECT_ALL_THEMES);

            selectResult = selectThemesQuery.executeQuery();
            while (selectResult.next()){
                int id = selectResult.getInt("id");
                String theme = selectResult.getString("body");
                Timestamp creationDate = selectResult.getTimestamp("creation_date");

                themes.add(new Theme(id, theme,creationDate));
            }
            return themes;
        }
        catch (SQLException e){
            throw new DaoException("ThemeDao: Error select themes", e);
        }
        finally {
            closeResultSet(selectResult);
            closeStatement(selectThemesQuery);
            closeConnection(connection);
        }
    }
}
