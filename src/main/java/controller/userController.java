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
            if (user==null){
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
            userModel user = userService.getOne(userService.UserProp.username, data.getUsername());
            if (user==null){
                ParseResponse.textRes(response, ParseResponse.HttpStatus.NOT_FOUND,"Khong tim thay user");
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