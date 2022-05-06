package ro.mycode.Repositories;

import org.junit.jupiter.api.Test;
import ro.mycode.classes.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    @Test
    public void testInsertIntoProduct(){

        ProductRepository productRepository = new ProductRepository();
        productRepository.insert(new Product("Miere", 15.99, "poliflora", 58));
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
}