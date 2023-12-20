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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            userModel data = ParseRequest.toObject(request,userModel.class);

//            ResultSet checkId= userService.getById(data.getId());
//Tìm thông tin user bằng username:Tien
            ResultSet checkUsername = userService.getOne(userService.UserProp.username,data.getUsername());
            if(checkUsername.next()==true){
                // Chuyển đổi ResultSet thành đối tượng UserModel
                userModel user = new userModel();
                user.setId(checkUsername.getInt("id"));
                user.setName(checkUsername.getString("name"));
                user.setAddress(checkUsername.getString("address"));
                user.setBirthday(checkUsername.getString("birthday"));
                user.setPhone(checkUsername.getString("phone"));
                user.setRole(checkUsername.getInt("role"));
                user.setImage(checkUsername.getString("image"));
                user.setUsername(checkUsername.getString("username"));

//                Trả về kết quả
                response.setStatus(200);
                response.setContentType("text/plain");
                response.getWriter().println("User found:");
                response.getWriter().println("Id: " + user.getId());
                response.getWriter().println("Name: " + user.getName());
                response.getWriter().println("Address: " + user.getAddress());
                response.getWriter().println("Birthday "+ user.getBirthday());
                response.getWriter().println("Phone: " + user.getPhone());
                response.getWriter().println("Role: " + user.getRole());
                response.getWriter().println("Image: " + user.getImage());
                response.getWriter().println("Username: " + user.getUsername());


                return;
//            } else {
//                // Người dùng không tồn tại
//                response.setStatus(404);
//                response.setContentType("text/plain");
//                response.getWriter().println("User not found");
//                return;

            }
//  Tìm thông tin bằng email:Tien
            ResultSet checkEmail = userService.getOne(userService.UserProp.email,data.getEmail());
            if(checkEmail.next()==true) {
                // Chuyển đổi ResultSet thành đối tượng UserModel
                userModel user = new userModel();
                user.setId(checkEmail.getInt("id"));
                user.setName(checkEmail.getString("name"));
                user.setAddress(checkEmail.getString("address"));
                user.setBirthday(checkEmail.getString("birthday"));
                user.setPhone(checkEmail.getString("phone"));
                user.setRole(checkEmail.getInt("role"));
                user.setImage(checkEmail.getString("image"));
                user.setUsername(checkEmail.getString("username"));

//                Trả về kết quả
                response.setStatus(200);
                response.setContentType("text/plain");
                response.getWriter().println("User found:");
                response.getWriter().println("Id: " + user.getId());
                response.getWriter().println("Name: " + user.getName());
                response.getWriter().println("Address: " + user.getAddress());
                response.getWriter().println("Birthday " + user.getBirthday());
                response.getWriter().println("Phone: " + user.getPhone());
                response.getWriter().println("Role: " + user.getRole());
                response.getWriter().println("Image: " + user.getImage());
                response.getWriter().println("Username: " + user.getUsername());


                return;
            }
//  Tìm kiếm thông tin bằng phone:Tien
            ResultSet checkPhone = userService.getOne(userService.UserProp.phone,data.getPhone());
            if(checkPhone.next()==true) {
                // Chuyển đổi ResultSet thành đối tượng UserModel
                userModel user = new userModel();
                user.setId(checkPhone.getInt("id"));
                user.setName(checkPhone.getString("name"));
                user.setAddress(checkPhone.getString("address"));
                user.setBirthday(checkPhone.getString("birthday"));
                user.setPhone(checkPhone.getString("phone"));
                user.setRole(checkPhone.getInt("role"));
                user.setImage(checkPhone.getString("image"));
                user.setUsername(checkPhone.getString("username"));

//                  Trả về kết quả
                response.setStatus(200);
                response.setContentType("text/plain");
                response.getWriter().println("User found:");
                response.getWriter().println("Id: " + user.getId());
                response.getWriter().println("Name: " + user.getName());
                response.getWriter().println("Address: " + user.getAddress());
                response.getWriter().println("Birthday " + user.getBirthday());
                response.getWriter().println("Phone: " + user.getPhone());
                response.getWriter().println("Role: " + user.getRole());
                response.getWriter().println("Image: " + user.getImage());
                response.getWriter().println("Username: " + user.getUsername());


                return;
            }
//            if(checkUsername.next()==true){
//                response.setStatus(200);
//                response.setContentType("text/plain");
//                response.getWriter().println("Checked");
//                return;
//            }
//Kiểm tra tài khoản tồn tại
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
//update user:Tien
            String sqlUD = "UPDATE users SET image=?, phone=?, email=?, address=?, username=?, password=?, birthday=?, role=?, name=? WHERE id=?";
            System.out.println("RunUD");
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

//Them user
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
