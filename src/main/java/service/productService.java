package service;
import lombok.Data;
import migration.testConnectionDB;
import model.ProductModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class  productService  {


    public enum ProProp{
        namePro,
        image,
        author;



    };




    public static List<ProductModel> getOne(ProProp by, String value  ) throws SQLException {
        ProductModel pro =new ProductModel();
        String findingVal = by.name();
        ResultSet  checkResult = null;
        try {
            String getOneSql = "SELECT * " +
                    " FROM products WHERE " + findingVal + " = ?";
            PreparedStatement stm = testConnectionDB.stm(getOneSql);
            stm.setString(1,value);
            checkResult = stm.executeQuery();


        }catch (SQLException e){
            throw new Error(e.toString());
        }
        return  um(checkResult);
    }
    public static ProductModel getUpdate(ProProp by, String value  ) throws SQLException {
        ProductModel pro =new ProductModel();
        ResultSet  checkResult = null;
        try {
            String sqlUD = "UPDATE products SET namePro=?, price=? ,imagePro=? , rating=?, author=?, description=?,quantity=? WHERE id=?";
            PreparedStatement stm = testConnectionDB.stm(sqlUD);
            stm.setString(1,value);
            checkResult = stm.executeQuery();
            um(checkResult);

        }catch (SQLException e){
            throw new Error(e.toString());
        }
        return pro;
    }
    public static ProductModel getDelete(ProProp by, String value  ) throws SQLException {
        ProductModel pro =new ProductModel();
        ResultSet  checkResult = null;
        try {
            String sqlDel ="DELETE from products WHERE id=?";
            PreparedStatement stm = testConnectionDB.stm(sqlDel);
            stm.setString(1,value);
            checkResult = stm.executeQuery();
            um(checkResult);

        }catch (SQLException e){
            throw new Error(e.toString());
        }
        return pro;
    }
    public static ProductModel getInsert(ProProp by, String value  ) throws SQLException {
        ProductModel pro =new ProductModel();
        ResultSet  checkResult = null;
        try {
            String sqlInsert = "INSERT INTO product(namePro,price,imagePro, rating, author, description, quantity " +
                    "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement stm = testConnectionDB.stm(sqlInsert);
            stm.setString(1,value);
            checkResult = stm.executeQuery();
            um(checkResult);

        }catch (SQLException e){
            throw new Error(e.toString());
        }
        return pro;
    }

    public static List<ProductModel> getById( int id  ) throws SQLException {
        ProductModel pro =new ProductModel();
        ResultSet checkResult = null;
        try {
            String getOneSql = "SELECT * " +
                    " FROM products WHERE id = ? ";
            PreparedStatement stm = testConnectionDB.stm(getOneSql);
            stm.setInt(1,id);
            checkResult = stm.executeQuery();






        }catch (SQLException e){



            throw new Error(e);

        }

        return um(checkResult);
    }
    public static List<ProductModel> getAll( ) throws SQLException {


        ResultSet checkResult = null;
        try {
            String getOneSql = "SELECT * FROM products ";
            PreparedStatement stm = testConnectionDB.stm(getOneSql);
            checkResult = stm.executeQuery();
        }catch (SQLException e){

            throw new Error(e);

        }

        return um(checkResult);
    }
    public static List<ProductModel> um(ResultSet resultSet) {
        List<ProductModel> Pros = new ArrayList<>();
        try {
            while(resultSet.next()) {
                // Chuyển đổi ResultSet thành đối tượng UserModel
                ProductModel pro =new ProductModel();
                pro.setId(resultSet.getInt("id"));
                try {
                    pro.setNamePro(resultSet.getString("namePro"));
                    pro.setPrice(resultSet.getDouble("price"));
                    pro.setImagePro(resultSet.getString("imagePro"));
                    pro.setRatingPro(resultSet.getInt("rating"));
                    pro.setAuthor(resultSet.getString("author"));
                    pro.setDescription(resultSet.getString("description"));
                    pro.setQuantity(resultSet.getInt("quantity"));

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            };
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Pros;
    }
    public static boolean createPro(ProductModel data) throws SQLException {
        boolean isSuccess = false;
        try {
            String sqlInsert = "INSERT INTO products (namePro, price, imagePro, rating, author, description, quantity) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = testConnectionDB.stm(sqlInsert);
            stm.setString(1, data.getNamePro());
            stm.setDouble(2, data.getPrice());
            stm.setString(3, data.getImagePro());
            stm.setInt(4, data.getRatingPro());
            stm.setString(5, data.getAuthor());
            stm.setString(6, data.getDescription());
            stm.setInt(7, data.getQuantity());

            int rowexc = stm.executeUpdate();
            ResultSet generatedKey = stm.getGeneratedKeys();
            int generatedID = -1;
            if (generatedKey.next()) {
                generatedID = generatedKey.getInt(1);
                data.setId(generatedID);
            }
            if (generatedID == -1) {
                isSuccess = false;
                stm.close();
                generatedKey.close();
                throw new SQLException(new Throwable());

            }
            stm.close();
            generatedKey.close();
            isSuccess = true;
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return isSuccess;
    }

    public static boolean updatePro(ProductModel data){
        String sqlUD = "UPDATE users SET image=?, phone=?, email=?, address=?, username=?, password=?, birthday=?, role=?, name=? WHERE id=?";
        boolean isSuccess = false;
        try (PreparedStatement statement = testConnectionDB.stm(sqlUD)) {
            statement.setString(1, data.getNamePro());
            statement.setDouble(2, data.getPrice());
            statement.setString(3, data.getImagePro());
            statement.setInt(4, data.getRatingPro());
            statement.setString(5, data.getAuthor());
            statement.setString(6, data.getDescription());
            statement.setInt(7, data.getQuantity());

            statement.setInt(8, data.getId()); // Update id nhập vào "Where id"

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
    public static boolean deletePro(ProductModel data) {
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
