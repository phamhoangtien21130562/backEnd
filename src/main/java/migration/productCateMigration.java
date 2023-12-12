package migration;

public class productCateMigration {
    public static String createTableQuery = "CREATE TABLE IF NOT EXISTS products_categories(" +
            "id INT AUTO_INCREMENT PRIMARY KEY ,"+
            "product_id VARCHAR(255),FOREIGN KEY (product_id) REFERENCES products(id),"+
            "category_id VARCHAR(255),FOREIGN KEY (category_id) REFERENCES categories(id),"+
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +")";

}
