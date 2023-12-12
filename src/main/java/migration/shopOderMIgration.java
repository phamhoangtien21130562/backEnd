package migration;

public class shopOderMigration {
    public static String createTableQuery = "CREATE TABLE IF NOT EXISTS users(" +
            "id INT AUTO_INCREMENT PRIMARY KEY ,"+
            "ship_id INT NOT NULL ," +
            "price_total INT NOT NULL ," +
            "date_oder VARCHAR(255) NOT NULL ," +
            "user_id VARCHAR(255),FOREIGN KEY (user_id) REFERENCES users(id),"+
            "address_oder VARCHAR(255) NOT NULL ," +
            "status VARCHAR(255) NOT NULL ," +
            "date_ship VARCHAR(255) NOT NULL ," +
            "date_pay VARCHAR(255) NOT NULL ," +
            "customer_name VARCHAR(255) NOT NULL ," +
            "customer_phone VARCHAR(255) NOT NULL ," +
            "methord_pay VARCHAR(255) NOT NULL ," +
            "note VARCHAR(255) NOT NULL ," +
}


