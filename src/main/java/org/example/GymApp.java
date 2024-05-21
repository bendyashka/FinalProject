package org.example;

import java.util.List;
import java.util.Scanner;

public class GymApp {
    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "0000";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);



        while (true) {
            System.out.println("Welcome! Please do authorization " +
                    "Please choose your role: " +
                    "1 - Visitor " +
                    "2 - Trainer " +
                    "3 - Admin ");
            int login = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if(login == 1) {
                authenticateVisitor(scanner);
            } else if(login == 2) {
                authenticateTrainer(scanner);
            } else if(login == 3) {
                authenticateAdmin(scanner);
            } else {
                System.out.println("Error, please choose a valid option.");
            }
        }
    }

    private static void authenticateVisitor(Scanner scanner) {
        System.out.println("Please enter your login (First Name) and password (Last Name):");
        System.out.print("Enter username: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter password: ");
        String lastName = scanner.nextLine();

        if(CRUDUtils.authenticateVisitor(firstName, lastName)) {
            System.out.println("Authentication Successful");

        } else {
            System.out.println("Authentication Failed");
        }
    }

    private static void authenticateTrainer(Scanner scanner) {
        System.out.println("Please enter your login (First Name) and password (Last Name):");
        System.out.print("Enter username: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter password: ");
        String lastName = scanner.nextLine();

        if(CRUDUtils.authenticateTrainer(firstName, lastName)) {
            System.out.println("Authentication Successful");

        } else {
            System.out.println("Authentication Failed");
        }
    }

    private static void authenticateAdmin(Scanner scanner) {
        System.out.println("Please enter your admin login and password:");
        System.out.print("Enter username: ");
        String login = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (ADMIN_LOGIN.equals(login) && ADMIN_PASSWORD.equals(password)) {
            System.out.println("Authentication Successful");
            showAdminMenu(scanner);
        } else {
            System.out.println("Authentication Failed");
        }
    }

    private static void showAdminMenu(Scanner scanner) {
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.println("Please choose an action:");
            System.out.println("1 - Add Visitor");
            System.out.println("2 - Add Trainer");
            System.out.println("3 - Show List Visitors");
            System.out.println("4 - Show List Trainers");
            System.out.println("5 - Exit");

            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    addVisitor(scanner);
                } else if (choice == 2) {
                    addTrainer(scanner);
                } else if (choice == 3) {
                    showList();
                } else if (choice == 4) {
                    showListTrainer();
                } else if (choice == 5) {
                    System.out.println("Exiting program...");
                    break;
                } else {
                    System.out.println("Error, please choose a valid option.");
                }
            } else {
                System.out.println("Error, please enter a valid number.");
                scanner.next();
            }
        }
    }

    private static void showListTrainer() {
        System.out.println("List of Trainers:");
        List<Trainer> trainers = Trainer.getTrainerList();
        for (Trainer trainer : trainers) {
            System.out.println(trainer);
        }
    }

    private static void addVisitor(Scanner scanner) {
        System.out.println("Name: ");
        String name = scanner.nextLine();

        System.out.println("Lastname: ");
        String lastname = scanner.nextLine();

        System.out.println("Date of entering (yyyy-MM-dd): ");
        String date = scanner.nextLine();

        System.out.println("Status of Abonement (true/false): ");
        boolean active = scanner.nextBoolean();

        System.out.println("Group ID:");
        int groupId = scanner.nextInt();

        CRUDUtils.saveVisitorData(name, lastname, date, active, groupId);
    }

    private static void addTrainer(Scanner scanner) {
        System.out.println("Name: ");
        String name = scanner.nextLine();

        System.out.println("Lastname: ");
        String lastname = scanner.nextLine();

        System.out.println("Leader of Group:");
        int groupId = scanner.nextInt();

        CRUDUtils.saveTrainerData(name, lastname, groupId);
    }

    private static void showList() {
        System.out.println("List of Visitors:");
        List<Visitor> visitors = Visitor.getVisitorList();
        for (Visitor visitor : visitors) {
            System.out.println(visitor);
        }
    }
}
