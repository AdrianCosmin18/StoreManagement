package ro.mycode.repositories;

import ro.mycode.modele.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class OrderRepository extends Repository<Order>{

    public OrderRepository(){

        super();
    }

    @Override
    public void insert(Order order) {

        String text = "";
        text += "insert into orders (customer_id, ammount,order_address,order_date) values(";
        text += String.format("%d, %.2f, '%s', '%s'", order.getCustomerId(), order.getAmmount(), order.getOrderAddress(), order.getOrderDate());
        text += ");";

        executeStatement(text);
    }

    @Override
    public void delete(int id) {

        String text = "";
        text += String.format("delete from orders where id = %d ;", id);
        executeStatement(text);
    }

    @Override
    public ResultSet allResultSet() {

        executeStatement("select * from orders");
        try{

            return statement.getResultSet();
        }catch (SQLException e){

            System.out.println("eroare la select * from orders");
            return null;
        }
    }

    @Override
    public List<Order> all() {

        ResultSet set = allResultSet();
        ArrayList<Order> orders = new ArrayList<>();
        try{

            while (set.next()){

                LocalDate localDate = Util.toDate(set.getString(5));
                Order order = new Order(set.getInt(1), set.getInt(2), set.getDouble(3), set.getString(4), localDate);
                orders.add(order);
            }
            return orders;
        }catch (Exception e){

            System.out.println("eroare la crearea listei");
        }

        return null;
    }

    @Override
    public void update(Order order) {

        String text = "";
        text += String.format("update orders set customer_id = %d, ammount = %.2f, order_address = '%s', order_date = '%s' where id = %d", order.getCustomerId(), order.getAmmount(), order.getOrderAddress(), order.getOrderDate(), order.getId());
        executeStatement(text);
    }

    @Override
    public boolean contains(Order order) {

        String text = String.format("select * from orders where id = %d", order.getId());
        executeStatement(text);
        try{
            ResultSet set = statement.getResultSet();
            if (set.next()){

                LocalDate localDate = Util.toDate(set.getString(5));
                Order order2 = new Order(set.getInt(1), set.getInt(2), set.getDouble(3), set.getString(4), localDate);
                return order2.equals(order);
            }

        }catch (Exception e){

            System.out.println("eroare la contains");
            return false;
        }
        return false;
    }

    public Order getOrderByCustomerIdAndDateAndAmmount0(int customerID, String date){

        String text = "";
        text += String.format("select * from orders where customer_id = %d and order_date = '%s' and ammount = 0;", customerID, date);

        executeStatement(text);
        try{

            ResultSet set = statement.getResultSet();
            if(set.next()){

                LocalDate date2 = Util.toDate(set.getString(5));
                Order order = new Order(set.getInt(1), set.getInt(2), set.getDouble(3), set.getString(4), date2);
                return order;
            }

        }catch (Exception e){

            System.out.println("Eroare la getOrderByCustomerIdAndDate");
        }
        return null;
    }

    public Order getOrderByMaxOrderDate(){

        String text = "select id, customer_id, ammount, order_address, max(order_date) from orders;";
        try{

            executeStatement(text);
            ResultSet set = statement.getResultSet();
            if(set.next()){

                LocalDate localDate = Util.toDate(set.getString(5));
                Order o = new Order(set.getInt(1), set.getInt(2), set.getDouble(3), set.getString(4), localDate);
                return o;
            }


        }catch(Exception e){

            System.out.println("eroare la " + text);
        }

        return null;
    }

}
