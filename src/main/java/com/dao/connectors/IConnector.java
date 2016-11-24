package com.dao.connectors;

import com.dao.exceptions.DaoException;

import java.sql.Connection;

/**
 * Created by Anastasia_Paramonova on 22.11.2016.
 */

public interface IConnector {
    public Connection getConnection() throws DaoException;
    public void closeConnector() throws DaoException;
}
