package model;

public class commentsModel {
    private String id;
    private String name;
    private String content;
    private String rating;
    private String product_id;
    private String user_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public commentsModel(String id, String name, String content, String rating, String product_id, String user_id) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.rating = rating;
        this.product_id = product_id;
        this.user_id = user_id;
    }
}
