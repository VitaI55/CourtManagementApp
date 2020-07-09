package Model.Dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private Connection connection = null;
    static final Logger DB_LOGGER = LogManager.getLogger(DBConnect.class);

    public DBConnect() {
    }

    public Connection getConnection() {
  //      DB_LOGGER.info("connecting...");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306" +
                    "/db?characterEncoding=latin1&useUnicode=true&" +
                    "useJDBCCompliantTimezoneShift=true&" +
                    "useLegacyDatetimeCode=false&" +
                    "serverTimezone=UTC";
            String user = "root";
            String password = "frenkyPin4598";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            DB_LOGGER.debug(e);
        }

        return connection;
    }

}
