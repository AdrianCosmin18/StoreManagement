package ro.mycode.Repositories;

import org.junit.jupiter.api.Test;
import ro.mycode.classes.Customer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryTest {

    @Test
    public void testInsert(){

        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer = new Customer("cosmin_ndlc@yahoo.com", "1234", "Nedelcu Cosmin", "Callatis 12", "0773941106");
        customerRepository.insert(customer);
    }

    @Test
    public void testDelete(){

        CustomerRepository customerRepository = new CustomerRepository();
        customerRepository.delete(2);
    }

    @Test
    public void testAll(){

        CustomerRepository customerRepository = new CustomerRepository();
        List<Customer> customers = customerRepository.all();
        System.out.println(customers);
    }

    @Test
    public void testUpdate(){

        CustomerRepository customerRepository = new CustomerRepository();
        customerRepository.update(new Customer(1,"cosminadrian1304@gmail.com", "12345", "Nedelcu Adrian Cosmin", "Timisul de Jos 3", "0773941106"));

    }

    @Test
    public void testContains(){

        CustomerRepository customerRepository = new CustomerRepository();
        assertEquals(false, customerRepository.contains(new Customer(1002,"345","3456", "345", "3453453", "2342354")));
    }

    @Test
    public void testGetIdByName(){

        CustomerRepository customerRepository = new CustomerRepository();
        System.out.println(customerRepository.getIdByName("Fairfax Lerner"));
    }

    @Test
    public void testGetCustomerByID(){

        CustomerRepository customerRepository = new CustomerRepository();
        System.out.println(customerRepository.getCustomerByID(1));
    }

}