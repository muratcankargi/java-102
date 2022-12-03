package Collection;

import java.util.Date;
public class Book implements Comparable<Book> {
    private String bookName;
    private int pageNumber;
    private String writerName;
    private int ReleaseYear;

    public Book(String bookName, int pageNumber, String writerName, int releaseDate) {
        this.bookName = bookName;
        this.pageNumber = pageNumber;
        this.writerName = writerName;
        ReleaseYear = releaseDate;
    }


    public String getBookName() {
        return bookName;
    }

    public int getPageNumber() {
        return pageNumber;
    }


    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public int compareTo(Book o) {
        return this.getBookName().compareTo(o.getBookName());
    }
}

