//import sun.swing.SwingUtilities2;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class testConnectionDB {
    public static Connection getConnection() throws SQLException {
        String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
        String DB_USERNAME = "root";
        String DB_PASSWORD = "";

        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

}
