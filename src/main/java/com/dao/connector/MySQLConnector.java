package com.dao.connector;

import com.dao.exception.DaoException;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Anastasia_Paramonova on 22.11.2016.
 */

public class MySQLConnector implements Connector {

    private static DataSource connectionPool;
    private static boolean connectorIsOpen;


    static {
        String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/write_your_best_database?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
        String USER_NAME = "root";
        String USER_PASSWORD = "123456";

        PoolProperties p = new PoolProperties();
        p.setUrl(URL);
        p.setDriverClassName(DRIVER_CLASS_NAME);
        p.setUsername(USER_NAME);
        p.setPassword(USER_PASSWORD);
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        connectionPool = new DataSource();
        connectionPool.setPoolProperties(p);

        connectorIsOpen = true;
    }

    public Connection getConnection() throws DaoException {
        try{
            if(connectorIsOpen){
                return connectionPool.getConnection();
            }
            else{
                throw new DaoException("MySQLConnector: Connector is already closet");
            }
        }
        catch(SQLException e){
            throw new DaoException("MySQLConnector: Error get connection from connection pool", e);
        }
    }

    public void closeConnector(){
        if(connectorIsOpen){
            connectionPool.close();
            connectorIsOpen = false;
        }
    }
}
