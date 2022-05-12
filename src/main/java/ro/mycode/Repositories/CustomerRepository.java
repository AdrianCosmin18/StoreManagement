package ro.mycode.Repositories;

import ro.mycode.classes.Customer;
import ro.mycode.classes.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository extends Repository<Customer>{

    public CustomerRepository(){

        super();
    }

    @Override
    protected void insert(Customer customer){

        String text = "";
        text += "insert into customers (email,password,full_name,billing_address,phone) values(";
        text += String.format("'%s', '%s', '%s', '%s', '%s'", customer.getEmail(), customer.getPassword(), customer.getFullName(), customer.getBillingAddress(), customer.getPhone());
        text += ");";

        executeStatement(text);
    }

    @Override
    protected void delete(int id) {

        String text = "";
        text += String.format("delete from customers where id = %d ;", id);
        executeStatement(text);
    }

    @Override
    protected ResultSet allResultSet() {

        String text = "";
        text += "select * from customers;";
        executeStatement(text);

        try{
            return statement.getResultSet();

        }catch (SQLException e){

            System.out.println("eroare la select * from customers");
            return null;
        }
    }

    @Override
    protected List<Customer> all() {

        ResultSet set = allResultSet();
        List<Customer> customers = new ArrayList<>();
        try{
            while(set.next()){

                Customer customer = new Customer(set.getInt(1), set.getString(2), set.getString(3), set.getString(4), set.getString(5), set.getString(6));
                customers.add(customer);
            }

            return customers;
        }catch (SQLException e){

            System.out.println("eroare la all()");
            return null;
        }
    }

    @Override
    protected void update(Customer customer) {

        String text = "";
        text += String.format("update customers set email = '%s', password = '%s', full_name = '%s', billing_address = '%s', phone = '%s' where id = %d ;", customer.getEmail(), customer.getPassword(), customer.getFullName(), customer.getBillingAddress(), customer.getPhone(), customer.getId());
        executeStatement(text);
    }

    @Override
    protected boolean contains(Customer customer) {

        String text = "";
        text += String.format("select * from customers where id = %d ;", customer.getId());
        executeStatement(text);

        try {
            ResultSet set = statement.getResultSet();
            if(set.next()){

                Customer c = new Customer(set.getInt(1), set.getString(2), set.getString(3), set.getString(4), set.getString(5), set.getString(6));
                return c.equals(customer);
            }

            return false;
        }catch (SQLException e){

            System.out.println("eroare la contains");
            return false;
        }
    }

    protected int getIdByName(String name){

        String text = "";
        text += String.format("select id from customers where full_name = '%s'", name);
        executeStatement(text);

        try {
            ResultSet set = statement.getResultSet();
            if(set.next()){

                return set.getInt(1);
            }

        }catch (SQLException e){

            System.out.println("eroare la getIdByName");
            return -1;
        }

        return -1;
    }

    protected Customer getCustomerByID(int id){

        String text = "";
        text += String.format("select * from customers where id = %d;", id);
        executeStatement(text);

        try{

            ResultSet set = statement.getResultSet();
            if(set.next()){

                return new Customer(set.getInt(1), set.getString(2), set.getString(3), set.getString(4), set.getString(5), set.getString(6));
            }

        }catch (SQLException e){

            System.out.println("eroare la getCustomerByID");
            return null;
        }

        return null;
    }
}
