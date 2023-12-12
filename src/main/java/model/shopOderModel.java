package model;
public class shopOderModel {
    private int id;
    private int ship_id;
    private int price_total;
    private String date_oder;
    private String user_id;
    private String address_oder;
    private String status;
    private String date_ship;
    private String date_pay;
    private String customer_name;
    private String customer_phone;
    private String method_pay;
    private String note;

    public shopOderModel(int id, int ship_id, int price_total, String date_oder, String user_id, String address_oder, String status, String date_ship, String date_pay, String customer_name, String customer_phone, String method_pay, String note) {
        this.id = id;
        this.ship_id = ship_id;
        this.price_total = price_total;
        this.date_oder = date_oder;
        this.user_id = user_id;
        this.address_oder = address_oder;
        this.status = status;
        this.date_ship = date_ship;
        this.date_pay = date_pay;
        this.customer_name = customer_name;
        this.customer_phone = customer_phone;
        this.method_pay = method_pay;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public int getShip_id() {
        return ship_id;
    }

    public int getPrice_total() {
        return price_total;
    }

    public String getDate_oder() {
        return date_oder;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getAddress_oder() {
        return address_oder;
    }

    public String getStatus() {
        return status;
    }

    public String getDate_ship() {
        return date_ship;
    }

    public String getDate_pay() {
        return date_pay;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public String getMethod_pay() {
        return method_pay;
    }

    public String getNote() {
        return note;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setShip_id(int ship_id) {
        this.ship_id = ship_id;
    }

    public void setPrice_total(int price_total) {
        this.price_total = price_total;
    }

    public void setDate_oder(String date_oder) {
        this.date_oder = date_oder;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setAddress_oder(String address_oder) {
        this.address_oder = address_oder;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate_ship(String date_ship) {
        this.date_ship = date_ship;
    }

    public void setDate_pay(String date_pay) {
        this.date_pay = date_pay;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public void setMethod_pay(String method_pay) {
        this.method_pay = method_pay;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "shopOderModel{" +
                "id=" + id +
                ", ship_id=" + ship_id +
                ", price_total=" + price_total +
                ", date_oder='" + date_oder + '\'' +
                ", user_id='" + user_id + '\'' +
                ", address_oder='" + address_oder + '\'' +
                ", status='" + status + '\'' +
                ", date_ship='" + date_ship + '\'' +
                ", date_pay='" + date_pay + '\'' +
                ", customer_name='" + customer_name + '\'' +
                ", customer_phone='" + customer_phone + '\'' +
                ", method_pay='" + method_pay + '\'' +
                ", note='" + note + '\'' +
                '}';
    }