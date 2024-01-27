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
import java.util.List;

@WebServlet(urlPatterns = "/users")
public class userController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id= request.getParameter("id");
            if (id == null){
                List<userModel> users = userService.getAll();
                ParseResponse.Res(response, ParseResponse.HttpStatus.OK,users);
                return;
            }
            List<userModel> user = userService.getById(Integer.parseInt(id));
            ParseResponse.Res(response, ParseResponse.HttpStatus.OK,user);


        }catch (Exception e){
            ParseResponse.textRes(response, ParseResponse.HttpStatus.INTERNAL_SERVER_ERROR,e.toString());

        }

    }
    //delete user: Tien
    protected  void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        try {

            userModel data = ParseRequest.toObject(request, userModel.class);
            List<userModel> user = userService.getOne(userService.UserProp.username, data.getUsername());
            if (user.isEmpty()){
                ParseResponse.textRes(response, ParseResponse.HttpStatus.NOT_FOUND,"Khong tim thay user");
            }
            boolean isSuccess= userService.deleteUser(data);
            if (!isSuccess){
                ParseResponse.textRes(response, ParseResponse.HttpStatus.INTERNAL_SERVER_ERROR,"Xoa khong thanh cong");
                return;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response){
        try {

            userModel data = ParseRequest.toObject(request, userModel.class);
            List<userModel> user = userService.getById(data.getId());
            if (user.isEmpty()){
                ParseResponse.textRes(response, ParseResponse.HttpStatus.NOT_FOUND,"Khong tim thay user");
                return;
            }
            boolean isSuccess= userService.updateUser(data);
            if (!isSuccess){
                ParseResponse.textRes(response, ParseResponse.HttpStatus.INTERNAL_SERVER_ERROR,"Cap nhat khong thanh cong");
                return;
            }

//            String json = ParseRequest.toJson(data);
//            response.setContentType("application/json");
//            response.getWriter().println(json);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            userModel data = ParseRequest.toObject(request, userModel.class);
            List<userModel> user = userService.getOne(userService.UserProp.username, data.getUsername());

            if (!user.isEmpty()){
                ParseResponse.textRes(response, ParseResponse.HttpStatus.INTERNAL_SERVER_ERROR,"User da ton tai");
            }

            boolean isSuccess= userService.createUser(data);
            if (!isSuccess){
                ParseResponse.textRes(response, ParseResponse.HttpStatus.INTERNAL_SERVER_ERROR,"Tao khong thanh cong");
                return;
            }
            // Lấy thông tin người dùng sau khi thêm thành công
            List<userModel> newUser = userService.getOne(userService.UserProp.username, data.getUsername());

            // Chuyển đổi thành đối tượng JSON
            String json = ParseRequest.toJson(newUser);

            // Gửi thông tin người dùng mới về cho client
            response.setContentType("application/json");
            response.getWriter().println(json);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}