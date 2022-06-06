package ro.mycode.view;

import ro.mycode.modele.Customer;
import ro.mycode.modele.Order;
import ro.mycode.modele.OrderDetails;
import ro.mycode.modele.Product;
import ro.mycode.exceptions.InsufficientStock;
import ro.mycode.exceptions.ProductNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ViewCustomer extends View{

    private Order order;
    private  Customer customer;


    public ViewCustomer(Customer customer){

        this.customer = customer;
        super.orderRepository.insert(new Order(customer.getId(), 0, customer.getRol(), LocalDate.now()));
        this.order = this.orderRepository.getOrderByCustomerIdAndDateAndAmmount0(customer.getId(),LocalDate.now().toString());

    }

    @Override
    public void meniu(){

        System.out.println("\n\nContul meu: " + customer.getFullName());
        System.out.println("===========================================");
        System.out.println("1. Lista tuturor produselor");
        System.out.println("2. Adauga un produs in cos");
        System.out.println("3. Produsele din cos");
        System.out.println("4. Sterge un produs din cos");
        System.out.println("5. Goliti cosul");
        System.out.println("6. Plaseaza comanda");
        System.out.println("10. Iesire");
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

                case 1: allProducts();
                    break;

                case 2:
                    try {
                        addToCart();
                    }catch (ProductNotFoundException e){
                        e.printStackTrace();
                    }catch (InsufficientStock e){
                        e.printStackTrace();
                    }
                    break;

                case 3:viewCart();
                break;

                case 4:
                    try{
                        deleteProductFromCart();
                    }catch(ProductNotFoundException e){
                        e.printStackTrace();
                    }
                    break;

                case 5: clearCart();
                    break;

                case 6: placeOrder();
                    break;

                case 10: {
                    iesire();
                    run = false;
                }
                    break;
            }
        }
    }

    public void iesire(){

        orderRepository.delete(this.order.getId());
    }


    private void allProducts(){

        List<Product> products =  productRepository.all();
        for(Product product:products){
            System.out.println(product);
        }
    }

    private void addToCart()throws ProductNotFoundException, InsufficientStock {

        System.out.print("Introduceti numele produsului pe care doriti sa-l adaugati in cos: ");
        String name = read.nextLine();
        int id = productRepository.getIdByName(name);

        if(id != -1){

            Product product = productRepository.getProductById(id);

            System.out.print("Cantitatea: ");
            int quantity = Integer.parseInt(read.nextLine());
            if(productRepository.getProductById(id).getStock() < quantity){

                throw new InsufficientStock(quantity);
            }
            orderDetailsRepository.insert(new OrderDetails(order.getId(), product.getId(), product.getPrice(), quantity));
            this.order.setAmmount(this.order.getAmmount() + product.getPrice() * quantity);


        }else{

            throw  new ProductNotFoundException(name);
        }
    }

    private void viewCart(){

        List<OrderDetails> ordersDetails = orderDetailsRepository.getOrderDetailsByOrderId(this.order.getId());
        List<Product> products = new ArrayList<>();
        for(OrderDetails orderDetails:ordersDetails){

            products.add(productRepository.getProductById(orderDetails.getProductId()));
        }

        System.out.println("\nCos: ");
        double totalSum = 0;
        for(int i=0; i< products.size(); i++){

            System.out.println(products.get(i).getName() + "    " + products.get(i).getPrice() + " x " + ordersDetails.get(i).getQuantity());
            totalSum += products.get(i).getPrice() *  ordersDetails.get(i).getQuantity();
        }
        System.out.println("\nSuma totala : " + totalSum);
    }

    private void deleteProductFromCart() throws ProductNotFoundException {

        List<OrderDetails> orderDetails = orderDetailsRepository.getOrderDetailsByOrderId(this.order.getId());
        System.out.print("Numele produsului pe care doriti sa-l setrgeti din cos: ");
        String nume = read.nextLine();
        Product p = productRepository.getProductById(productRepository.getIdByName(nume));

        if(p != null){

            orderDetailsRepository.deleteByOrderIdAndProductID(this.order.getId(), p.getId());
            System.out.println("produs sters din cos !");
        }
    }

    private void clearCart(){

        orderDetailsRepository.deleteByOrderId(this.order.getId());
        System.out.println("cosul a fost golit !");
    }

    private void placeOrder(){

        List<OrderDetails> ordersDetails = orderDetailsRepository.getOrderDetailsByOrderId(this.order.getId());
        List<Product> products = new ArrayList<>();
        for(OrderDetails orderDetails:ordersDetails){

            Product p = productRepository.getProductById(orderDetails.getProductId());
            productRepository.updateStock(p.getName(), orderDetails.getQuantity());

        }

        orderRepository.update(this.order);
        super.orderRepository.insert(new Order(customer.getId(), 0, customer.getRol(), LocalDate.now()));
        this.order = this.orderRepository.getOrderByCustomerIdAndDateAndAmmount0(customer.getId(),LocalDate.now().toString());

    }

}
