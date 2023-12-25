package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import migration.testConnectionDB;
import model.userModel;
import service.userService;
import util.ParseRequest;
import util.ParseResponse;

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

        //lấy param từ request
        try {

            String paramValue = request.getParameter("id");



            userModel user = userService.getById(Integer.parseInt(paramValue));
            ParseResponse.Res(response, ParseResponse.HttpStatus.OK,user);

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    //delete user: Tien
    protected  void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        try {

            userModel data = ParseRequest.toObject(request, userModel.class);
            userModel user = userService.getOne(userService.UserProp.username, data.getUsername());
            String sqlDel ="DELETE from users WHERE id=?";
            try(PreparedStatement statement = testConnectionDB.stm(sqlDel)){
                statement.setInt(1,data.getId());
                int rowexc =statement.executeUpdate();
                if(rowexc > 0){
                    System.out.println("user delete successfully!");
                }else {
                    System.out.println("No user found with the given ID.");
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response){
        try {

            userModel data = ParseRequest.toObject(request, userModel.class);
            ResultSet checkUsername = null;
            userModel user = userService.getOne(userService.UserProp.username, data.getUsername());
            if (user==null){
                ParseResponse.textRes(response, ParseResponse.HttpStatus.NOT_FOUND,"Khong tim thay user");
            }

            String sqlUD = "UPDATE users SET image=?, phone=?, email=?, address=?, username=?, password=?, birthday=?, role=?, name=? WHERE id=?";
            try (PreparedStatement statement = testConnectionDB.stm(sqlUD)) {
                statement.setString(1, data.getImage());
                statement.setString(2, data.getPhone());
                statement.setString(3, data.getEmail());
                statement.setString(4, data.getAddress());
                statement.setString(5, data.getUsername());
                statement.setString(6, data.getPassword());
                statement.setString(7, data.getBirthday());
                statement.setInt(8, data.getRole());
                statement.setString(9, data.getName());
                statement.setInt(10, data.getId()); // Update id nhập vào "Where id"

                int rowexc = statement.executeUpdate();
                if (rowexc > 0) {
                    System.out.println("User updated successfully!");
                    return;
                } else {
                    System.out.println("No user found with the given ID.");
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            userModel data = ParseRequest.toObject(request, userModel.class);
            userModel user = userService.getOne(userService.UserProp.username, data.getUsername());

            if (user!=null){
                ParseResponse.textRes(response, ParseResponse.HttpStatus.INTERNAL_SERVER_ERROR,"User da ton tai");
            }

            boolean isSuccess= userService.createUser(data);
            if (!isSuccess){
                ParseResponse.textRes(response, ParseResponse.HttpStatus.INTERNAL_SERVER_ERROR,"Tao khong thanh cong");
                return;
            }
            String json = ParseRequest.toJson(data);
            response.setContentType("application/json");
            response.getWriter().println(json);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}