package controller;

import migration.testConnectionDB;
import model.commentsModel;
import model.userModel;
import service.commentsService;
import service.userService;
import util.ParseRequest;
import util.ParseResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/comments")
public class commentsController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            commentsModel data = ParseRequest.toObject(request, commentsModel.class);
            commentsModel cmt = commentsService.getOne(commentsService.CommentsProp.name, data.getName());
            if (cmt==null){
                ParseResponse.textRes(response, ParseResponse.HttpStatus.NOT_FOUND,"Khong tim thay user");
            }
            boolean isSuccess= commentsService.updateCmt(data);
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
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            commentsModel data = ParseRequest.toObject(request, commentsModel.class);
            commentsModel cmt = commentsService.getOne(commentsService.CommentsProp.name, data.getName());
            if (cmt==null){
                ParseResponse.textRes(response, ParseResponse.HttpStatus.NOT_FOUND,"Khong tim thay user");
            }
            boolean isSuccess= commentsService.deleteCmt(data);
            if (!isSuccess){
                ParseResponse.textRes(response, ParseResponse.HttpStatus.INTERNAL_SERVER_ERROR,"Xoa khong thanh cong");
                return;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            commentsModel data = ParseRequest.toObject(request, commentsModel.class);
            commentsModel cmt = commentsService.getOne(commentsService.CommentsProp.name, data.getName());


            boolean isSuccess= commentsService.createCmt(data);
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