package ro.mycode.exceptions;


public class ProductNotFoundException extends Exception{

    public ProductNotFoundException(String nume) {

        super(String.format("Produsul cu numele %s nu exista",nume));
    }
}
