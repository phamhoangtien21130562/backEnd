package model;

public class ProductControllerModel {
    private int Status;
    private String Id;
    private int ProductId;

    public ProductControllerModel() {
    }

    public ProductControllerModel(int status, String id, int productId) {
        Status = status;
        Id = id;
        ProductId = productId;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    @Override
    public String toString() {
        return "ProductControllerModel{" +
                "Status=" + Status +
                ", Id='" + Id + '\'' +
                ", ProductId=" + ProductId +
                '}';
    }
}
