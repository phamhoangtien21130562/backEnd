package service;

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
        username("username");
        private final String value;
        UserProp(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }



    };

   public static ResultSet getOne(UserProp by, String value  ) throws SQLException {
       String findingVal = by.getValue();
       ResultSet  checkResult = null;
       try {
           String getOneSql = "SELECT " + findingVal +
                   " FROM users WHERE " + findingVal + " = ?";
           PreparedStatement stm = testConnectionDB.stm(getOneSql);
           stm.setString(1,value);
           checkResult = stm.executeQuery();

       }catch (SQLException e){
           throw new Error(e.toString());
       }
       return checkResult;
   }


}
