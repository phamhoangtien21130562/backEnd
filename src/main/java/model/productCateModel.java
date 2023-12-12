package model;

public class productCateModel {
    private int productId;
    private int categoryId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public productCateModel(int productId, int categoryId) {
        this.productId = productId;
        this.categoryId = categoryId;
    }
}
