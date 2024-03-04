import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class LibrarySystem {
    private Map<String, String> users;
    private Map<String, Book> books;

    public LibrarySystem() {
        users = new HashMap<>();
        books = new HashMap<>();
    }

    public boolean registerUser(String username, String password) {
        if (!users.containsKey(username)) {
            users.put(username, password);
            return true;
        }
        return false;
    }

    public boolean authenticateUser(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public void addBook(String title, String author) {
        books.put(title, new Book(title, author));
    }

    public Book findBook(String title) {
        return books.get(title);
    }

    public void borrowBook(String title) {
        Book book = books.get(title);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Book not available for borrowing.");
        }
    }

    public void returnBook(String title) {
        Book book = books.get(title);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Invalid book or already returned.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Library System!");
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();
                    if (library.registerUser(regUsername, regPassword)) {
                        System.out.println("Registration successful.");
                    } else {
                        System.out.println("Username already exists. Try again.");
                    }
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    if (library.authenticateUser(username, password)) {
                        System.out.println("Authentication successful.");
                        while (true) {
                            System.out.println("\nSelect an option:");
                            System.out.println("1. Add Book");
                            System.out.println("2. Search Book");
                            System.out.println("3. Borrow Book");
                            System.out.println("4. Return Book");
                            System.out.println("5. Logout");
                            int userChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            switch (userChoice) {
                                case 1:
                                    System.out.print("Enter book title: ");
                                    String bookTitle = scanner.nextLine();
                                    System.out.print("Enter author: ");
                                    String author = scanner.nextLine();
                                    library.addBook(bookTitle, author);
                                    break;
                                case 2:
                                    System.out.print("Enter book title to search: ");
                                    String searchTitle = scanner.nextLine();
                                    Book foundBook = library.findBook(searchTitle);
                                    if (foundBook != null) {
                                        System.out.println("Book found:");
                                        System.out.println("Title: " + foundBook.getTitle());
                                        System.out.println("Author: " + foundBook.getAuthor());
                                        System.out.println("Availability: "
                                                + (foundBook.isAvailable() ? "Available" : "Not available"));
                                    } else {
                                        System.out.println("Book not found.");
                                    }
                                    break;
                                case 3:
                                    System.out.print("Enter book title to borrow: ");
                                    String borrowTitle = scanner.nextLine();
                                    library.borrowBook(borrowTitle);
                                    break;
                                case 4:
                                    System.out.print("Enter book title to return: ");
                                    String returnTitle = scanner.nextLine();
                                    library.returnBook(returnTitle);
                                    break;
                                case 5:
                                    System.out.println("Logged out.");
                                    break;
                                default:
                                    System.out.println("Invalid option. Try again.");
                            }

                            if (userChoice == 5) {
                                break;
                            }
                        }
                    } else {
                        System.out.println("Authentication failed.");
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
