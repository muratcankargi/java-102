package PolicyManagment;

import java.util.*;
import java.time.*;

public class InsuranceManager {
    static Scanner s = new Scanner(System.in);
    User user;

    public InsuranceManager() {
    }

    public Insurance createInsurance(User user) {
        System.out.println("Select one of Insurance : " +
                "\n 1 - Healt " +
                "\n 2 - Residance " +
                "\n 3 - Travel " +
                "\n 4 - Car ");
        String choice = s.nextLine();
        System.out.print("Enter amount :");
        double price = s.nextInt();

        if (choice.equals("1")) return new HealthInsurance("Healt Insurance", 1.98, Date.from(Instant.now()),user);
        if (choice.equals("2"))
            return new ResidenceInsurance("Residance Insurance", 1.46, Date.from(Instant.now()), user);
        if (choice.equals("3")) return new TravelInsurance("Travel Insurance ", 1.30, Date.from(Instant.now()), user);
        return new CarInsurance("Car Insurance", 1.50, Date.from(Instant.now()), user);
    }
}

