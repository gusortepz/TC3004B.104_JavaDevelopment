package excercise1;

public class Book {
    private String title;
    private String author;
    private int isbn;
    private int copies;
    private int timesBorrowed;
    private int timesReturned;
    
    public Book(String title, String author, int isbn, int copies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.copies = copies;
        this.timesBorrowed = 0;
        this.timesReturned = 0;
    }

    public synchronized boolean borrow() {
        if (copies > 0) {
            copies--;
            timesBorrowed++;
            return true;
        }
        return false;
    }

    public synchronized void returned() {
        copies++;
        timesReturned++;
    }

    @Override
    public String toString() {
        return "Book: " + title + " by " + author + " [ISBN: " + isbn + "] Copies: " + copies;
    }

    public int getTimesBorrowed() {
        return timesBorrowed;
    }

    public int getTimesReturned() {
        return timesReturned;
    }

    public int borrowBook() {
        if (copies > 0) {
            copies--;
            timesBorrowed++;
            return copies;
        }
        return -1;
    }

    public String concatData() {
        return title + " " + author + " " + isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getIsbn() {
        return isbn;
    }

    public int getCopies() {
        return copies;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int addCopies(int copies) {
        this.copies += copies;
        return this.copies;
    }
}
