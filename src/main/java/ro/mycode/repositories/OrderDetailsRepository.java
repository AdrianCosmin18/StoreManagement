package ro.mycode.repositories;

import ro.mycode.modele.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderDetailsRepository extends Repository<OrderDetails> {

    public OrderDetailsRepository(){

        super();
    }

    @Override
    public void insert(OrderDetails orderDetails) {

        String text = "";
        text += "insert into order_details (order_id, product_id, price ,quantity) values(";
        text += String.format("%d, %d, %.2f, %d", orderDetails.getOrderId(), orderDetails.getProductId(), orderDetails.getPrice(), orderDetails.getQuantity());
        text += ");";

        executeStatement(text);
    }

    @Override
    public void delete(int id) {

        String text = "";
        text += String.format("delete from order_details where id = %d ;", id);
        executeStatement(text);
    }

    @Override
    public ResultSet allResultSet() {

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
    public List<OrderDetails> all() {

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
    public void update(OrderDetails orderDetails) {

        String text = "";
        text += String.format("update order_details set order_id = %d, product_id = %d, price = %.2f, quantity = %d where id = %d", orderDetails.getOrderId(), orderDetails.getProductId(), orderDetails.getPrice(), orderDetails.getQuantity(), orderDetails.getId());
        executeStatement(text);
    }

    @Override
    public boolean contains(OrderDetails orderDetails) {

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

    public List<OrderDetails> getOrderDetailsByOrderId(int orderID){

        String text = "";
        text += String.format("select * from order_details where order_id = %d;", orderID);
        executeStatement(text);

        try {
            ResultSet set = statement.getResultSet();
            List<OrderDetails> ordersDetails = new ArrayList<>();

            while(set.next()){

                OrderDetails orderDetail = new OrderDetails(set.getInt(1), set.getInt(2), set.getInt(3), set.getDouble(4), set.getInt(5));
                ordersDetails.add(orderDetail);
            }
            return ordersDetails;

        }catch (SQLException e){

            System.out.println("eroare la getOrderDetailsByOrderId");
        }
        return null;
    }

    public void deleteByOrderIdAndProductID(int orderId, int productId){

        String text = String.format("delete from order_details where order_id = %d and product_id = %d ;", orderId, productId);
        executeStatement(text);
    }

    public void deleteByOrderId(int orderID){

        String text = String.format("delete from order_details where order_id = %d ;", orderID);
        executeStatement(text);
    }

    public HashMap<Integer, Integer> getProductIdAndQuantity(){

        String text = String.format("select product_id, quantity from order_details;");

        try{

            executeStatement(text);
            ResultSet set = statement.getResultSet();

            HashMap<Integer, Integer> map = new HashMap<>();
            while(set.next()){

                int productId = set.getInt(1);
                int quantity = set.getInt(2);

                if (map.containsKey(productId)){

                    map.put(productId, map.get(productId) + quantity);
                }
                else{

                    map.put(productId, quantity);
                }
            }

            return map;

        }catch (Exception e){

            System.out.println("eroare la " + text);
            return null;
        }
    }
}
