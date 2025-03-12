package excercise1;

import java.util.ArrayList;
import java.util.List;

public class Patrons {
    private int id;
    private String name;
    private String email;
    private String phone;
    private List<Book> books;

    public Patrons(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.books = new ArrayList<>();
    }

    public int addBook (Book book) {
        books.add(book);
        return books.size();
    }

    public int findBook (int isbn) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn() == isbn) {
                return i;
            }
        }
        return -1;
    }

    public int returnBook (int isbn) {
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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
