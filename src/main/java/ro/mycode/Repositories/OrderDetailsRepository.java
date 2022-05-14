package ro.mycode.Repositories;

import ro.mycode.classes.Order;
import ro.mycode.classes.OrderDetails;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsRepository extends Repository<OrderDetails> {

    public OrderDetailsRepository(){

        super();
    }

    @Override
    protected void insert(OrderDetails orderDetails) {

        String text = "";
        text += "insert into order_details (order_id, product_id, price ,quantity) values(";
        text += String.format("%d, %d, %.2f, %d", orderDetails.getOrderId(), orderDetails.getProductId(), orderDetails.getPrice(), orderDetails.getQuantity());
        text += ");";

        executeStatement(text);
    }

    @Override
    protected void delete(int id) {

        String text = "";
        text += String.format("delete from order_details where id = %d ;", id);
        executeStatement(text);
    }

    @Override
    protected ResultSet allResultSet() {

        String text = "select * from order_details;";
        executeStatement(text);
        try{

            return statement.getResultSet();
        }catch (Exception e){

            System.out.println("eroare la allResultSet");
            return null;
        }
    }

    @Override
    protected List<OrderDetails> all() {

        ResultSet set = allResultSet();
        try{

            List<OrderDetails> details = new ArrayList<>();
            while (set.next()){

                OrderDetails detail = new OrderDetails(set.getInt(1), set.getInt(2), set.getInt(3), set.getDouble(4), set.getInt(5));
                details.add(detail);
            }
            return details;
        }catch (Exception e){

            System.out.println("eroare la all");
            return null;
        }
    }

    @Override
    protected void update(OrderDetails orderDetails) {

        String text = "";
        text += String.format("update order_details set order_id = %d, product_id = %d, price = %.2f, quantity = %d where id = %d", orderDetails.getOrderId(), orderDetails.getProductId(), orderDetails.getPrice(), orderDetails.getQuantity(), orderDetails.getId());
        executeStatement(text);
    }

    @Override
    protected boolean contains(OrderDetails orderDetails) {

        String text = String.format("select * from order_DETAILS where id = %d", orderDetails.getId());
        executeStatement(text);
        try{
            ResultSet set = statement.getResultSet();
            if (set.next()){

                OrderDetails orderDetails1 = new OrderDetails(set.getInt(1), set.getInt(2), set.getInt(3), set.getDouble(4), set.getInt(5));
                return orderDetails1.equals(orderDetails);
            }

        }catch (Exception e){

            System.out.println("eroare la contains");
            return false;
        }
        return false;
    }
}
