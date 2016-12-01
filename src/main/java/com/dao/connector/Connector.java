package com.dao.connector;

import com.dao.exception.DaoException;

import java.sql.Connection;

/**
 * Created by Anastasia_Paramonova on 22.11.2016.
 */

public interface Connector {
    public Connection getConnection() throws DaoException;
    public void closeConnector() throws DaoException;
}
