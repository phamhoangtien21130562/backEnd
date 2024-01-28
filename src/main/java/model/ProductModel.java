package model;

public class ProductModel {
    private int id;
    private String namePro;
    private double price;
    private String imagePro;
    private int ratingPro;
    private String author;
    private String description;
    private int quantity;

    public ProductModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamePro() {
        return namePro;
    }

    public void setNamePro(String namePro) {
        this.namePro = namePro;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImagePro() {
        return imagePro;
    }

    public void setImagePro(String imagePro) {
        this.imagePro = imagePro;
    }

    public int getRatingPro() {
        return ratingPro;
    }

    public void setRatingPro(int ratingPro) {
        this.ratingPro = ratingPro;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", namePro='" + namePro + '\'' +
                ", price=" + price +
                ", imagePro='" + imagePro + '\'' +
                ", ratingPro=" + ratingPro +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
