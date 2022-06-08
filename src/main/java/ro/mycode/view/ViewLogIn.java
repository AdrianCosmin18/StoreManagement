package ro.mycode.view;

import ro.mycode.modele.Customer;
import ro.mycode.repositories.CustomerRepository;

import java.util.Scanner;

public class ViewLogIn {

    private Scanner read;
    private CustomerRepository customerRepository;

    public ViewLogIn(){

        read = new Scanner(System.in);
        customerRepository = new CustomerRepository();
    }


    public void menu(){

        System.out.println("Exit : press 1");
        System.out.println("Log In : press 2");
        System.out.println("Register as customer, new account : press 3");
    }

    public void play(){

        boolean run = true;
        int choice;

        while (run){

            menu();
            System.out.println("\nChoice : ");
            choice = Integer.parseInt(read.nextLine());

            switch (choice){

                case 1 : run = false;
                    break;

                case 2 : logIn();
                    break;

                case 3 : register();
                    break;
            }
        }
    }


    public void logIn(){

        System.out.println("Name : ");
        String name = read.nextLine();

        System.out.println("Password : ");
        String password = read.nextLine();

        Customer customer = customerRepository.getClientByNameAndPassword(name, password);

        if(customer.getRol().equals("customer")){

            new ViewCustomer(customer).play();
        }
        else if(customer.getRol().equals("admin")){

            new ViewAdmin(customer).play();
        }
        else{

            System.out.println("Nume sau parola gresita !!!");
        }
    }

    public void register(){

        System.out.println("Name : ");
        String nume = read.nextLine();

        while(customerRepository.existsName(nume)){

            System.out.println("This name is already taken, try another one");
            System.out.print("Name : ");
            nume = read.nextLine();
        }

        System.out.print("Password : ");
        String parola = read.nextLine();

        System.out.print("email : ");
        String email = read.nextLine();

        String rol = "customer";

        System.out.print("Phone: ");
        String phone = read.nextLine();


        Customer newCusotmer = new Customer(email, parola, nume, rol, phone);
        customerRepository.insert(newCusotmer);
    }
}
