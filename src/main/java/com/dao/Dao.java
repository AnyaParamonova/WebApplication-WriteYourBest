package com.dao;

import com.dao.connectors.Connector;
import com.dao.exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Anastasia_Paramonova on 23.11.2016.
 */
public abstract class Dao {

    Connector connector;

    protected Dao(Connector connector){
        this.connector = connector;
    }

    protected void closeConnection(Connection connection) throws DaoException {
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException("Error closing connection", e);
            }
        }
    }

    protected void closeStatement(PreparedStatement statement) throws DaoException{
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Error closing statement", e);
            }
        }
    }

    protected void closeResultSet(ResultSet resultSet) throws DaoException{
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DaoException("Error closing result set", e);
            }
        }
    }
}
