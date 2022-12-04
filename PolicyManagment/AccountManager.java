package PolicyManagment;

import java.awt.image.PackedColorModel;
import java.util.*;

public class AccountManager {
    static TreeSet<User> userList = new TreeSet<>();
    User user;
    Account account;
    static Scanner s = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.print("1-Register\n"+
                    "2-Login\n" +
                    "0-Exit\n" +
                    "Choice : ");
            int choice = s.nextInt();
            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    break;
            }
        }
    }

    public void login() {
        try {
            user.login();
        } catch (InvalidAuthenticationException e) {
            e.toString();
        }
        individualUserProcess();
    }

    public void register() {
        String firstName, lastName, email, password, job;
        int age;
        char type;
        System.out.print("Enter name : ");
        firstName = s.nextLine();
        System.out.print("Enter surname : ");
        lastName = s.nextLine();
        System.out.print("Enter email : ");
        email = s.nextLine();
        System.out.print("Enter password : ");
        password = s.nextLine();
        System.out.print("Enter job : ");
        job = s.nextLine();
        System.out.print("Enter age : ");
        age = s.nextInt();
        System.out.println("Individual or Enterprise (I/E) :");
        type = s.next().charAt(0);

        User user = new User(firstName, lastName, email, password, job, age);
        userList.add(user);
        System.out.println("User Registration Created!");
    }

    public void individualUserProcess() {
        System.out.println("Welcome " + user.getFirstName());
        int choice = -1;
        while (choice != 0) {
            Scanner src = new Scanner(System.in);
            System.out.println("\n1 - Show my infos");
            System.out.println("2 - Get Insurance");
            System.out.println("3 - Show Insurance List");
            System.out.println("4 - Add Address");
            System.out.println("5 - Show All Address");
            System.out.println("0 - Exit");
            System.out.print("Choice : ");
            choice = s.nextInt();

            switch (choice) {
                case 1:
                    user.showUserInfo();
                    break;
                case 2:
                    InsuranceManager insuranceManager = new InsuranceManager();
                    account.addInsurance(insuranceManager.createInsurance(user));
                case 3:
                    for (Insurance insurance : account.getUser().getInsurancesList()) {
                        System.out.print(" \n  Name : " + insurance.getInsuranceName());
                        System.out.println("Prace : " + insurance.getInsuranceFee());
                    }
                case 4:
                    account.addAddress(user,AddressManager.createHomeAddress());
                case 5:
                    for (Address address:user.getAddressList()){
                        System.out.println(user.getAddressList());
                    }


            }
        }
    }
}