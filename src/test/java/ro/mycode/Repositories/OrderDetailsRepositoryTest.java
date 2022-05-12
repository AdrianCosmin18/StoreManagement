package ro.mycode.Repositories;

import org.junit.jupiter.api.Test;
import ro.mycode.classes.OrderDetails;

import static org.junit.jupiter.api.Assertions.*;

class OrderDetailsRepositoryTest {

    @Test
    public void testAll(){

        OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepository();
        System.out.println(orderDetailsRepository.all());
    }

    @Test
    public void testInsert(){

        OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepository();
        orderDetailsRepository.insert(new OrderDetails(1,5,5.9,2));
    }

}