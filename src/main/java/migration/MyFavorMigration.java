package migration;

public class MyFavorMigration {
    public static String createTableQuery = "CREATE TABLE IF NOT EXISTS product_controls(" +
            "Id INT AUTO_INCREMENT PRIMARY KEY ,"+
            "ProID VARCHAR(255),FOREIGN KEY (product_id) REFERENCES products(id)," +


            "UserId VARCHAR(255),FOREIGN KEY (product_id) REFERENCES User(Id),"
            +")";

}


