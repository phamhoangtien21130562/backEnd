package migration;

public class ProductMigration {
    public static String createTableQuery = "CREATE TABLE IF NOT EXISTS products(" +
            "id INT AUTO_INCREMENT PRIMARY KEY ,"+
            "namePro NVARCHAR(255) NOT NULL ," +
            "price double NOT NULL ," +
            "ratingPro int NOT NULL ," +
            "author NVARCHAR(255) NOT NULL ," +
            "description NVARCHAR(255) NOT NULL ," +
            "quantity int NOT NULL " +")";


}


