package excercise1;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private String name;
    private List<Book> books;
    private List<Patrons> patrons;

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
        this.patrons = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addBook(String title, String author, int isbn, int copies) {
        int indexIsbn = findBook(isbn);
        int indexTitle = findBook(title);
        if (indexIsbn != -1 && (indexIsbn == indexTitle) && cleanTitle(books.get(indexIsbn).getAuthor()).equals(cleanTitle(author))) {
            System.out.println("Book already in the library, adding " + copies + " copies");
            books.get(indexIsbn).addCopies(copies);
        } else if (indexIsbn != -1 && (indexIsbn != indexTitle)) {
            System.out.println("Book exists with different name, we can't add it with the same ISBN");
            System.out.println("The actual book with the " + isbn + " ISBN is:");
            DisplaySingleBook(books.get(indexIsbn));
        } else if (indexIsbn != -1 && (indexIsbn == indexTitle) && !cleanTitle(books.get(indexIsbn).getAuthor()).equals(cleanTitle(author))) {
            System.out.println("Book exists with different author, we can't add it with the same ISBN");
            System.out.println("The actual book with the " + isbn + " ISBN is:");
            DisplaySingleBook(books.get(indexIsbn));
        } else {
            books.add(new Book(title, author, isbn, copies));
        }
    }



    public void DisplayBooks() {
        if (books.size() == 0) {
            System.out.println("\nNo books in the library");
            return;
        }
        System.out.println("Books in the library: \n");
        for (Book book : books) {
            DisplaySingleBook(book);
        }
    }

    public void DisplaySingleBook(Book book) {
        System.out.println(book.getTitle() + " by " + book.getAuthor() + " ISBN: " + book.getIsbn() + " Copies: " + book.getCopies());
    }

    public String cleanTitle (String title) {
        for (int i = 0; i < title.length(); i++) {
            if (title.charAt(i) == ' ' || title.charAt(i) == '.' || title.charAt(i) == ',' || title.charAt(i) == ';' || title.charAt(i) == ':' || title.charAt(i) == '-' || title.charAt(i) == '_' || title.charAt(i) == '(' || title.charAt(i) == ')' || title.charAt(i) == '\'' || title.charAt(i) == '"' || title.charAt(i) == '!' || title.charAt(i) == '?' || title.charAt(i) == '¿' || title.charAt(i) == '¡') {
                title = title.substring(0, i) + title.substring(i + 1, title.length());
            } else if (title.charAt(i) == 'á') {
                title = title.substring(0, i) + "a" + title.substring(i + 1, title.length());
            } else if (title.charAt(i) == 'é') {
                title = title.substring(0, i) + "e" + title.substring(i + 1, title.length());
            } else if (title.charAt(i) == 'í') {
                title = title.substring(0, i) + "i" + title.substring(i + 1, title.length());
            } else if (title.charAt(i) == 'ó') {
                title = title.substring(0, i) + "o" + title.substring(i + 1, title.length());
            } else if (title.charAt(i) == 'ú') {
                title = title.substring(0, i) + "u" + title.substring(i + 1, title.length());
            } else if (title.charAt(i) == 'ñ') {
                title = title.substring(0, i) + "n" + title.substring(i + 1, title.length());
            } else if (title.charAt(i) == 'ü') {
                title = title.substring(0, i) + "u" + title.substring(i + 1, title.length());
            } else if (title.charAt(i) == 'ç') {
                title = title.substring(0, i) + "c" + title.substring(i + 1, title.length());
            }
        }
        return title;
    }

    public int findBook(String title) {
        for (int i = 0; i < books.size(); i++) {
            if (cleanTitle(books.get(i).getTitle().toLowerCase()).equals(cleanTitle(title.toLowerCase()))) {
                return i;
            }
        }
        return -1;
    }

    public int findBook(int ISBN) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn() == ISBN) {
                return i;
            }
        }
        return -1;
    }

    public void RemoveBook() {
        if (books.size() == 0) {
            System.out.println("No books in the library");
            return;
        }

        int index = findBook();
        books.remove(index);
    }

    public void editBook() {
        if (books.size() == 0) {
            System.out.println("No books in the library");
            return;
        }
        System.out.println("Enter the book ISBN: ");
        int ISBN = Integer.parseInt(System.console().readLine());
        int bookIndex = findBook(ISBN);
        if (bookIndex == -1) {
            System.out.println("Book not found");
            return;
        }
        DisplaySingleBook(books.get(bookIndex));
        System.out.println("Do you want to edit the title? (y/n)");
        String response = System.console().readLine();
        if (response.toLowerCase().equals("y")) {
            System.out.println("Enter the new title:");
            String newTitle = System.console().readLine();
            books.get(bookIndex).setTitle(newTitle);
        }
        System.out.println("Do you want to edit the author? (y/n)");
        response = System.console().readLine();
        if (response.toLowerCase().equals("y")) {
            System.out.println("Enter the new author:");
            String newAuthor = System.console().readLine();
            books.get(bookIndex).setAuthor(newAuthor);
        }
    }

    public int addPatron(String name, String email, String phone) {
        int id = patrons.size() + 1;
        patrons.add(new Patrons(id, name, email, phone));
        return 1;
    }

    public void displayPatrons() {
        for (int i = 0; i < patrons.size(); i++) {
            displaySinglePatron(patrons.get(i));
        }
    }
    
    public void displaySinglePatron(Patrons patron) {
        System.out.println(patron.getName() + " ID: " + patron.getId() + " Email: " + patron.getEmail() + " Phone: " + patron.getPhone());
    }

    public int findPatron(int id) {
        for (int i = 0; i < patrons.size(); i++) {
            if (patrons.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public int findPatron() {
        System.out.println("Enter the patron ID:");
        int patronID = Integer.parseInt(System.console().readLine());
        int patronIndex = findPatron(patronID);
        if (patronIndex == -1) {
            System.out.println("Patron not found");
            return -1;
        }
        return patronIndex;
    }

    public void editPatron() {
        int patronIndex = findPatron();
        if (patronIndex == -1) {
            return;
        }
        displaySinglePatron(patrons.get(patronIndex));
        System.out.println("Do you want to edit the name? (y/n)");
        String response = System.console().readLine();
        if (response.toLowerCase().equals("y")) {
            System.out.println("Enter the new name:");
            String newName = System.console().readLine();
            patrons.get(patronIndex).setName(newName);
        }
        System.out.println("Do you want to edit the email? (y/n)");
        response = System.console().readLine();
        if (response.toLowerCase().equals("y")) {
            System.out.println("Enter the new email:");
            String newEmail = System.console().readLine();
            patrons.get(patronIndex).setEmail(newEmail);
        }
        System.out.println("Do you want to edit the phone? (y/n)");
        response = System.console().readLine();
        if (response.toLowerCase().equals("y")) {
            System.out.println("Enter the new phone:");
            String newPhone = System.console().readLine();
            patrons.get(patronIndex).setPhone(newPhone);
        }
    }

    public int findBook() {
        System.out.println("Enter the book ISBN:");
        int bookISBN = Integer.parseInt(System.console().readLine());
        int bookIndex = findBook(bookISBN);
        if (bookIndex == -1) {
            System.out.println("Book not found");
            return -1;
        }
        return bookIndex;
    }

    public void borrowBook() {
        int patronIndex = findPatron();
        if (patronIndex == -1) {
            return;
        }

        int bookIndex = findBook();
        if (bookIndex == -1) {
            return;
        }

        if (books.get(bookIndex).getCopies() == 0) {
            System.out.println("No copies available");
            return;
        }

        patrons.get(patronIndex).addBook(books.get(bookIndex));
        books.get(bookIndex).addCopies(-1);
        return;
    }

    public void returnBook() {
        int patronIndex = findPatron();
        if (patronIndex == -1) {
            return;
        }

        System.out.println("Enter the book ISBN:");
        int bookISBN = Integer.parseInt(System.console().readLine());
        int confirmation = patrons.get(patronIndex).returnBook(bookISBN);
        if (confirmation == -1) {
            return;
        }

        books.get(findBook(bookISBN)).addCopies(1);
        return;
    }

    public void booksPerPatron() {
        System.out.println("Do you want to display which books each patron has borrowed? (y/n)");
        String response = System.console().readLine();
        for (Patrons patron : patrons) {
            System.out.println("Books borrowed by " + patron.getName() + ":" + patron.getBooks().size() + "\n");
            if (response.toLowerCase().equals("n")) {
                continue;
            }
            for (Book book : patron.getBooks()) {
                DisplaySingleBook(book);
            }
        }
    }

    public void searchBook() {
        System.out.println("Enter the book title, author or ISBN: ");
        String search = System.console().readLine();
        search = cleanTitle(search.toLowerCase());

        for (Book book : books) {
            if (deepSearch(search, cleanTitle(book.concatData().toLowerCase())) == 1) {
                DisplaySingleBook(book);
            }
        }
    }

    public int deepSearch(String search, String bookString) {
        if (search.length() > bookString.length()) {
            return 0;
        }
        for (int i = 0; i <= bookString.length() - search.length(); i++) {
            if (bookString.substring(i, i + search.length()).equals(search)) {
                return 1;
            }
        }
        return 0;
    }

    public void findPatronInformation() {
        int patronIndex = findPatron();
        if (patronIndex == -1) {
            return;
        }

        displaySinglePatron(patrons.get(patronIndex));
        System.out.println(patrons.get(patronIndex).getBooks().size() + " books borrowed: \n");
        for (Book book : patrons.get(patronIndex).getBooks()) {
            DisplaySingleBook(book);
        }
    }
}
