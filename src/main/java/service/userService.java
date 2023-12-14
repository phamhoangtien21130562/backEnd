package service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import migration.testConnectionDB;
import model.userModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/users")
public class userService extends HttpServlet {
    Connection conn = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter rs = response.getWriter();
        rs.print("Hello");
        rs.flush();
        rs.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {





        userModel data = this.ParseRequest(request,userModel.class);
            response.setContentType("text/plain");
            response.getWriter().println("Data received from the request body: " + data.getUsername());

            conn = testConnectionDB.Dbcontext();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <T> T ParseRequest(HttpServletRequest request,Class<T> modelclass ) {
        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = null;
        T returnjson=null;
        try {
            reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }


            String bodyData = requestBody.toString();
            Gson gson = new Gson();
            returnjson = gson.fromJson(requestBody.toString(), modelclass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return returnjson ;
    }

}
