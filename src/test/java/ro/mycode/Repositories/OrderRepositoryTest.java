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

    //nu stiu cum sa creez un Date in java
    @Test
    public void testInsert() throws ParseException {

        OrderRepository orderRepository = new OrderRepository();

//        //convert String to Date
//        String string = "July 2, 2010";
//        DateFormat format = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
//        Date date = format.parse(string);
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse("18/12/2015");

        orderRepository.insert(new Order(5, 14.5, "Timisul de Jos 3", date1));
    }

    @Test
    public void testDelete(){

        OrderRepository orderRepository = new OrderRepository();
        orderRepository.delete(2);
    }

    @Test
    public void testALl(){

        OrderRepository orderRepository = new OrderRepository();
        System.out.println(orderRepository.all());
    }

    //nu pot testa pt ca nu stiu cum sa creez un Date in java
    @Test
    public void testUpdate(){

        OrderRepository orderRepository = new OrderRepository();
        //Order order = new Order(4, 60, 90.9, "Drummul Castanelor 10", "2021-09-20");
    }

    //nu pot testa pt ca nu stiu cum sa creez un Date in java
    @Test
    public void testContains(){

        OrderRepository orderRepository = new OrderRepository();
        //orderRepository.contains(new Order(4,5,100,"Acasa 1", ))
    }


}