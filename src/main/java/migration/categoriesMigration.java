package migration;

public class categoriesMigration {
    public static String createTableQuery = "CREATE TABLE IF NOT EXISTS categories(" +
            "id INT AUTO_INCREMENT PRIMARY KEY ,"+
            "name VARCHAR(255) NOT NULL ," +
            "description VARCHAR(255) NOT NULL ," +
            "isActive VARCHAR(255) NOT NULL ," +
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +")";

}
