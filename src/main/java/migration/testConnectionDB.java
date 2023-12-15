package migration;//import sun.swing.SwingUtilities2;


import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.SQLException;

import  java.sql.Statement;

public class testConnectionDB {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    public static Connection Dbcontext() throws SQLException {
        Connection conn = null;

        try {
            Class. forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
        } catch (SQLException e){
          e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }
    public static PreparedStatement stm(String sql) throws  SQLException{
        return Dbcontext().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
    }


}
