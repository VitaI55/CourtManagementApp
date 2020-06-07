package Model.Dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    public DBConnect() {
    }

    public Connection getConnection() {
        Connection connection = null;
        System.out.println("connecting..."); //there must be logger
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
            e.printStackTrace();
        }
        return connection;
    }

}
