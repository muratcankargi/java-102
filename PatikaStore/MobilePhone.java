package PatikaStore;

import java.lang.management.MonitorInfo;
import java.util.ArrayList;
import java.util.Comparator;

public class MobilePhone extends Products{
    private int phoneBatteryPower;
    private String phoneColor;
    private int camera;

    public MobilePhone(int id, double unitPrice, double discountRate, int stockQuantitiy, String productName, String brand, int memory, double screenSize, int RAM, int phoneBatteryPower, String phoneColor, int camera) {
        super(id, unitPrice, discountRate, stockQuantitiy, productName, brand, memory, screenSize, RAM);
        this.phoneBatteryPower = phoneBatteryPower;
        this.phoneColor = phoneColor;
        this.camera = camera;
    }
    public MobilePhone() {
        phoneList.add(new MobilePhone(1, 3199, 10, 5, "Samsung Galaxy A51", "Samsung", 128, 6.5, 6, 4000, "Siyah", 32));
        phoneList.add(new MobilePhone(2, 7379, 10, 5, "Iphone 11 64 GB", "Apple", 64, 6.1, 6, 3046, "Mavi", 5));
        phoneList.add(new MobilePhone(3, 4012, 10, 5, "Redmi Note 10 Pro 8 GB", "Xiaomi", 128, 6.5, 12, 4000, "Beyaz", 35));
    }
    public static void menu() {
        int choice;
        System.out.println("\n===== Notebook Management Panel =====\n" +
                "1- Telefon Ekle\n" +
                "2- Telefon Listele\n" +
                "3- Telefon Sil\n" +
                "4- ID ye göre sırala\n" +
                "5- Markaya göre sırala");
        do {
            System.out.print("Tercihin : ");
            choice = s.nextInt();
        } while (choice < 1 || choice > 5);
        switch (choice) {
            case 1:
                add("Phone");
                break;
            case 2:
                print(phoneList);
                break;
            case 3:
                remove();
                break;
            case 4:
                sort();
                break;
            case 5:
                sort();
                break;
        }

    }

    public static void print(ArrayList<MobilePhone> phones) {
        System.out.println("| ID | Name of Product               | Price     | Brand     | Memory    | Screen Size | Camera    | Battery   | RAM       | Color      | ");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");

        for (MobilePhone phone : phones) {
            System.out.printf("| %-2d | %-29s | %-9.1f | %-9s | %-9d | %-11.1f | %-9d | %-9d | %-9d | %-10s |",
                    phone.getId(), phone.getProductName(), phone.getUnitPrice(), phone.getBrand(), phone.getMemory(),
                    phone.getScreenSize(), phone.getCamera(), phone.getPhoneBatteryPower(), phone.getRAM(), phone.getPhoneColor());
            System.out.println();
        }
    }
    public static void remove() {
        print(Products.phoneList);
        System.out.print("Silmek istediğiniz ürün ID seçiniz: ");
        int sel = s.nextInt();
        for (MobilePhone phone : phoneList) {
            if (phone.getId() == sel-1) {
                phoneList.remove(sel-1);
                System.out.println("Ürün Silindi!");
                break;
            }
        }
    }
    public static void sort() {
        ArrayList<MobilePhone> sortByID = phoneList;

        sortByID.sort(new Comparator<MobilePhone>() {
            @Override
            public int compare(MobilePhone o1, MobilePhone o2) {
                return o1.getId() - o2.getId();
            }
        });
        print(sortByID);
    }

    public int getCamera() {
        return camera;
    }

    public void setCamera(int camera) {
        this.camera = camera;
    }

    public static void filterByBrand() {
        Brand.printWithId();
    }
    public int getPhoneBatteryPower() {
        return phoneBatteryPower;
    }

    public void setPhoneBatteryPower(int phoneBatteryPower) {
        this.phoneBatteryPower = phoneBatteryPower;
    }

    public String getPhoneColor() {
        return phoneColor;
    }

    public void setPhoneColor(String phoneColor) {
        this.phoneColor = phoneColor;
    }
}
