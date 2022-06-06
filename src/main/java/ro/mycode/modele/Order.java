package ro.mycode.modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private int id;
    private int customerId;//price
    private double ammount;//customerID
    private String orderAddress;
    private LocalDate orderDate;


    public Order(int customerId, double ammount, String orderAddress, LocalDate orderDate) {
        this.customerId = customerId;
        this.ammount = ammount;
        this.orderAddress = orderAddress;
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o){

        Order order = (Order) o;
        return this.customerId==order.getCustomerId()&&this.orderDate.equals(order.getOrderDate());
    }

    @Override
    public String toString(){

        String text = "";
        text += "\nID: " + id;
        text += "\nCustomer id: " + customerId;
        text += "\nAmmount: " + ammount;
        text += "\nOrder address: " +orderAddress;
        text += "\nOrder date: " + orderDate;

        return text;
    }


}
