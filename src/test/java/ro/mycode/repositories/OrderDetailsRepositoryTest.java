package ro.mycode.repositories;

import org.junit.jupiter.api.Test;
import ro.mycode.modele.OrderDetails;

class OrderDetailsRepositoryTest {

    @Test
    public void testAll(){

        OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepository();
        System.out.println(orderDetailsRepository.all());
    }

    @Test
    public void testInsert(){

        OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepository();
        orderDetailsRepository.insert(new OrderDetails(1,6,5.9,2));
    }

    @Test
    public void testDelete(){

        OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepository();
        orderDetailsRepository.delete(26);
        System.out.println(orderDetailsRepository.all());
    }

    @Test
    public void testUpdate(){

        OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepository();
        orderDetailsRepository.update(new OrderDetails(22, 1, 7, 47, 3));
        System.out.println(orderDetailsRepository.all());
    }

    @Test
    public void testContains(){

        OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepository();
        System.out.println(orderDetailsRepository.contains(new OrderDetails(20, 0, 9, 0, 3)));
    }

    @Test
    public void testgetOrderDetailsByOrderId(){

        OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepository();
        System.out.println(orderDetailsRepository.getOrderDetailsByOrderId(1));
    }

    @Test
    public void testDeleteByOrderIdAndProductId(){

        OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepository();
        orderDetailsRepository.deleteByOrderIdAndProductID(16, 6);
    }

    @Test
    public void testGetProductIdAndQuantity(){

        OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepository();
        System.out.println(orderDetailsRepository.getProductIdAndQuantity());
    }

}