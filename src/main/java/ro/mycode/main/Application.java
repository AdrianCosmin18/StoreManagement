package ro.mycode.main;

import ro.mycode.modele.Customer;
import ro.mycode.view.View;
import ro.mycode.view.ViewAdmin;
import ro.mycode.view.ViewCustomer;

public class Application {

    public static void main(String[] args) {

        Customer cosmin = new Customer(1,"cosminadrian1304@gmail.com","12345","Nedelcu Adrian Cosmin","admin","0773941106");
        Customer ricki = new Customer(4, "rdadge2@shinystat.com", "mJkyAOpGW0jr", "Ricki Dadge", "customer", "6568306478");
        //ViewCustomer viewCustomer = new ViewCustomer(ricki);
        ViewAdmin viewAdmin = new ViewAdmin(cosmin);
        //viewCustomer.play();
        viewAdmin.play();
    }
}

