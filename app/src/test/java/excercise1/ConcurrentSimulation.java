package excercise1;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentSimulation {
    public static void main(String[] args) {
        Library library = new Library("Concurrent Library");

        library.addBook("Concurrencia en Java", "Autor 1", 1001, 3);
        library.addBook("Multithread en Java", "Autor 2", 1002, 2);
        library.addBook("Java a fondo", "Autor 3", 1003, 4);
        library.addBook("Java Concurrente", "Autor 4", 1004, 1);
        library.addBook("Java Concurrencia", "Autor 5", 1005, 5);
        library.addBook("Java Concurrente 2", "Autor 6", 1006, 0);
        library.addBook("Java Concurrente 3", "Autor 7", 1007, 2);
        library.addBook("Java Concurrente 4", "Autor 8", 1008, 3);
        library.addBook("Java Concurrente 5", "Autor 9", 1009, 4);

        List<Thread> patronThreads = new ArrayList<>();

        Patron patron1 = new Patron(1, "Gustavo", "gus@mail.com", "111-111-111", library);
        Patron patron2 = new Patron(2, "Dano", "dano@mail.com", "222-222-222", library);
        Patron patron3 = new Patron(3, "Pablo", "pablo@mail.com", "333-333-333", library);
        Patron patron4 = new Patron(4, "Beto", "beto@mail.com", "444-444-444", library);
        Patron patron5 = new Patron(5, "Hakon", "hakon@gmail.com", "555-555-555", library);

        library.addPatron(patron1);
        library.addPatron(patron2);
        library.addPatron(patron3);
        library.addPatron(patron4);
        library.addPatron(patron5);

        // Crear e iniciar hilos para cada patron
        Thread t1 = new Thread(patron1);
        Thread t2 = new Thread(patron2);
        Thread t3 = new Thread(patron3);
        Thread t4 = new Thread(patron4);
        Thread t5 = new Thread(patron5);

        patronThreads.add(t1);
        patronThreads.add(t2);
        patronThreads.add(t3);
        patronThreads.add(t4);
        patronThreads.add(t5);

        for (Thread t : patronThreads) {
            t.start();
        }

        for (Thread t : patronThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        System.out.println("\n=== Final Library State ===");
        library.DisplayBooks();
    }
}