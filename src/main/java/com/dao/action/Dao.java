package com.dao.action;

import com.dao.connector.Connector;
import com.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Anastasia_Paramonova on 23.11.2016.
 */
abstract class Dao {

    Connector connector;

    Dao(Connector connector){
        this.connector = connector;
    }

    void closeConnection(Connection connection) throws DaoException {
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException("Error closing connection", e);
            }
        }
    }

    void closeStatement(PreparedStatement statement) throws DaoException{
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Error closing statement", e);
            }
        }
    }

    void closeResultSet(ResultSet resultSet) throws DaoException{
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DaoException("Error closing result set", e);
            }
        }
    }
}
