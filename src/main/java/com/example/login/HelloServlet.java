package com.example.login;

import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }

    public static class testDB {
        public static void main(String[] args) {
            String url = "jdbc:mysql://localhost:3306/mydatabase";
            String user = "root";
            String password = "";
            String sql = "select * from students";
            try(Connection conn = DriverManager.getConnection(url,user,password)){
                System.out.println(conn.getCatalog());
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                showInfo(resultSet);
            }catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
        private static void showInfo(ResultSet rs){
            try{
                while (rs.next()){
                    System.out.println(rs.getString("columnName1")+" - "+ rs.getString("columnName2")
                            +" - "+ rs.getInt("columnName3")+" - "+ rs.getInt("columnName4"));
                }
            }catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
    }
}