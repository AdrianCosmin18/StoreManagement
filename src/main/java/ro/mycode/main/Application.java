package ro.mycode.main;

import ro.mycode.modele.Customer;
import ro.mycode.view.View;
import ro.mycode.view.ViewAdmin;
import ro.mycode.view.ViewCustomer;
import ro.mycode.view.ViewLogIn;

public class Application {

    public static void main(String[] args) {

        ViewLogIn viewLogIn = new ViewLogIn();
        viewLogIn.play();
    }
}

