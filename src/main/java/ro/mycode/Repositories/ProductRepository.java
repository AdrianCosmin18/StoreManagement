package ro.mycode.Repositories;

import ro.mycode.classes.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends Repository<Product>{

    public ProductRepository(){

        super();
    }

    @Override
    protected void insert(Product product){

        String insertTo = "";
        insertTo += "insert into products ";
        insertTo += "(name, price, description, stock) ";
        insertTo += "values(";
        insertTo += String.format(" '%s' , %.2f, '%s', %d",product.getName(), product.getPrice(), product.getDescription(), product.getStock());
        insertTo += ");";

        executeStatement(insertTo);
    }

    @Override
    protected void delete(int id) {

        String deleteTo = "";
        deleteTo += "delete from products where id = ";
        deleteTo += String.format("%d",id);
        deleteTo += ";";

        executeStatement(deleteTo);
    }

    @Override
    protected ResultSet allResultSet() {
        executeStatement("select * from products;");
        try{

            return statement.getResultSet();
        }catch (SQLException e){

            System.out.println("nu s-a putut executa : select * from products;");
            return null;
        }
    }

    @Override
    protected List<Product> all(){

        ResultSet set = allResultSet();
        List<Product> products = new ArrayList<>();
        try{
            while (set.next()){

                Product product = new Product(set.getInt(1), set.getString(2),set.getDouble(3), set.getString(4), set.getInt(5));
                products.add(product);
            }

        }catch (Exception e){

            System.out.println("nu s-a putut crea lista");
        }

        return products;
    }

    protected void modifyProduct(Product product){


    }


}
