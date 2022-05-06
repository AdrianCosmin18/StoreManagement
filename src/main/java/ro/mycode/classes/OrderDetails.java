package ro.mycode.classes;



public class OrderDetails {

    private int id;
    private int orderId;
    private int productId;
    private double price;
    private int quantity;

    public OrderDetails(int orderId, int productId, double price, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }
}
