package ro.mycode.Repositories;

import java.time.LocalDate;

public class Util {


    public static LocalDate toDate(String stringDate){

        try{

            return LocalDate.parse(stringDate);

        }catch (Exception e){

            System.out.println("eroare la convertire");
            return null;
        }
    }
}
