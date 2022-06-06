package ro.mycode.exceptions;

public class InsufficientStock extends Exception{

    public InsufficientStock(int count){

        super(String.format("Produsul dat nu are %d bucati in stoc", count));
    }
}
