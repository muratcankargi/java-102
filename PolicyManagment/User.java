package PolicyManagment;

import java.util.*;

public class User extends Account{
    ArrayList<Address> AddressList= new ArrayList<>();
    public User(String firstName, String lastName, String email, String password, String job, int age) {
        super(firstName, lastName, email, password, job, age);
    }

    @Override
    public void addInsurance(Insurance i) {

    }

    @Override
    public void addAddress(User user, Address address) {

    }

    @Override
    public void removeAddres(User user, Address address) {

    }


    public static void removeAddress(User user,Address address){
        user.getAddressList().remove(address);
    }
    public static Address createHomeAddress(){
        Scanner src = new Scanner(System.in);
        System.out.print("Enter Home Addres : ");
        String homeadr= src.nextLine();
        return new HomeAddress(homeadr);
    }
    public  static Address createBusenessAddres(){
        Scanner src = new Scanner(System.in);
        System.out.print("Enter Buseness Addres : ");
        String Busenessadr= src.nextLine();
        return new HomeAddress(Busenessadr);
    }

    public ArrayList<Address> getAddressList() {
        return AddressList;
    }

    public void setAddressList(ArrayList<Address> addressList) {
        AddressList = addressList;
    }

}
