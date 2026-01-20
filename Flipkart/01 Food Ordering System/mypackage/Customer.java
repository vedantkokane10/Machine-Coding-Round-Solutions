package mypackage;

public class Customer {
    private String name;
    private String email;
    private String phone;
    private long id;

    Customer(String name, String email, String phone, long id){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.id = id;
    }

    void setName(String name){
        this.name = name;
    }

    void setEmail(String email){
        this.email = email;
    }

    void setPhone(String phone){
        this.phone = phone;
    }

    String getName(){
        return this.name;
    }

    String getEmail(){
        return this.email;
    }

    String getPhone(){
        return this.phone;
    }

    long getId(){
        return this.id;
    }
}
