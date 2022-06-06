package ro.mycode.repositories;

import org.junit.jupiter.api.Test;
import ro.mycode.modele.Order;

import java.time.LocalDate;


class OrderRepositoryTest {


    @Test
    public void testInsert() {

        OrderRepository orderRepository = new OrderRepository();
        orderRepository.insert(new Order(40, 100, "Callatis 13", LocalDate.parse("2022-05-14")));
    }

    @Test
    public void testDelete(){

        OrderRepository orderRepository = new OrderRepository();
        orderRepository.delete(7);
        System.out.println(orderRepository.all());
    }

    @Test
    public void testAll(){

        OrderRepository orderRepository = new OrderRepository();
        System.out.println(orderRepository.all());
    }

    @Test
    public void testUpdate(){

        OrderRepository orderRepository = new OrderRepository();
        Order order = new Order(4, 1, 90.9, "Drummul Castanelor 10", LocalDate.parse("2021-09-20"));
        orderRepository.update(order);
    }

    @Test
    public void testContains(){

        OrderRepository orderRepository = new OrderRepository();
        System.out.println(orderRepository.contains(new Order(8,5,100,"Acasa 1", LocalDate.parse("2021-09-20"))));
    }

    @Test
    public void testGetOrderByCustomerIdAndLocalDate(){

        OrderRepository orderRepository = new OrderRepository();
        System.out.println(orderRepository.getOrderByCustomerIdAndDateAndAmmount0(1, "2022-05-07"));
        System.out.println(LocalDate.now());
    }


}