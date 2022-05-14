package ro.mycode.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private int id;
    private int customerId;
    private double ammount;
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
        return this.id  == order.id;
    }


}
