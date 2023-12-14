package model;

public class MyFavorModel {
    private String Id;
    private String ProID;
    private String UserId;

    public MyFavorModel() {
    }

    public MyFavorModel(String id, String proID, String userId) {
        this.Id = id;
        this.ProID = proID;
        this.UserId = userId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getProID() {
        return ProID;
    }

    public void setProID(String proID) {
        ProID = proID;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    @Override
    public String toString() {
        return "MyFavorModel{" +
                "Id='" + Id + '\'' +
                ", ProID='" + ProID + '\'' +
                ", UserId='" + UserId + '\'' +
                '}';
    }
}
