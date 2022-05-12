package ro.mycode.Repositories;

import org.junit.jupiter.api.Test;
import ro.mycode.classes.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    @Test
    public void testInsertIntoProduct(){

        ProductRepository productRepository = new ProductRepository();
        productRepository.insert(new Product("Paine neagra", 10.99, "cu cartofi", 50));
    }

    @Test
    public void testSelectFromProducts(){

        ProductRepository productRepository = new ProductRepository();
        List<Product> products = productRepository.all();
        System.out.println(products);
    }

    @Test
    public void testDeleteByID(){

        ProductRepository productRepository = new ProductRepository();
        productRepository.delete(3);
        List<Product> products = productRepository.all();
        System.out.println(products);
    }

    @Test
    public void testUpdate(){

        ProductRepository productRepository = new ProductRepository();
        productRepository.update(new Product(4,"Paine neagra", 6.5, "Vel Pitar", 150));

        List<Product> products = productRepository.all();
        for(Product product:products){

            System.out.println(product);
        }
    }

    @Test
    public void testDeleteBelowAnInt(){

        ProductRepository productRepository = new ProductRepository();
        productRepository.deleteBelowAnInt(50);

        List<Product> products = productRepository.all();
        for(Product product:products){

            System.out.println(product);
        }
    }


    @Test
    public void testContains(){

        ProductRepository productRepository = new ProductRepository();
        assertEquals(false, productRepository.contains(new Product(5,"dfvfv", 0.4, "fvdfvd", 4)));
    }

    @Test
    public void testGetIdByName(){

        ProductRepository productRepository = new ProductRepository();
        assertEquals(4, productRepository.getIdByName("Paine neagra"));
    }

    @Test
    public void testGetProductById(){

        ProductRepository productRepository = new ProductRepository();
        Product product = productRepository.getProductById(4);
        System.out.println(product);
    }


}