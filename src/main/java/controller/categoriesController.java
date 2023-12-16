package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/categories")
public class categoriesController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter rs = response.getWriter();
        //lấy param từ request
        String paramValue = request.getParameter("name");
        rs.print("Hello Categories");
        rs.flush();
        rs.close();
    }
//    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//        try {
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
}
