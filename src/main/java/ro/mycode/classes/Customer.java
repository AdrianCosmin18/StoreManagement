package ro.mycode.classes;

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
}
