package BookList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(new Book("Hobbit", 565, "J.R.R Tolkien", 1940));
        bookList.add(new Book("Savaş ve Baris", 800, "Tolstoy", 1915));
        bookList.add(new Book("Suc ve Ceza", 612, "Dostoyevski", 1925));
        bookList.add(new Book("Tutunamayanlar", 550, "Oğuz Atay", 1950));
        bookList.add(new Book("Denizler altinda yirmi bin fersah", 55, "Jules Verne", 1925));
        bookList.add(new Book("Kuyucakli Yusuf", 145, "Sabahattin Ali", 1965));
        bookList.add(new Book("Metro 2023", 650, "Dmitry Glukhovsky", 2006));
        bookList.add(new Book("Gece Yarısı Kütüphanesi", 296, "Matt Haig", 2021));
        bookList.add(new Book("Hayvan Çiftliği", 152, "George Orwell", 2006));
        bookList.add(new Book("Satranç", 71, "Stefan Zweig", 1942));


        Map<String,String> bookMap= new HashMap<>();
        bookList.stream().forEach(i->bookMap.put(i.getBookName(),i.getWriterName()));
        for(String s:bookMap.keySet()){
            System.out.println("Book Name : "+s+"\t\tBook Author : "+bookMap.get(s));
        }
        System.out.println("-------------------");
        List<Book> newBookList;
        newBookList = bookList.stream().filter(book->book.getPageNumber()>100).collect(Collectors.toList());
        newBookList.stream().forEach(b->System.out.println("Book name : "+b.getBookName() +
                "\t\tAuthor : "+b.getWriterName()+
                "\t\tPage number : "+b.getPageNumber()));

    }
    }

