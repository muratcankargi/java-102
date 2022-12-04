package PolicyManagment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public abstract class Account {
    static Scanner s = new Scanner(System.in);
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String job;
    private int age;
    static ArrayList<Insurance> insurancesList = new ArrayList<>();
    private Date lastLoginDate;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private char type;
    User user;

    enum AuthenticationStatus {
        SUCCESS,
        FAIL
    }

    public Account(String firstName, String lastName, String email, String password, String job, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.job = job;
        this.age = age;

    }

    public Account() {
    }

    public boolean login() throws InvalidAuthenticationException {
        System.out.print("E-mail : ");
        String email = s.next();
        System.out.println("Password : ");
        String password = s.next();
        if (email.equals(getEmail()) && password.equals(getPassword())) {
            System.out.println(AuthenticationStatus.SUCCESS);
            return true;
        }
        throw new InvalidAuthenticationException("");
    }

    public final void showUserInfo() {
        System.out.println("First Name:" + this.firstName + "\n" +
                "Last Name:" + this.lastName + "\n" +
                "E-mail:" + this.email + "\n" +
                "Job:" + this.job + "\n" +
                "Age:" + this.age + "\n" +
                "Password:" + this.password + "\n" +
                "Last Login:" + this.lastLoginDate + "\n");
    }

    public abstract void addInsurance(Insurance i);

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public static ArrayList<Insurance> getInsurancesList() {
        return insurancesList;
    }

    public static void setInsurancesList(ArrayList<Insurance> insurancesList) {
        Account.insurancesList = insurancesList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public abstract void addAddress(User user,Address address);
    public abstract void removeAddres(User user,Address address);
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
}
