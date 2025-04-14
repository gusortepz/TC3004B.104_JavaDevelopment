package excercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Patron implements Runnable {
    private int id;
    private String name;
    private String email;
    private String phone;
    private List<Book> books;
    private Library library;
    private Random random;

    public Patron(int id, String name, String email, String phone, Library library) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.library = library;
        this.books = new ArrayList<>();
        this.random = new Random();
    }

    public synchronized List<Book> getBorrowedBooks() { 
        return books;
    }

    public synchronized void addBorrowedBook(Book book) {
        books.add(book);
    }

    public synchronized boolean removeBorrowedBook(int isbn) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn() == isbn) {
                books.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(random.nextInt(1000) + 500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (random.nextBoolean()) {
                List<Book> libraryBooks = library.getBooks();
                if (!libraryBooks.isEmpty()) {
                    Book book = libraryBooks.get(random.nextInt(libraryBooks.size()));
                    library.simulateBorrow(this, book.getIsbn());
                }
            } else {
                if (!books.isEmpty()) {
                    Book bookToReturn = books.get(random.nextInt(books.size()));
                    library.simulateReturn(this, bookToReturn.getIsbn());
                }
            }
        }
    }

    public synchronized int addBook(Book book) {
        addBorrowedBook(book);
        return books.size();
    }

    public synchronized int findBook(int isbn) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn() == isbn) {
                return i;
            }
        }
        return -1;
    }

    public synchronized int returnBook(int isbn) {
        int index = findBook(isbn);
        if (index == -1) {
            System.out.println("Book not found");
            return -1;
        }
        Book bookToReturn = books.get(index);
        books.remove(index);
        System.out.println("Book " + bookToReturn.getTitle() + " returned");
        return index;
    }

    public int getId() {
        return id;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized String getEmail() {
        return email;
    }

    public synchronized String getPhone() {
        return phone;
    }

    public synchronized List<Book> getBooks() {
        return books;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized void setEmail(String email) {
        this.email = email;
    }

    public synchronized void setPhone(String phone) {
        this.phone = phone;
    }
}
