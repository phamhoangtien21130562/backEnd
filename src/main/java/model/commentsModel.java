package model;

public class commentsModel {
    private int id;
    private String name;
    private String content;
    private String rating;
    private int productId;
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public commentsModel(int id, String name, String content, String rating, int productId, int userId) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.rating = rating;
        this.productId = productId;
        this.userId = userId;
    }
}
