package classes.dao.connectors;

import classes.dao.exceptions.DaoException;
import org.apache.commons.dbcp.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Anastasia_Paramonova on 22.11.2016.
 */

public class MySQLConnector implements IConnector{

    private static BasicDataSource connectionPool;
    private static boolean connectorIsOpen;


    static {
        String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/write_your_best_database?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
        String USER_NAME = "root";
        String USER_PASSWORD = "123456";

        connectionPool = new BasicDataSource();
        connectionPool.setDriverClassName(DRIVER_CLASS_NAME);
        connectionPool.setUrl(URL);
        connectionPool.setUsername(USER_NAME);
        connectionPool.setPassword(USER_PASSWORD);

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

    public void closeConnector() throws DaoException {
        try {
            if(connectorIsOpen){
                connectionPool.close();
                connectorIsOpen = false;
            }
        } catch (SQLException e) {
            throw new DaoException("MySQLConnector: Error close connector", e);
        }
    }
}
