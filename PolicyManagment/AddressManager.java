package PolicyManagment;

import java.util.Date;
import java.util.Scanner;
import java.util.stream.StreamSupport;

public class AddressManager extends User{
    static Scanner s= new Scanner(System.in);
    public AddressManager(String firstName, String lastName, String email, String password, String job, int age) {
        super(firstName, lastName, email, password, job, age);
    }
    public void addAddress(User user, Address address){
        user.getAddressList().add(address);
    }
    public static void removeAddress(User user,Address address){
        user.getAddressList().remove(address);
    }
    public static Address createHomeAddress(){
        System.out.print("Enter Home Addres : ");
        String homeadr= s.nextLine();
        return new HomeAddress(homeadr);
    }
    public  static Address createBusenessAddres(){
        System.out.print("Enter Buseness Addres : ");
        String Busenessadr= s.nextLine();
        return new HomeAddress(Busenessadr);
    }

}
