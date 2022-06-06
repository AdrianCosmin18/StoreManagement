package ro.mycode.repositories;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    public void testLocalDate(){

        LocalDate localDate = LocalDate.parse("2022-12-13");
        assertEquals(localDate, Util.toDate("2022-12-13"));


        System.out.println(Util.toDate("2022-12-13"));
    }

}