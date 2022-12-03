package PatikaStore;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Products {
    static Scanner s = new Scanner(System.in);
    private int id;
    private double unitPrice;
    private double discountRate;
    private int stockQuantitiy;
    private String productName;
    private String brand;
    private int RAM;
    private double screenSize;
    private int memory;
    private static int phoneCount = 3;
    private static int notebookCount = 3;

    public static ArrayList<Notebook> notebookList = new ArrayList<>();
    public static ArrayList<MobilePhone> phoneList = new ArrayList<>();

    public Products(int id, double unitPrice, double discountRate, int stockQuantitiy, String productName, String brand, int memory, double screenSize, int RAM) {
        this.id = id;
        this.unitPrice = unitPrice;
        this.discountRate = discountRate;
        this.stockQuantitiy = stockQuantitiy;
        this.productName = productName;
        this.brand = brand;
        this.RAM = RAM;
        this.screenSize = screenSize;
        this.memory = memory;

    }

    protected Products() {
    }

    public static void add(String productGroupName) {
        System.out.print("Adı: ");
        String name = s.next();
        s.nextLine();
        System.out.print("Marka: ");
        String brand = s.nextLine();
        brand = brand.substring(0, 1).toUpperCase() + brand.substring(1).toLowerCase();
        System.out.print("Hafıza: ");
        int memory = s.nextInt();
        System.out.print("Ekran Boyutu: ");
        double screenSize = s.nextDouble();
        System.out.print("RAM: ");
        int RAM = s.nextInt();
        System.out.print("Adet Fiyatı: ");
        double price = s.nextDouble();
        System.out.print("İndirim oranı: ");
        double discountRate = s.nextDouble();
        System.out.print("Stok Adeti: ");
        int amount = s.nextInt();

        if (productGroupName.equals("Phone")) {
            System.out.print("Batarya: ");
            int battery = s.nextInt();
            System.out.print("Renk: ");
            String color = s.next();
            System.out.print("Kamera: ");
            int camera = s.nextInt();

            setPhoneCount(getPhoneCount() + 1);
            phoneList.add(new MobilePhone(phoneCount, price, discountRate, amount, name, brand, memory, screenSize, RAM, battery, color, camera));

            System.out.println("Telefon listeye eklendi!");
        } else if (productGroupName.equals("Notebook")) {
            setNotebookCount(getNotebookCount() + 1);
            notebookList.add(new Notebook(getNotebookCount(), price, discountRate, amount, name, brand, memory, screenSize, RAM));
            System.out.println("Notebook liteye eklendi!");
        }
    }

    public static int getPhoneCount() {
        return phoneCount;
    }

    public static void setPhoneCount(int phoneCount) {
        Products.phoneCount = phoneCount;
    }

    public static int getNotebookCount() {
        return notebookCount;
    }

    public static void setNotebookCount(int notebookCount) {
        Products.notebookCount = notebookCount;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public int getId() {
        return id;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(int discountRate) {
        this.discountRate = discountRate;
    }

    public int getStockQuantitiy() {
        return stockQuantitiy;
    }

    public void setStockQuantitiy(int stockQuantitiy) {
        this.stockQuantitiy = stockQuantitiy;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getRAM() {
        return RAM;
    }

    public void setRAM(int RAM) {
        this.RAM = RAM;
    }
}
