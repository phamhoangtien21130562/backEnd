package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import migration.testConnectionDB;
import model.userModel;
import service.userService;
import util.ParseRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(urlPatterns = "/users")
public class userController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter rs = response.getWriter();
        //lấy param từ request
        String paramValue = request.getParameter("username");
        rs.print("Hello World");
        rs.flush();
        rs.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            userModel data = ParseRequest.toObject(request,userModel.class);
            ResultSet checkUser = userService.getOne(userService.UserProp.username,data.getUsername());
            System.out.println("Run");
            if(checkUser.next()==true){
                response.setStatus(200);
                response.setContentType("text/plain");
                response.getWriter().println("Checked");
                return;
            }
            String checkSql = "SELECT username, email, phone " +
                    "FROM users  WHERE username = ? OR email = ? OR phone = ? ";
            PreparedStatement checkStm = testConnectionDB.stm(checkSql);
            checkStm.setString(1,data.getUsername());
            checkStm.setString(2,data.getEmail());
            checkStm.setString(3,data.getPhone());
            ResultSet checkResult = checkStm.executeQuery();
            if(checkResult.next()==true){

                response.setStatus(401);
                response.setContentType("text/plain");
                response.getWriter().println("Tai khoan da ton tai");
                return;

            }
            String sql = "INSERT INTO users(image, phone, email, address,username, password, birthday,role,name) " +
                    "VALUES(?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement = testConnectionDB.stm(sql);

            statement.setString(1,data.getImage());
            statement.setString(2,data.getPhone());
            statement.setString(3,data.getEmail());
            statement.setString(4,data.getAddress());
            statement.setString(5,data.getUsername());
            statement.setString(6,data.getPassword());
            statement.setString(7,data.getBirthday());
            statement.setInt(8,data.getRole());
            statement.setString(9,data.getName());

            int rowexc = statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            int generatedID =-1;
            if (generatedKey.next()){
                generatedID = generatedKey.getInt(1);
                data.setId(generatedID);
            }
            if(generatedID == -1){

                statement.close();
                generatedKey.close();
                throw new SQLException(new Throwable());

            }
            String json = ParseRequest.toJson(data);
            response.setContentType("application/json");
            response.getWriter().println(json);

            statement.close();
            generatedKey.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
