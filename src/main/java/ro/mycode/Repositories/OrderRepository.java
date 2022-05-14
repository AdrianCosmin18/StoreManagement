package ro.mycode.Repositories;

import ro.mycode.classes.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class OrderRepository extends Repository<Order>{

    public OrderRepository(){

        super();
    }

    @Override
    protected void insert(Order order) {

        String text = "";
        text += "insert into orders (customer_id, ammount,order_address,order_date) values(";
        text += String.format("%d, %.2f, '%s', '%s'", order.getCustomerId(), order.getAmmount(), order.getOrderAddress(), order.getOrderDate());
        text += ");";

        executeStatement(text);
    }

    @Override
    protected void delete(int id) {

        String text = "";
        text += String.format("delete from orders where id = %d ;", id);
        executeStatement(text);
    }

    @Override
    protected ResultSet allResultSet() {

        executeStatement("select * from orders");
        try{

            return statement.getResultSet();
        }catch (SQLException e){

            System.out.println("eroare la select * from orders");
            return null;
        }
    }

    @Override
    protected List<Order> all() {

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
    protected void update(Order order) {

        String text = "";
        text += String.format("update orders set customer_id = %d, ammount = %.2f, order_address = '%s', order_date = '%s' where id = %d", order.getCustomerId(), order.getAmmount(), order.getOrderAddress(), order.getOrderDate(), order.getId());
        executeStatement(text);
    }

    @Override
    protected boolean contains(Order order) {

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
}
