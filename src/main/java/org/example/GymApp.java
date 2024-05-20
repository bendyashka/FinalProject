package org.example;

import java.util.Scanner;
import java.util.InputMismatchException;

public class GymApp {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please choose an action:");
            System.out.println("1 - Add Visitor");
            System.out.println("2 - Add Trainer");

            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
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
                    break;
                } else if (choice == 2) {
                    System.out.println("Name: ");
                    String name = scanner.nextLine();

                    System.out.println("Lastname: ");
                    String lastname = scanner.nextLine();

                    System.out.println("Leader of Group:");
                    int groupId = scanner.nextInt();

                    CRUDUtils.saveTrainerData(name, lastname, groupId);
                    break;
                } else {
                    System.out.println("Error, please choose 1 or 2.");
                }
            } else {
                System.out.println("Error, please enter a valid number.");
                scanner.next();
            }
        }
    }
}
