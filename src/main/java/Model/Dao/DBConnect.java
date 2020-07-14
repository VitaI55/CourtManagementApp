package Model.Dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static final Logger DB_LOGGER =
            LogManager.getLogger(DBConnect.class);
    private Connection connection;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String JDBC_DB_URL = "jdbc:mysql://localhost:3306/db";
    static final String JDBC_USER = "root";
    static final String JDBC_PASS = "frenkyPin4598";

    public DBConnect() {
        this.connection = null;
    }

    public Connection getConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
        } catch (SQLException | ClassNotFoundException e) {
            DB_LOGGER.warn(e);
        }
        return connection;
    }
}
