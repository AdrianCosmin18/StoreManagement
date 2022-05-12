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

    @Override
    protected void update(Product product){

        String modifyTo = "";
        modifyTo += String.format("update products set name = '%s', price =  %.2f, description = '%s', stock = %d", product.getName(), product.getPrice(), product.getDescription(), product.getStock());
        modifyTo += String.format(" where id = %d", product.getId());
        modifyTo += ";";

        executeStatement(modifyTo);
    }

    protected void deleteBelowAnInt(int x){

        String deleteTo = "";
        deleteTo += String.format("delete from products where stock <= %d ", x);
        deleteTo += ";";

        executeStatement(deleteTo);
    }

    protected boolean contains(Product product){
        try{
            String text = "";
            text += String.format("select * from products where id = %d ", product.getId());
            executeStatement(text);
            ResultSet set = statement.getResultSet();
            if(set.next()){

                return true;
            }

        }catch (SQLException e){

            System.out.println("eroare la contanins");
            return false;
        }

        return false;
    }

    protected int getIdByName(String name){

        String text = "";
        text += String.format("select id from products where name = '%s'; ", name);
        executeStatement(text);
        try{
            ResultSet set = statement.getResultSet();
            List<Integer> ids = new ArrayList<>();
            while(set.next()){

                ids.add(set.getInt(1));
            }

            return ids.get(0);

        }catch (SQLException e){

            System.out.println("eroare la getIdByName");
            return -1;
        }
    }

    protected Product getProductById(int id){

        String text = "";
        text += String.format("select * from products where id = %d ;", id);
        executeStatement(text);

        try{
            ResultSet set = statement.getResultSet();
            if(set.next()){

                return new Product(set.getInt(1), set.getString(2), set.getDouble(3), set.getString(4), set.getInt(5));
            }

        }catch (SQLException e){

            System.out.println("eroare la getProductByID");
            return null;
        }

        return null;
    }

}
