package ro.mycode.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private int id;
    private String email;
    private String password;
    private String fullName;
    private String billingAddress;
    private String phone;

    public Customer(String email, String password, String fullName, String billingAddress, String phone) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.billingAddress = billingAddress;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o){

        Customer customer = (Customer) o;
        return customer.id == this.id;
    }

    @Override
    public String toString(){

        String text = "";
        text += "\nID: " + id;
        text += "\nemail: "+ email;
        text += "\npassword: "+password;
        text += "\nfullname: "+fullName;
        text += "\naddress: "+billingAddress;
        text += "\nphone: "+ phone;

        return text;
    }
}
