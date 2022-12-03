package PatikaStore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class Brand {
    final private int id;
    private String name;
    static TreeSet<Brand> brands= new TreeSet<>(new Comparator<Brand>() {
        @Override
        public int compare(Brand o1, Brand o2) {
            return o1.getName().compareTo(o2.getName());
        }
    });
     static ArrayList<String> productGroup= new ArrayList<>();

static {
    Brand b1= new Brand(101,"Samsung");
        Brand b2= new Brand(102,"Lenovo");
        Brand b3= new Brand(103,"Apple");
        Brand b4= new Brand(104,"Huawei");
        Brand b5= new Brand(105,"Casper");
        Brand b6= new Brand(106,"Asus");
        Brand b7= new Brand(107,"HP");
        Brand b8= new Brand(108,"Xiaomi");
        Brand b9= new Brand(109,"Monster");

        brands.add(b1);
        brands.add(b2);
        brands.add(b3);
        brands.add(b4);
        brands.add(b5);
        brands.add(b6);
        brands.add(b7);
        brands.add(b8);
        brands.add(b9);

        productGroup.add("Cep Telefonları");
        productGroup.add("Notebook");
}

    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public static void printWithId() {
        for (int i = 0; i < productGroup.size(); i++) {
            System.out.println((i + 1) + "- " + productGroup.get(i));
        }
    }

    public static void printBrands() {
        System.out.println("\nMarkalarımız\n----------");
        for (Brand brand : brands) {
            System.out.println("- " + brand.name);
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
