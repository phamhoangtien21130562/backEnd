package migration;

public class ProductControllMigration {
    public static String createTableQuery = "CREATE TABLE IF NOT EXISTS product_controls(" +
            "Id INT AUTO_INCREMENT PRIMARY KEY ,"+

            "Status int NOT NULL ," +
            "product_id VARCHAR(255),FOREIGN KEY (product_id) REFERENCES products(id),"
            +")";

}


