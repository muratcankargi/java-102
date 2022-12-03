package PatikaStore;

import java.util.Scanner;

public class PatikaStore {
    Scanner s = new Scanner(System.in);
    int choice;

    public PatikaStore() {
    }

    public void run() {
        while (true) {
            System.out.println("\nPatika Store Ürün Yönetim Paneli !\n");
            int count = 1;
            for (String a : Brand.productGroup) {
                System.out.println(count + " - " + a + " İşlemleri");
                count++;
            }
            System.out.println(count + " - Marka Listele\n" +
                    "0 - Çıkış Yap\n");
            do {
                System.out.print("Tercihiniz :");
                choice = s.nextInt();
            } while (choice < 0 || choice > 3);
            switch (choice) {
                case 1:
                    MobilePhone.menu();
                    break;
                case 2:
                    Notebook.menu();
                    break;
                case 3:
                    Brand.printBrands();
                    break;
                case 0:
                    break;

            }
            if (choice == 0) break;
        }

    }
}

