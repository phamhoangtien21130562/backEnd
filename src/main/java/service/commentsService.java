package service;

import migration.testConnectionDB;
import model.commentsModel;
import model.userModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class commentsService {
    public enum CommentsProp {
        userId,
        productId,
        name;


    }

    ;

    public static commentsModel getOne(commentsService.CommentsProp by, String value) throws SQLException {
        commentsModel cmt = new commentsModel();
        String findingVal = by.name();
        ResultSet checkResult = null;
        try {
            String getOneSql = "SELECT * " +
                    " FROM users WHERE " + findingVal + " = ?";
            PreparedStatement stm = testConnectionDB.stm(getOneSql);
            stm.setString(1, value);
            checkResult = stm.executeQuery();
            um(checkResult);

        } catch (SQLException e) {
            throw new Error(e.toString());
        }
        return cmt;
    }
    public static List<commentsModel> getAll( ) throws SQLException {


        ResultSet checkResult = null;
        try {
            String getOneSql = "SELECT * FROM comments ";
            PreparedStatement stm = testConnectionDB.stm(getOneSql);
            checkResult = stm.executeQuery();

        }catch (SQLException e){

            throw new Error(e);

        }

        return um(checkResult);
    }

    public static List<commentsModel> um(ResultSet resultSet) {
            List<commentsModel> cmts= null;

        try {
           while(resultSet.next() ) {
                // Chuyển đổi ResultSet thành đối tượng UserModel
               commentsModel cmt =new commentsModel();
                try {
                    cmt.setName(resultSet.getString("name"));
                    cmt.setContent(resultSet.getString("content"));
                    cmt.setRating(resultSet.getString("rating"));
                    cmts.add(cmt);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cmts;
    }

    public static boolean updateCmt(commentsModel data) {
        boolean isSuccess = false;
        String sqlUD = "UPDATE comments SET content=?, rating=? WHERE id=?";

        try (PreparedStatement statement = testConnectionDB.stm(sqlUD)) {
            statement.setString(1, data.getContent());
            statement.setString(2, data.getRating());
            statement.setInt(3, data.getId()); // Update id nhập vào "Where id"
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
    public static boolean deleteCmt(commentsModel data){
        boolean isSuccess = false;
        String sqlDel = "DELETE FROM comments WHERE id=?";
        try (PreparedStatement statement = testConnectionDB.stm(sqlDel)){
            statement.setInt(1,data.getId());
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
    public static boolean createCmt(commentsModel data){
        boolean isSuccess = false;
        String sql = "INSERT INTO comments(name, content, rating) " +
                "VALUES(?,?,?)";
        try(PreparedStatement statement = testConnectionDB.stm(sql);) {
            statement.setString(1,data.getName());
            statement.setString(2,data.getContent());
            statement.setString(3,data.getRating());



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
        }catch (SQLException e){
            e.printStackTrace();
        }
        return isSuccess;
    }
}
