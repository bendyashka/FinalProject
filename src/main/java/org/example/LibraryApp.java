package org.example;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import org.example.CRUDUtils;

import static org.example.CRUDUtils.saveVisitor;

// login: admin
// password: 0000

public class LibraryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Library Management System!\n" +
                    "Please choose your role:\n" +
                    "1 - Visitor\n" +
                    "2 - Worker\n" +
                    "3 - Admin\n" +
                    "9 - Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                authenticateVisitor(scanner);
            } else if (choice == 2) {
                authenticateWorker(scanner);
            } else if (choice == 3) {
                authenticateAdmin(scanner);
            } else if (choice == 9) {
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void authenticateVisitor(Scanner scanner) {
        System.out.println("Please enter your login:");
        String login = scanner.nextLine();

        System.out.println("Please enter your password:");
        String password = scanner.nextLine();

        if (Visitor.authenticateVisitor(login, password)) {
            System.out.println("Welcome, " + login + "!");
            showVisitorMenu(scanner);
        } else {
            System.out.println("Authentication failed. Incorrect login or password.");
        }
    }


    private static void showVisitorMenu(Scanner scanner) {
        while (true) {
            System.out.println("Visitor Menu:\n" +
                    "1 - View Available Books\n" +
                    "2 - Borrow a Book\n" +
                    "3 - Return a Book\n" +
                    "9 - Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showAvailableBooks();
                    break;
                case 2:
                    borrowBook(scanner);
                    break;
                case 3:
                    returnBook(scanner);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void authenticateWorker(Scanner scanner) {
        System.out.println("Please enter your login and password");
        System.out.print("Login: ");
        String name = scanner.nextLine();
        System.out.print("Password: ");
        String lastName = scanner.nextLine();

        if (CRUDUtils.authenticateWorker(name, lastName)) {
            System.out.println("Welcome, Worker " + name + "!");
            showWorkerMenu(scanner);
        } else {
            System.out.println("Authentication failed.");
        }
    }

    private static void showWorkerMenu(Scanner scanner) {
        while (true) {
            System.out.println("Worker Menu:\n" +
                    "1 - Add a Book\n" +
                    "2 - View All Books\n" +
                    "3 - Update Book Availability\n" +
                    "9 - Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    showAllBooks();
                    break;
                case 3:
                    updateBookAvailability(scanner);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void authenticateAdmin(Scanner scanner) {
        System.out.println("Please enter your admin username and password:");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (CRUDUtils.authenticateAdmin(username, password)) {
            System.out.println("Welcome, Admin!");
            showAdminMenu(scanner);
        } else {
            System.out.println("Authentication failed.");
        }
    }

    private static void showAdminMenu(Scanner scanner) {
        while (true) {
            System.out.println("Admin Menu:\n" +
                    "1 - Add a Worker\n" +
                    "2 - Update Admin Password\n" +
                    "3 - Add a User\n" +
                    "9 - Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addWorker(scanner);
                    break;
                case 2:
                    updateAdminPassword(scanner);
                    break;
                case 3:
                    saveVisitor(scanner);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void saveVisitor(Scanner scanner) {
        System.out.println("Enter visitor's first name:");
        String name = scanner.nextLine();

        System.out.println("Enter visitor's last name:");
        String lastName = scanner.nextLine();

        System.out.println("Enter visitor's login:");
        String login = scanner.nextLine();

        System.out.println("Enter visitor's password:");
        String password = scanner.nextLine();

        System.out.println("Enter membership expiry date (YYYY-MM-DD):");
        String membershipExpiryDate = scanner.nextLine();

        System.out.println("Is the membership active? (true/false):");
        boolean membershipActive = scanner.nextBoolean();
        scanner.nextLine();


        CRUDUtils.saveVisitor(name, lastName, login, password, membershipExpiryDate, membershipActive);

        System.out.println("Visitor saved successfully!");
    }

    private static void showAvailableBooks() {
        List<Book> books = CRUDUtils.getBookList();
        System.out.println("Available Books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book);
            }
        }
    }

    public static void borrowBook(Scanner scanner) {
        System.out.println("Enter the ID of the book to borrow:");
        int bookId = scanner.nextInt();
        scanner.nextLine();


        System.out.println("Enter the return date (YYYY-MM-DD):");
        String returnDate = scanner.nextLine();


        if (returnDate.isEmpty()) {
            System.out.println("Invalid date. Please enter a valid return date.");
            return;
        }


        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = sdf.parse(returnDate);
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());


            CRUDUtils.updateBookAvailability(bookId, false, sqlDate);
            System.out.println("Book borrowed successfully! Return date set.");
        } catch (java.text.ParseException e) {
            System.out.println("Error: Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    private static void returnBook(Scanner scanner) {
        System.out.println("Enter the ID of the book you want to return:");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        if (CRUDUtils.returnBook(bookId)) {
            System.out.println("You have successfully returned the book.");
        } else {
            System.out.println("Failed to return the book. Please try again.");
        }
    }

    private static void addBook(Scanner scanner) {
        System.out.println("Enter book title:");
        String title = scanner.nextLine();

        System.out.println("Enter book price:");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Is the book available? (true/false):");
        boolean isAvailable = scanner.nextBoolean();
        scanner.nextLine();

        System.out.println("Enter available date (YYYY-MM-DD) or leave blank:");
        String availableDate = scanner.nextLine();


        if (availableDate.isEmpty()) {
            availableDate = null;
        }

        CRUDUtils.saveBook(title, price, isAvailable, availableDate);
    }


    private static void showAllBooks() {
        List<Book> books = CRUDUtils.getBookList();
        System.out.println("All Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void updateBookAvailability(Scanner scanner) {
        System.out.println("Enter the ID of the book to update:");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Is the book available? (true/false):");
        boolean isAvailable = scanner.nextBoolean();
        scanner.nextLine();

        System.out.println("Enter available date (YYYY-MM-DD):");
        String availableDate = scanner.nextLine();

        CRUDUtils.updateBookAvailability(bookId, isAvailable, Date.valueOf(availableDate));
    }

    private static void addWorker(Scanner scanner) {
        System.out.println("Enter worker's name:");
        String name = scanner.nextLine();

        System.out.println("Enter worker's last name:");
        String lastName = scanner.nextLine();


        System.out.println("Enter worker's login:");
        String login = scanner.nextLine();

        System.out.println("Enter worker's password:");
        String password = scanner.nextLine();


        CRUDUtils.saveWorker(name, lastName, login, password);
    }

    private static void updateAdminPassword(Scanner scanner) {
        System.out.println("Enter new admin password:");
        String newPassword = scanner.nextLine();

        CRUDUtils.updateAdminPassword("admin", newPassword);
    }
}
