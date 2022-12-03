package PatikaStore;

import java.util.*;

public class Notebook extends Products {
    static Scanner s = new Scanner(System.in);


    public Notebook(int id, double unitPrice, double discountRate, int stockQuantitiy, String productName, String brand, int RAM, double screenSize, int memory) {
        super(id, unitPrice, discountRate, stockQuantitiy, productName, brand, RAM, screenSize, memory);
    }
    public Notebook() {
        notebookList.add(new Notebook(1, 7000, 10, 5, "Huawei Matebook 14", "Huawei", 512, 14,16));
        notebookList.add(new Notebook(2, 3699, 10, 5, "Lenovo V14 IGL", "Lenovo", 1024, 14, 8));
        notebookList.add(new Notebook(3, 8199, 10, 5, "Asus Tuf Gaming", "Asus", 2048, 15.6, 32));
    }
    public static void menu() {
        int choice;
        System.out.println("\n===== Notebook Management Panel =====\n" +
                "1- Notebook Ekle\n" +
                "2- Notebook Listele\n" +
                "3- Notebook Sil\n" +
                "4- ID ye göre sırala\n" +
                "5- Markaya göre sırala");
        do {
            System.out.println("Tercihin : ");
            choice = s.nextInt();
        } while (choice < 1 || choice > 5);
        switch (choice) {
            case 1:
                add("Notebook");
                break;
            case 2:
                print(notebookList);
                break;
            case 3:
                remove();
                break;
            case 4:
                sort();
                break;
            case 5:
                Brand.printWithId();
                break;
        }

    }

    public static void print(ArrayList<Notebook> notebooks) {
        System.out.println("| ID | Name of Product               | Price     | Brand     | Memory  | Screen Size | RAM      |");
        System.out.println("-------------------------------------------------------------------------------------------------");

        for (Notebook notebook : notebooks) {
            System.out.printf("| %-2d | %-29s | %-9.1f | %-9s | %-7d | %-11.1f | %-8d |",
                    notebook.getId(), notebook.getProductName(), notebook.getUnitPrice(), notebook.getBrand(), notebook.getMemory(),
                    notebook.getScreenSize(), notebook.getRAM());
            System.out.println();
        }
    }

    public static void remove() {
        print(Products.notebookList);
        System.out.print("Silmek istediğiniz ürün ID seçiniz: ");
        int sel = s.nextInt();
        for (Notebook notebook : notebookList) {
            if (notebook.getId() == sel-1) {
                notebookList.remove(sel-1);
                System.out.println("Ürün Silindi!");
                break;
            }
        }
    }
    public static void sort() {
        ArrayList<Notebook> sortByID = notebookList;

        sortByID.sort(new Comparator<Notebook>() {
            @Override
            public int compare(Notebook o1, Notebook o2) {
                return o1.getId() - o2.getId();
            }
        });
        print(sortByID);
    }
    public static void filterByBrand() {
        Brand.printWithId();
    }
}
