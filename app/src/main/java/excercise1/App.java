package excercise1;

public class App {
    public void menu() {
        System.out.println("Welcome to the library system, enter the name of the library: ");
        String name = System.console().readLine();
        Library library = new Library(name);
        int option = displayMainMenu(name);

        while (option != 5) {
            switch (option) {
                case 1:
                    bookManagement(library);
                    break;
                case 2:
                    patronManagement(library);
                    break;
                case 3:
                    borrowAndReturnBooks(library);
                    break;
                case 4:
                    searchBooksOrPatrons(library);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
            option = displayMainMenu(name);
        }
    }

    public int displayMainMenu(String name) {
        System.out.println("\n\n\n\n\n\n\n\nThis is the " + name + " library system, enter the number of the option: ");
        System.out.println("1. Book management");
        System.out.println("2. Patron management");
        System.out.println("3. Borrow and return books");
        System.out.println("4. Search for books or patrons");
        System.out.println("5. Exit");
        System.out.println("\nEnter the number of the option: ");
        
        int option = Integer.parseInt(System.console().readLine());
        return option;
    }

    public int bookManagement(Library library) {
        System.out.println("\n\n\nEnter the number of the option: ");
        System.out.println("1. Add book");
        System.out.println("2. Remove book");
        System.out.println("3. Display all books");
        System.out.println("4. Edit book");
        System.out.println("5. Exit");
        
        int option = Integer.parseInt(System.console().readLine());
        switch (option) {
            case 1:
                System.out.println("Enter the title of the book: ");
                String title = System.console().readLine();

                System.out.println("Enter the author of the book: ");
                String author = System.console().readLine();
                
                System.out.println("Enter the ISBN of the book: ");
                int isbn = Integer.parseInt(System.console().readLine());

                System.out.println("Enter the number of copies of the book: ");
                int copies = Integer.parseInt(System.console().readLine());

                library.addBook(title, author, isbn, copies);
                break;
            case 2:
                library.RemoveBook();
                break;
            case 3:
                library.DisplayBooks();
                break;
            case 4:
                library.editBook();
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
        return option;
    }

    public int patronManagement(Library library) {
        System.out.println("\n\n\nEnter the number of the option: ");
        System.out.println("1. Add patron");
        System.out.println("2. Display all patrons");
        System.out.println("3. Edit patron");
        System.out.println("4. Exit");
        
        int option = Integer.parseInt(System.console().readLine());
        switch (option) {
            case 1:
                System.out.println("Enter the name of the patron: ");
                String name = System.console().readLine();

                System.out.println("Enter the email of the patron: ");
                String email = System.console().readLine();
                
                System.out.println("Enter the phone of the patron: ");
                String phone = System.console().readLine();

                library.addPatron(name, email, phone);
                break;
            case 2:
                library.displayPatrons();
                break;
            case 3:
                library.editPatron();
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
        return option;
    }

    public int borrowAndReturnBooks(Library library) {
        System.out.println("\n\n\nEnter the number of the option: ");
        System.out.println("1. Borrow book");
        System.out.println("2. Return book");
        System.out.println("3. Show books per patron");
        System.out.println("4. Exit");
        
        int option = Integer.parseInt(System.console().readLine());
        switch (option) {
            case 1:
                library.borrowBook();
                break;
            case 2:
                library.returnBook();
                break;
            case 3:
                library.booksPerPatron();
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
        return option;
    }

    public int searchBooksOrPatrons(Library library) {
        System.out.println("\n\n\nEnter the number of the option: ");
        System.out.println("1. Search for books");
        System.out.println("2. Search for patrons");
        System.out.println("3. Exit");
        
        int option = Integer.parseInt(System.console().readLine());
        switch (option) {
            case 1:
                library.searchBook();
                break;
            case 2:
                library.findPatronInformation();
                break;
            default:
                System.out.println("Invalid option");
                break;
        }

        return option;
    }

    public static void main(String[] args) {
        App app = new App();
        app.menu();
    }
}