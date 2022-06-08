package ro.mycode.view;

import ro.mycode.exceptions.ProductNotFoundException;
import ro.mycode.modele.Customer;
import ro.mycode.modele.Order;
import ro.mycode.modele.OrderDetails;
import ro.mycode.modele.Product;

import java.util.*;

public class ViewAdmin extends View{

    private Customer customer;

    public ViewAdmin(Customer customer){

        this.customer = customer;
    }

    @Override
    public void meniu(){

        System.out.println("\n\nContul meu de admin: " + customer.getFullName());
        System.out.println("==================================================");
        System.out.println("1. Ultima comanda inregistrata");
        System.out.println("2. Cel mai bine vandut produs");
        System.out.println("3. Cel mai prost vandut produs");
        System.out.println("4. Adauga stock unui produs");
        System.out.println("5. Modifica un produs");
        System.out.println("6. Adauga un produs nou");
        System.out.println("7. Adauga un client nou");
        System.out.println("8. Informatii despre utilizator pe baza numelui");
        System.out.println("9. Sterge un client");
        System.out.println("10. Modifica un client");
        System.out.println("11. Iesire");
    }

    @Override
    public void play(){

        boolean run = true;
        int alegere;

        while(run){

            meniu();
            System.out.print("\nTasta: ");
            alegere = Integer.parseInt(read.nextLine());

            switch (alegere){

                case 1:lastCommandRegistered();
                break;

                case 2: bestSoldProduct();
                break;

                case 3: worstSoldPorduct();
                break;

                case 4: addStockToProduct();
                break;

                case 5: updateProduct();
                break;

                case 6: addNewProduct();
                break;

                case 7: addNewCustomer();
                break;

                case 8: getCustomerByName();
                break;

                case 9: deleteCustomer();
                break;

                case 10: updateCustomer();
                break;

                case 11: run = false;
                break;
            }
        }
    }


    private void lastCommandRegistered(){

        Order o = orderRepository.getOrderByMaxOrderDate();

        if(o != null){

            System.out.println(o);

            List<OrderDetails> ordersDetails = orderDetailsRepository.getOrderDetailsByOrderId(o.getId());
            List<Product> products = new ArrayList<>();
            for(OrderDetails orderDetails:ordersDetails){

                products.add(productRepository.getProductById(orderDetails.getProductId()));
            }

            System.out.println("Continut: ");
            for(int i=0; i< products.size(); i++){

                System.out.println(products.get(i).getName() + "    " + products.get(i).getPrice() + " x " + ordersDetails.get(i).getQuantity());
            }
        }
        else{
            System.out.println("nu exista o ultima comanda");
        }



    }

    private void bestSoldProduct(){

        int maxi = 0;
        HashMap<Integer, Integer> map = orderDetailsRepository.getProductIdAndQuantity();
        System.out.println(map);

        HashSet<Integer> set = new HashSet<>();
        for(Map.Entry<Integer, Integer> itr : map.entrySet()){

            if(itr.getValue() > maxi){

                maxi = itr.getValue();
                set.clear();
                set.add(itr.getKey());
            }
            else if(itr.getValue() == maxi){

                set.add(itr.getKey());
            }
        }

        if(set.size() > 1){

            System.out.println("Best sold products with the sales rate of " + maxi + " are: ");
            for(Integer itr : set){

                System.out.println(productRepository.getProductById(itr).getName());
            }
        }
        else{

            for(Integer itr : set){

                String name = productRepository.getProductById(itr).getName();
                System.out.println("Best sold produsct is " + name + " and the sales rate is " +maxi);
            }
        }
    }

    private void worstSoldPorduct(){

        int mini = 99999;
        HashMap<Integer, Integer> map = orderDetailsRepository.getProductIdAndQuantity();
        HashSet<Integer> set = new HashSet<>();

        for(Map.Entry<Integer, Integer> itr : map.entrySet()){

            if(itr.getValue() < mini){

                mini = itr.getValue();
                set.clear();
                set.add(itr.getKey());
            }
            else if(itr.getValue() == mini){

                set.add(itr.getKey());
            }
        }

        if(set.size() > 1){

            System.out.println("Worst sold products with the sales rate of " + mini + " are: ");
            for(Integer itr : set){

                System.out.println(productRepository.getProductById(itr).getName());
            }
        }
        else{

            for(Integer itr : set){

                String name = productRepository.getProductById(itr).getName();
                System.out.println("Worst sold product is " + name + " and the sales rate is " +mini);
            }
        }
    }

    private void addStockToProduct(){

        System.out.print("Introduceti numele produsului al carui stoc doirti sa-l mariti: ");
        String nume = read.nextLine();

        try{

            int id = productRepository.getIdByName(nume);
            if(id != -1){

                System.out.print("Nr cu care majorati stocul: ");
                int increaseStock = Integer.parseInt(read.nextLine());
                productRepository.increaseStock(nume, increaseStock);
                System.out.println("Stock modificat cu succes !!!");
            }

        }catch (ProductNotFoundException e){

            e.printStackTrace();
        }
    }

    private void updateProduct(){

        System.out.print("Id-ul produsului pe care doriti sa il modificati: ");
        int id = Integer.parseInt(read.nextLine());

        try{

            if(productRepository.getProductById(id) != null){

                System.out.println("Numele modificat al produsului: ");
                String nume = read.nextLine();

                System.out.println("Pretul modificat al produsului");
                Double price = Double.parseDouble(read.nextLine());

                System.out.println("Descrierea modificata a produsului: ");
                String description = read.nextLine();

                System.out.println("Stockul modificat al produsului: ");
                int sotck = Integer.parseInt(read.nextLine());

                productRepository.update(new Product(id, nume,price,description,sotck));
                System.out.println("Produs modificat cu succes !!!");
            }
        }catch (Exception e){

            e.printStackTrace();
            System.out.println("nu exista produs cu acest id");
        }

    }

    private void addNewProduct(){

        System.out.print("Numele noului produs: ");
        String nume = read.nextLine();

        System.out.println("Pretul produsului: ");
        Double price = Double.parseDouble(read.nextLine());

        System.out.println("Descrierea produsului: ");
        String description = read.nextLine();

        System.out.println("Stockul produsului: ");
        int sotck = Integer.parseInt(read.nextLine());

        productRepository.insert(new Product(nume,price, description, sotck));
        System.out.println("Inserare reusita cu succes !!!");
    }

    private void addNewCustomer(){

        System.out.println("Nume client nou: ");
        String nume = read.nextLine();

        if(customerRepository.getIdByName(nume) == -1){

            System.out.println("email: ");
            String email = read.nextLine();

            System.out.println("parola: ");
            String password = read.nextLine();

            System.out.println("Nr de telefon: ");
            String phone = read.nextLine();

            customerRepository.insert(new Customer(email,password,nume,"customer",phone));
            System.out.println("Clinet inserat cu succes !!!");
        }
        else{

            System.out.println("Exista deja un utilizator cu acest nume !!!");
        }
    }

    private void getCustomerByName(){

        System.out.println("Nume persoana : ");
        String nume = read.nextLine();

        if(customerRepository.getIdByName(nume) != -1){

            System.out.println(customerRepository.getCustomerByID(customerRepository.getIdByName(nume)));
        }
        else{
            System.out.println("nu exista utilizator cu acest nume !!!");
        }
    }

    private void deleteCustomer(){

        System.out.println("Numele persoanei pe care doriti sa o stergeti: ");
        String nume = read.nextLine();

        if(customerRepository.getIdByName(nume) != -1){

            customerRepository.delete(customerRepository.getIdByName(nume));
            System.out.println("persoana stearsa cu succes !!");
        }
        else{

            System.out.println("nu exista persoana cu acest nume !!!");
        }
    }

    private void updateCustomer(){

        System.out.println("Nume client modificat: ");
        String nume = read.nextLine();

        if(customerRepository.getIdByName(nume) != -1){

            int id = customerRepository.getIdByName(nume);
            System.out.println("email: ");
            String email = read.nextLine();

            System.out.println("parola: ");
            String password = read.nextLine();

            System.out.println("Nr de telefon: ");
            String phone = read.nextLine();

            System.out.println("Doriti sa il faceti admin ? (da/nu)");
            String output = read.nextLine();

            String rol = "";
            if(output.equals("da")){

                rol = "admin";
            }
            else{
                rol = "customer";
            }

            System.out.println("rol: " + rol);
            customerRepository.update(new Customer(id, email,password,nume,rol, phone));
            System.out.println("persoana modificata cu succes !!!");
        }
        else{

            System.out.println("nu exista persoana cu acest nume !!!");
        }
    }
}