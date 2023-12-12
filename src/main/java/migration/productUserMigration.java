package migration;

public class productUserMigration {
    public static String createTableQuery = "CREATE TABLE IF NOT EXISTS users(" +
            "id INT AUTO_INCREMENT PRIMARY KEY ,"+
            "product_id VARCHAR(255),FOREIGN KEY (user_id) REFERENCES users(id),"+
            "quantity INT NOT NULL ," +
            "user_id VARCHAR(255),FOREIGN KEY (user_id) REFERENCES users(id),"+
