package Collection;

import java.util.Comparator;
import java.util.TreeSet;

public class BookMain {
    public static void main(String[] args) {
        TreeSet<Book> books= new TreeSet<>();

        Book b1= new Book("Gece Yarısı Kütüphanesi",496,"aa",1950);
        Book b2= new Book("Bilinmeyen Bir Kadının Mektubu",56,"bb",1960);
        Book b3= new Book("Suç ve Ceza",687,"cc",1970);
        Book b4= new Book("Bir Aşk Masalı",256,"dd",1980);
        Book b5= new Book("Kırmızı Pelerin",124,"ee",1990);

        books.add(b1);
        books.add(b2);
        books.add(b3);
        books.add(b4);
        books.add(b5);

        for(Book value:books){
            System.out.println(value.getBookName());
        }

        System.out.println("-------------------");


        TreeSet<Book> books2= new TreeSet<>(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                if(o1.getPageNumber()==o2.getPageNumber())
                    return o1.getBookName().compareTo(o2.getBookName());
                return o1.getPageNumber()-o2.getPageNumber();
            }
        });
        books2.add(b1);
        books2.add(b2);
        books2.add(b3);
        books2.add(b4);
        books2.add(b5);
        for(Book value:books2){
            System.out.println(value.getBookName()+"-"+value.getPageNumber()+" sayfa");
        }
    }
}
