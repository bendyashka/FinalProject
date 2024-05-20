package org.example;

import java.util.List;
import java.util.Scanner;

public class GymApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please choose an action:");
            System.out.println("1 - Add Visitor");
            System.out.println("2 - Add Trainer");
            System.out.println("3 - Show List");
            System.out.println("4 - Exit");

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

