package model;

public class productUserCateModel {
    private int id;
    private String product_id;
    private int quantity;
    private String user_id;

    public productUserCateModel(int id, String product_id, int quantity, String user_id) {
        this.id = id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.user_id = user_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "productUserCateModel{" +
                "id=" + id +
                ", product_id='" + product_id + '\'' +
                ", quantity=" + quantity +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUser_id() {
        return user_id;
    }
}