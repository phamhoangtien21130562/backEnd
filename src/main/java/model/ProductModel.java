package model;

public class ProductModel {
    private String Id;
    private String NamePro;
    private double price;
    private String ImagePro;
    private int RatingPro;
    private String Author;
    private String Description;
    private int Quantity;

    public ProductModel() {
    }

    public ProductModel(String id, String namePro, double price, String imagePro, int ratingPro, String author, String description, int quantity) {
        Id = id;
        NamePro = namePro;
        this.price = price;
        ImagePro = imagePro;
        RatingPro = ratingPro;
        Author = author;
        Description = description;
        Quantity = quantity;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNamePro() {
        return NamePro;
    }

    public void setNamePro(String namePro) {
        NamePro = namePro;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImagePro() {
        return ImagePro;
    }

    public void setImagePro(String imagePro) {
        ImagePro = imagePro;
    }

    public int getRatingPro() {
        return RatingPro;
    }

    public void setRatingPro(int ratingPro) {
        RatingPro = ratingPro;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "Id='" + Id + '\'' +
                ", NamePro='" + NamePro + '\'' +
                ", price=" + price +
                ", ImagePro='" + ImagePro + '\'' +
                ", RatingPro=" + RatingPro +
                ", Author='" + Author + '\'' +
                ", Description='" + Description + '\'' +
                ", Quantity=" + Quantity +
                '}';
    }
}
