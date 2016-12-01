package com.dao.action;

import com.dao.connector.Connector;
import com.dao.connector.MySQLConnector;
import com.dao.exception.DaoException;
import com.model.composition.Composition;
import org.w3c.dom.ranges.RangeException;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Anastasia_Paramonova on 28.11.2016.
 */
public class CompositionDao extends Dao {

    private static String INSERT_COMPOSITION_QUERY = "INSERT INTO COMPOSITIONS(AUTHOR_ID, THEME_ID, BODY) VALUES(?, (SELECT ID FROM THEMES ORDER BY CREATION_DATE DESC LIMIT 1), ?)";
    private static String SELECT_COMPOSITION_LIST = "SELECT c.ID AS id, t.BODY AS theme , c.BODY as composition, c.CREATION_DATE as creation_date FROM COMPOSITIONS c INNER JOIN themes t ON c.THEME_ID = t.ID WHERE c.AUTHOR_ID = ? ORDER BY c.CREATION_DATE DESC LIMIT ?, ?";

    public CompositionDao(){
        super(new MySQLConnector());
    }

    public CompositionDao(Connector connector) {
        super(connector);
    }


    public ArrayList<Composition> getCompositionList(int userId, int offset, int count) throws DaoException {
        if(offset < 0)
            throw new IllegalArgumentException("offset");

        if(count < 0)
            throw new IllegalArgumentException("count");

        return selectCompositionsFromDatabase(userId, offset, count);
    }

    public boolean saveComposition(int authorId, String body) throws DaoException {
        if(body == null)
            throw new NullPointerException("body");

        return insertCompositionIntoDatabase(authorId, body);
    }

    private boolean insertCompositionIntoDatabase(int authorId, String body) throws DaoException {
        Connection connection = null;
        PreparedStatement insertUserQuery = null;
        try{
            connection = connector.getConnection();
            insertUserQuery = connection.prepareStatement(INSERT_COMPOSITION_QUERY);
            insertUserQuery.setInt(1, authorId);
            insertUserQuery.setString(2, body);

            return (insertUserQuery.executeUpdate() != 0);
        }
        catch (SQLException e){
            throw new DaoException("ThemeDao: Error add composition to database", e);
        }
        finally {
            closeStatement(insertUserQuery);
            closeConnection(connection);
        }
    }

    private ArrayList<Composition> selectCompositionsFromDatabase(int userId, int offset, int count) throws DaoException {
        Connection connection = null;
        PreparedStatement selectListQuery = null;
        ResultSet result = null;

        ArrayList<Composition> compositions = new ArrayList<Composition>();
        try{

            connection = connector.getConnection();
            selectListQuery = connection.prepareStatement(SELECT_COMPOSITION_LIST);
            selectListQuery.setInt(1, userId);
            selectListQuery.setInt(2, offset);
            selectListQuery.setInt(3, count);

            result = selectListQuery.executeQuery();
            while (result.next()){
                int id = result.getInt("id");
                String theme = result.getString("theme");
                String composition = result.getString("composition");
                Timestamp creationDate = result.getTimestamp("creation_date");

                compositions.add(new Composition(id, theme, composition, creationDate));
            }
            return compositions;
        }
        catch (SQLException e){
            throw new DaoException("ThemeDao: Error select composition from database", e);
        }
        finally {
            closeResultSet(result);
            closeStatement(selectListQuery);
            closeConnection(connection);
        }
    }
}
