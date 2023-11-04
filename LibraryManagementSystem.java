import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrow() {
        isAvailable = false;
    }

    public void returnBook() {
        isAvailable = true;
    }
}

class Patron {
    private int id;
    private String name;

    public Patron(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

public class LibraryManagementSystem {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Patron> patrons = new ArrayList<>();

    public void addBook(int id, String title, String author) {
        Book book = new Book(id, title, author);
        books.add(book);
    }

    public void addPatron(int id, String name) {
        Patron patron = new Patron(id, name);
        patrons.add(patron);
    }

    public void borrowBook(int patronId, int bookId) {
        Patron patron = findPatronById(patronId);
        Book book = findBookById(bookId);

        if (patron != null && book != null && book.isAvailable()) {
            book.borrow();
            System.out.println("Book " + book.getTitle() + " borrowed by " + patron.getName());
        } else {
            System.out.println("Book not available or patron not found.");
        }
    }

    public void returnBook(int patronId, int bookId) {
        Patron patron = findPatronById(patronId);
        Book book = findBookById(bookId);

        if (patron != null && book != null && !book.isAvailable()) {
            book.returnBook();
            System.out.println("Book " + book.getTitle() + " returned by " + patron.getName());
        } else {
            System.out.println("Book not borrowed by this patron or patron not found.");
        }
    }

    public void calculateFine(int patronId) {
        
        System.out.println("Fine calculated for patron with ID " + patronId);
    }

    public void searchBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Book found - ID: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor());
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void generateReports() {
       System.out.println("Library Book Report:");
        System.out.println("---------------------------------------------------");
        System.out.printf("%-20s%-20s%-10s%n", "Title", "Author", "ID");
        System.out.println("---------------------------------------------------");

        for (Book book : books) {
            System.out.printf("%-20s%-20s%-10d%n", book.getTitle(), book.getAuthor(), book.getId());
        }
        System.out.println("Reports generated.");
    }

    public Patron findPatronById(int id) {
        for (Patron patron : patrons) {
            if (patron.getId() == id) {
                return patron;
            }
        }
        return null;
    }

    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        LibraryManagementSystem librarySystem = new LibraryManagementSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Add Patron");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Calculate Fine");
            System.out.println("6. Search Book");
            System.out.println("7. Generate Reports");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add Book
                    System.out.print("Enter Book ID: ");
                    int bookId = scanner.nextInt();
                    System.out.print("Enter Book Title: ");
                    scanner.nextLine(); // Consume newline
                    String bookTitle = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    librarySystem.addBook(bookId, bookTitle, author);
                    System.out.println("Book added successfully.");
                    break;

                case 2:
                    // Add Patron
                    System.out.print("Enter Patron ID: ");
                    int patronId = scanner.nextInt();
                    System.out.print("Enter Patron Name: ");
                    scanner.nextLine(); // Consume newline
                    String patronName = scanner.nextLine();
                    librarySystem.addPatron(patronId, patronName);
                    System.out.println("Patron added successfully.");
                    break;

                case 3:
                    // Borrow Book
                    System.out.print("Enter Patron ID: ");
                    patronId = scanner.nextInt();
                    System.out.print("Enter Book ID: ");
                    bookId = scanner.nextInt();
                    librarySystem.borrowBook(patronId, bookId);
                    break;

                case 4:
                    // Return Book
                    System.out.print("Enter Patron ID: ");
                    patronId = scanner.nextInt();
                    System.out.print("Enter Book ID: ");
                    bookId = scanner.nextInt();
                    librarySystem.returnBook(patronId, bookId);
                    break;

                case 5:
                    // Calculate Fine
                    System.out.print("Enter Patron ID: ");
                    patronId = scanner.nextInt();
                    librarySystem.calculateFine(patronId);
                    break;

                case 6:
                    // Search Book
                    System.out.print("Enter Book Title: ");
                    scanner.nextLine(); // Consume newline
                    bookTitle = scanner.nextLine();
                    librarySystem.searchBook(bookTitle);
                    break;

                case 7:
                    // Generate Reports
                    librarySystem.generateReports();
                    break;

                case 8:
                    System.out.println("Exiting Library Management System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);

        scanner.close();
    }
}