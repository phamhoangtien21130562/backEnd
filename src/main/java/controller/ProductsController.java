package controller;


import model.ProductModel;
import model.userModel;
import service.userService;
import util.ParseRequest;
import util.ParseResponse;
import service.productService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet(urlPatterns = "/products")
public class ProductsController extends HttpServlet {
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

            ProductModel data = ParseRequest.toObject(request, ProductModel.class);
            List<ProductModel> pro = productService.getOne(productService.ProProp.namePro, data.getNamePro());
            if (pro.isEmpty()){
                ParseResponse.textRes(response, ParseResponse.HttpStatus.NOT_FOUND,"Khong tim thay products");
            }
            boolean isSuccess= productService.deletePro(data);
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

            ProductModel data = ParseRequest.toObject(request, ProductModel.class);
            List<userModel> pro = userService.getById(data.getId());
            if (pro.isEmpty()){
                ParseResponse.textRes(response, ParseResponse.HttpStatus.NOT_FOUND,"Khong tim thay product");
                return;
            }
            boolean isSuccess= productService.updatePro(data);
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
            ProductModel data = ParseRequest.toObject(request, ProductModel.class);
            List<ProductModel> pro = productService.getOne(productService.ProProp.namePro, data.getNamePro());

            if (!pro.isEmpty()){
                ParseResponse.textRes(response, ParseResponse.HttpStatus.INTERNAL_SERVER_ERROR,"product da ton tai");
            }

            boolean isSuccess= productService.createPro(data);
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
    }}