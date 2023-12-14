package migration;

public class productCateMigration {
    public static String createTableQuery = "CREATE TABLE IF NOT EXISTS products_categories(" +
            "id INT AUTO_INCREMENT PRIMARY KEY ,"+
            "productId INT,FOREIGN KEY (productId) REFERENCES products(id),"+
            "categoryId INT,FOREIGN KEY (categoryId) REFERENCES categories(id),"+
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +")";

}
