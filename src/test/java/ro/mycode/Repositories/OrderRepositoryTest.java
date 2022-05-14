package ro.mycode.Repositories;

import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;
import ro.mycode.classes.Order;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;


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

    //nu pot testa pt ca nu stiu cum sa creez un Date in java
    @Test
    public void testContains(){

        OrderRepository orderRepository = new OrderRepository();
        System.out.println(orderRepository.contains(new Order(8,5,100,"Acasa 1", LocalDate.parse("2021-09-20"))));
    }


}