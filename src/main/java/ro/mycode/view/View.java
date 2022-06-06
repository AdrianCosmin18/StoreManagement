package ro.mycode.view;

import ro.mycode.repositories.CustomerRepository;
import ro.mycode.repositories.OrderDetailsRepository;
import ro.mycode.repositories.OrderRepository;
import ro.mycode.repositories.ProductRepository;
import ro.mycode.modele.Customer;

import java.util.Scanner;

public abstract class View {

    protected Scanner read;
    protected Customer customer;

    protected CustomerRepository customerRepository;
    protected ProductRepository productRepository;
    protected OrderRepository orderRepository;
    protected OrderDetailsRepository orderDetailsRepository;


    public View(){
        read = new Scanner(System.in);
        productRepository = new ProductRepository();
        orderRepository = new OrderRepository();
        orderDetailsRepository = new OrderDetailsRepository();
        customerRepository= new CustomerRepository();
    }

    public abstract void meniu();
    public abstract void play();
}
