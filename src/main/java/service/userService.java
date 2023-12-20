package service;

import lombok.Data;
import migration.testConnectionDB;
import model.userModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class userService  {


    public enum UserProp{
        username,
        phone,
        email;



    };




   public static ResultSet getOne(UserProp by, String value  ) throws SQLException {
       String findingVal = by.name();
       ResultSet  checkResult = null;
       try {
           String getOneSql = "SELECT * " +
                   " FROM users WHERE " + findingVal + " = ?";
           PreparedStatement stm = testConnectionDB.stm(getOneSql);
           stm.setString(1,value);
           checkResult = stm.executeQuery();

       }catch (SQLException e){
           throw new Error(e.toString());
       }
       return checkResult;
   }

    public static userModel getById( int id  ) throws SQLException {


        userModel user = new userModel();
        try {
            String getOneSql = "SELECT * " +
                    " FROM users WHERE id = ? ";
            PreparedStatement stm = testConnectionDB.stm(getOneSql);
            stm.setInt(1,id);
            ResultSet checkResult = stm.executeQuery();
            while (checkResult.next()){
                user.setName(checkResult.getString("name"));

            }
            checkResult.close();

        }catch (SQLException e){



           throw new Error(e);

        }

        return user;
    }




}
