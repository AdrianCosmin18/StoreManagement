package ro.mycode.classes;

import java.time.LocalDateTime;

public class Order {

    private int id;
    private int customerId;
    private int ammount;
    private String orderAddress;
    private LocalDateTime orderDate;


    public Order(int customerId, int ammount, String orderAddress, LocalDateTime orderDate) {
        this.customerId = customerId;
        this.ammount = ammount;
        this.orderAddress = orderAddress;
        this.orderDate = orderDate;
    }
}
