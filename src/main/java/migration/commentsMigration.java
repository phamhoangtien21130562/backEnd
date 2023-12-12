package migration;

public class commentsMigration {
    public static String createTableQuery = "CREATE TABLE IF NOT EXISTS comments(" +
            "id INT AUTO_INCREMENT PRIMARY KEY ,"+
            "name VARCHAR(255) NOT NULL ," +
            "content VARCHAR(255) NOT NULL ," +
            "rating VARCHAR(255) NOT NULL ," +
            "product_id VARCHAR(255),FOREIGN KEY (product_id) REFERENCES products(id),"+
            "user_id VARCHAR(255),FOREIGN KEY (user_id) REFERENCES users(id),"+
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +")";

}

}
