package migration;

public class ProductMigration {
    public static String createTableQuery = "CREATE TABLE IF NOT EXISTS products(" +
            "Id INT AUTO_INCREMENT PRIMARY KEY ,"+
            "NamePro NVARCHAR(255) NOT NULL ," +
            "Price int NOT NULL ," +
            "RatingPro int NOT NULL ," +
            "Author NVARCHAR(255) NOT NULL ," +
            "Description NVARCHAR(255) NOT NULL ," +
            "Quantity int NOT NULL ," +")";


}


