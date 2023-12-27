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




    public static List<userModel> getOne(UserProp by, String value  ) throws SQLException {
        userModel user=new userModel();
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
        return  um(checkResult);
    }
    public static userModel getUpdate(UserProp by, String value  ) throws SQLException {
        userModel user=new userModel();
        ResultSet  checkResult = null;
        try {
            String sqlUD = "UPDATE users SET image=?, phone=?, email=?, address=?, username=?, password=?, birthday=?, role=?, name=? WHERE id=?";
            PreparedStatement stm = testConnectionDB.stm(sqlUD);
            stm.setString(1,value);
            checkResult = stm.executeQuery();
            um(checkResult);

        }catch (SQLException e){
            throw new Error(e.toString());
        }
        return user;
    }
    public static userModel getDelete(UserProp by, String value  ) throws SQLException {
        userModel user=new userModel();
        ResultSet  checkResult = null;
        try {
            String sqlDel ="DELETE from users WHERE id=?";
            PreparedStatement stm = testConnectionDB.stm(sqlDel);
            stm.setString(1,value);
            checkResult = stm.executeQuery();
            um(checkResult);

        }catch (SQLException e){
            throw new Error(e.toString());
        }
        return user;
    }
    public static userModel getInsert(UserProp by, String value  ) throws SQLException {
        userModel user=new userModel();
        ResultSet  checkResult = null;
        try {
            String sqlInsert = "INSERT INTO users(image, phone, email, address,username, password, birthday,role,name) " +
                    "VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement stm = testConnectionDB.stm(sqlInsert);
            stm.setString(1,value);
            checkResult = stm.executeQuery();
            um(checkResult);

        }catch (SQLException e){
            throw new Error(e.toString());
        }
        return user;
    }

    public static List<userModel> getById( int id  ) throws SQLException {
        userModel user = new userModel();
        ResultSet checkResult = null;
        try {
            String getOneSql = "SELECT * " +
                    " FROM users WHERE id = ? ";
            PreparedStatement stm = testConnectionDB.stm(getOneSql);
            stm.setInt(1,id);
           checkResult = stm.executeQuery();






        }catch (SQLException e){



            throw new Error(e);

        }

        return um(checkResult);
    }
    public static List<userModel> getAll( ) throws SQLException {


        ResultSet checkResult = null;
        try {
            String getOneSql = "SELECT * FROM users ";
            PreparedStatement stm = testConnectionDB.stm(getOneSql);
            checkResult = stm.executeQuery();
        }catch (SQLException e){

            throw new Error(e);

        }

        return um(checkResult);
    }
    public static List<userModel> um(ResultSet resultSet) {
        List<userModel> users = new ArrayList<>();
        try {
             while(resultSet.next()) {
                // Chuyển đổi ResultSet thành đối tượng UserModel
                 userModel user = new userModel();
                user.setId(resultSet.getInt("id"));
                try {
                    user.setName(resultSet.getString("name"));
                    user.setAddress(resultSet.getString("address"));
                    user.setBirthday(resultSet.getString("birthday"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setRole(resultSet.getInt("role"));
                    user.setImage(resultSet.getString("image"));
                    user.setUsername(resultSet.getString("username"));
                    users.add(user);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            };
             resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
    public static boolean createUser(userModel data) {
        String sql = "INSERT INTO users(image, phone, email, address,username, password, birthday,role,name) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        boolean isSuccess = false;
        PreparedStatement statement = null;
        try {
            statement = testConnectionDB.stm(sql);

            statement.setString(1, data.getImage());
            statement.setString(2, data.getPhone());
            statement.setString(3, data.getEmail());
            statement.setString(4, data.getAddress());
            statement.setString(5, data.getUsername());
            statement.setString(6, data.getPassword());
            statement.setString(7, data.getBirthday());
            statement.setInt(8, data.getRole());
            statement.setString(9, data.getName());

            int rowexc = statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            int generatedID = -1;
            if (generatedKey.next()) {
                generatedID = generatedKey.getInt(1);
                data.setId(generatedID);
            }
            if (generatedID == -1) {
                isSuccess = false;
                statement.close();
                generatedKey.close();
                throw new SQLException(new Throwable());

            }
            statement.close();
            generatedKey.close();
            isSuccess = true;
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return isSuccess;
    }
    public static boolean updateUser(userModel data){
        String sqlUD = "UPDATE users SET image=?, phone=?, email=?, address=?, username=?, password=?, birthday=?, role=?, name=? WHERE id=?";
        boolean isSuccess = false;
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
                statement.close();
                isSuccess = true;

            } else {
                isSuccess = false;
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
    public static boolean deleteUser(userModel data) {
        String sqlDel = "DELETE from users WHERE id=?";
        boolean isSuccess = false;
        try (PreparedStatement statement = testConnectionDB.stm(sqlDel)) {
            statement.setInt(1, data.getId());
            int rowexc = statement.executeUpdate();
            if (rowexc > 0) {
                isSuccess = true;
                statement.close();
            } else {
                isSuccess = false;
                statement.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }



}