package ro.mycode.classes;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private int id;
    private String name;
    private double price;
    private String description;
    private int stock;

    public Product(String name, double price, String description, int stock) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    @Override
    public String toString(){

        String text = "";
        text += "\nID: " + id;
        text += "\nName: " + name;
        text += "\nPrice: " + price;
        text += "\nDescription: " + description;
        text += "\nStock: " + stock;

        return text;
    }
}
