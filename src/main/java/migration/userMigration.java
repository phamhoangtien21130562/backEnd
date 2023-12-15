package migration;

public class userMigration {
   public static String createTableQuery = "CREATE TABLE IF NOT EXISTS users(" +
            "id INT AUTO_INCREMENT PRIMARY KEY ,"+
            "name VARCHAR(255) NOT NULL ," +
            "email VARCHAR(255) NOT NULL ," +
            "address VARCHAR(255) NOT NULL ," +
            "birthday VARCHAR(255) NOT NULL ," +
            "phone VARCHAR(255) NOT NULL ," +
            "username VARCHAR(255) NOT NULL ," +
            "password VARCHAR(255) NOT NULL ," +
            "role INT NOT NULL ," +
            "image VARCHAR(255) NOT NULL ,"+
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +")";

}


