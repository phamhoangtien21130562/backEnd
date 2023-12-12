package migration;

public class productOderMigration {
    public static String createTableQuery = "CREATE TABLE IF NOT EXISTS users(" +
            "id INT AUTO_INCREMENT PRIMARY KEY ,"+
            "product_name VARCHAR(255) NOT NULL ," +
            "shopOder_id VARCHAR(255),FOREIGN KEY (user_id) REFERENCES users(id),"+
            "quantity INT NOT NULL ," +
            "price int NOT NULL ," +
