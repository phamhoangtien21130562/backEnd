package controller;

import migration.testConnectionDB;
import model.commentsModel;
import model.userModel;
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
        response.setContentType("application/json");
        response.getWriter().println("Hello Comments");
        //lấy param từ request
//        try {
//
//            String paramValue = request.getParameter("id");
//
//
//
//            userModel user = userService.getById(Integer.parseInt(paramValue));
//            ParseResponse.Res(response, ParseResponse.HttpStatus.OK,user);
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            commentsModel data = ParseRequest.toObject(request, commentsModel.class);

//            ResultSet checkId= userService.getById(data.getId());
//delete comment:Tien
            String sqlDel = "DELETE FROM comments WHERE id=?";
            System.out.println("RunDelete");
            try (PreparedStatement statement = testConnectionDB.stm(sqlDel)){
                statement.setInt(1,data.getId());
                int rowexc = statement.executeUpdate();
                if(rowexc>0){
                    System.out.println("Comment delete successfully!");
                    return;
                }else{
                    System.out.println("No comment found with the given ID.");
                }


            }catch (SQLException e){
                e.printStackTrace();
            }




//update comment:Tien
            String sqlUD = "UPDATE comments SET content=?, rating=? WHERE id=?";
            System.out.println("RunUD");
            try (PreparedStatement statement = testConnectionDB.stm(sqlUD)) {
                statement.setString(1, data.getContent());
                statement.setString(2, data.getRating());
                statement.setInt(3, data.getId()); // Update id nhập vào "Where id"

                int rowexc = statement.executeUpdate();
                if (rowexc > 0) {
                    System.out.println("Comment updated successfully!");
                    return;
                } else {
                    System.out.println("No comment found with the given ID.");
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

//Them comment
            String sql = "INSERT INTO comments(name, content, rating) " +
                    "VALUES(?,?,?)";
            System.out.println("Run Insert");
            PreparedStatement statement = testConnectionDB.stm(sql);

            statement.setString(1,data.getName());
            statement.setString(2,data.getContent());
            statement.setString(3,data.getRating());



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