package ro.mycode.modele;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Override
    public boolean equals(Object o){

        OrderDetails orderDetails = (OrderDetails) o;
        return this.id == orderDetails.id;
    }
}
