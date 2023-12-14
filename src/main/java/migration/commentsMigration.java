package migration;

public class commentsMigration {
    public static String createTableQuery = "CREATE TABLE IF NOT EXISTS comments(" +
            "id INT AUTO_INCREMENT PRIMARY KEY ,"+
            "name VARCHAR(255) NOT NULL ," +
            "content VARCHAR(255) NOT NULL ," +
            "rating VARCHAR(255) NOT NULL ," +
            "productId INT,FOREIGN KEY (productId) REFERENCES products(Id),"+
            "userId INT,FOREIGN KEY (userId) REFERENCES users(id),"+
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +")";

}


