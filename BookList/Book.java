package BookList;

public class Book {
    private String bookName;
    private int pageNumber;
    private String writerName;
    private int releaseYear;

    public Book(String bookName, int pageNumber, String writerName, int releaseYear) {
        this.bookName = bookName;
        this.pageNumber = pageNumber;
        this.writerName = writerName;
        this.releaseYear = releaseYear;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
