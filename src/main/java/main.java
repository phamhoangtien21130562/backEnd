import migration.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class main {
    public static void main(String[] args) {
        try {
            Connection conn = testConnectionDB.Dbcontext();
            System.out.println("Connected");
            Statement statement = conn.createStatement();
            String userQuery = userMigration.createTableQuery;
            String commentsQuery = commentsMigration.createTableQuery;
            String categoriesQuery = categoriesMigration.createTableQuery;
            String productCateQuery = productCateMigration.createTableQuery;
            String productsQuery = ProductMigration.createTableQuery;
            statement.executeUpdate(productsQuery);
            System.out.println("sucessfully created products table");
            statement.executeUpdate(userQuery);
            System.out.println("sucessfully created users table");
            statement.executeUpdate(commentsQuery);
            System.out.println("sucessfully created comments table");
            statement.executeUpdate(categoriesQuery);
            System.out.println("sucessfully created categories table");
            statement.executeUpdate(productCateQuery);
            System.out.println("sucessfully created products_categories table");
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();

        }

    }
}
