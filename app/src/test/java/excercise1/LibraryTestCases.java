package excercise1;

public class LibraryTestCases {

    // Test Case 1:
    // Múltiples patronos intentan tomar prestado el mismo libro (con una única copia).
    // Se espera que solo uno logre el préstamo y los demás reciban el mensaje de "No hay copias disponibles".
    public static void testMultipleBorrowSameBook() {
        System.out.println("Test Case 1: Múltiples patronos intentando tomar prestado el mismo libro simultáneamente");
        Library library = new Library("Test Library - Single Copy");
        library.addBook("Unique Book", "Unique Author", 5000, 1);

        Patron p1 = new Patron(1, "Gustavo", "gus@mail.com", "111-111-111", library);
        Patron p2 = new Patron(2, "Dano", "dano@mail.com", "222-222-222", library);
        Patron p3 = new Patron(3, "Pablo", "pablo@mail.com", "333-333-333", library);
        Patron p4 = new Patron(4, "Beto", "beto@mail.com", "444-444-444", library);
        Patron p5 = new Patron(5, "Hakon", "hakon@gmail.com", "555-555-555", library);

        library.addPatron(p1);
        library.addPatron(p2);
        library.addPatron(p3);
        library.addPatron(p4);
        library.addPatron(p5);

        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        Thread t3 = new Thread(p3);
        Thread t4 = new Thread(p4);
        Thread t5 = new Thread(p5);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }

        System.out.println("\nEstado final para 'Unique Book':");
        library.DisplayBooks();
        System.out.println("---------------------------------------------------\n");
    }




    // Test Case 2:
    // Un patron toma prestado un libro varias veces (en este ejemplo, el mismo patron lo toma
    // en sucesivas operaciones) y luego se ejecuta su thread, lo que simula devoluciones en secuencia aleatoria.
    // Se espera que, al finalizar, la cantidad de copias en la biblioteca se incremente acorde a las devoluciones.

    public static void testRandomReturnSequence() {
        System.out.println("Test Case 2: Patron devolviendo libros en secuencia aleatoria");
        Library library = new Library("Test Library - Random Return");
        library.addBook("Popular Book", "Famous Author", 6000, 3);


        Patron p1 = new Patron(1, "Gustavo", "gus@gmail.com", "444-444-444", library);
        library.addPatron(p1);
        library.simulateBorrow(p1, 6000);
        library.simulateBorrow(p1, 6000);
        library.simulateBorrow(p1, 6000);

        Thread t1 = new Thread(p1);
        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }

        System.out.println("\nEstado final después de la simulación aleatoria de devoluciones:");
        library.DisplayBooks();
        System.out.println("---------------------------------------------------\n");
    }





    // Test Case 3:
    // Se crean dos patronos que realizan operaciones de préstamo y devolución de forma aleatoria en dos libros.
    // Se muestra la salida de logging para evidenciar el estado intermedio y final del inventario.

    public static void testLoggingState() {
        System.out.println("Test Case 3: Logging del estado de la biblioteca después de operaciones concurrentes");
        Library library = new Library("Test Library - Logging");
        library.addBook("Log Book A", "Author A", 7000, 2);
        library.addBook("Log Book B", "Author B", 7001, 2);

        Patron p1 = new Patron(1, "Dano", "dano@gmail.com", "555-555-555", library);
        Patron p2 = new Patron(2, "Pablo", "pablo@gmail.com", "666-666-666", library);

        library.addPatron(p1);
        library.addPatron(p2);

        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }

        System.out.println("\nEstado final de la biblioteca en 'Test Library - Logging':");
        library.DisplayBooks();
        System.out.println("---------------------------------------------------\n");
    }

    public static void main(String[] args) {
        System.out.println("=== Ejecución de Casos de Prueba ===\n");

        testMultipleBorrowSameBook();
        testRandomReturnSequence();
        testLoggingState();

        System.out.println("=== Fin de los Casos de Prueba ===");
    }
}
