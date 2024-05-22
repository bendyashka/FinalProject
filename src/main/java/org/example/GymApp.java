package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GymApp {
    // ADMIN_LOGIN = "admin";
    // ADMIN_PASSWORD = "0000";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome! Please do authorization " +
                    "Please choose your role: " +
                    "1 - Visitor " +
                    "2 - Trainer " +
                    "3 - Admin " +
                    "9 - Exit ");
            int login = scanner.nextInt();
            scanner.nextLine();

            if (login == 1) {
                authenticateVisitor(scanner);
            } else if (login == 2) {
                authenticateTrainer(scanner);
            } else if (login == 3) {
                authenticateAdmin(scanner);
            } else if (login == 9) {
                break;
            } else {
                System.out.println("Error, please choose a valid option.");
            }
        }
    }

    private static void authenticateVisitor(Scanner scanner) {
        System.out.println("Please enter your login and password:");
        System.out.print("Enter login: ");
        String loginMember = scanner.nextLine();
        System.out.print("Enter password: ");
        String passwordMember = scanner.nextLine();

        if (CRUDUtils.authenticateVisitor(loginMember, passwordMember)) {
            System.out.println("Authentication Successful");

        } else {
            System.out.println("Authentication Failed");
        }
    }

    private static void authenticateTrainer(Scanner scanner) {
        System.out.println("Please enter your login and password:");
        System.out.print("Enter login: ");
        String loginTrainer = scanner.nextLine();
        System.out.print("Enter password: ");
        String passwordTrainer = scanner.nextLine();

        if (CRUDUtils.authenticateTrainer(loginTrainer, passwordTrainer)) {
            System.out.println("Authentication Successful");
            showTrainerMenu(scanner);
        } else {
            System.out.println("Authentication Failed");
        }
    }

    private static void showTrainerMenu(Scanner scanner) {
        while (true) {
            System.out.println("Trainer Menu:");
            System.out.println("1 - Update Room Status");
            System.out.println("2 - Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    updateRoomStatus(scanner);
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }




    private static void authenticateAdmin(Scanner scanner) {
        System.out.println("Please enter your admin login and password:");
        System.out.print("Enter username: ");
        String login = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (CRUDUtils.authenticateAdmin(login, password)) {
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
            System.out.println("5 - Add Room");
            System.out.println("6 - Change Admin Password");
            System.out.println("7 - Exit");

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
                    addRoom(scanner);
                } else if (choice == 6) {
                    changeAdminPassword(scanner);
                } else if (choice == 7) {
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


    private static void changeAdminPassword(Scanner scanner) {
        System.out.print("Enter new admin password: ");
        String newPassword = scanner.nextLine();
        CRUDUtils.updateAdminPassword(newPassword);
    }

    private static void showListTrainer() {
        System.out.println("List of Trainers:");
        List<Trainer> trainers = CRUDUtils.getTrainerList();
        for (Trainer trainer : trainers) {
            System.out.println(trainer);
        }
    }

    private static void addVisitor(Scanner scanner) {
        System.out.println("Name: ");
        String name = scanner.nextLine();

        System.out.println("Lastname: ");
        String lastname = scanner.nextLine();

        System.out.println("Date of entering (dd-mm-yy): ");
        String date = scanner.nextLine();

        System.out.println("Status of Abonement (true/false): ");
        boolean active = scanner.nextBoolean();
        scanner.nextLine();

        System.out.println("Login: ");
        String loginMember = scanner.nextLine();

        System.out.println("Password: ");
        String passwordMember = scanner.nextLine();

        System.out.println("Group ID:");
        int groupId = scanner.nextInt();

        CRUDUtils.saveVisitorData(name, lastname, date, active, groupId, loginMember, passwordMember);
    }

    private static void addTrainer(Scanner scanner) {
        System.out.println("Name: ");
        String name = scanner.nextLine();

        System.out.println("Lastname: ");
        String lastname = scanner.nextLine();

        System.out.println("Leader of Group:");
        int groupId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Login: ");
        String loginTrainer = scanner.nextLine();

        System.out.println("Password: ");
        String passwordTrainer = scanner.nextLine();

        CRUDUtils.saveTrainerData(name, lastname, groupId, loginTrainer, passwordTrainer);
    }


    private static void showList() {
        System.out.println("List of Visitors:");
        List<Visitor> visitors = Visitor.getVisitorList();
        for (Visitor visitor : visitors) {
            System.out.println(visitor);
        }
    }
    private static void addRoom(Scanner scanner) {
        System.out.println("Enter Room ID: ");
        int roomID = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter Trainer ID for the room: ");
        int trainerID = Integer.parseInt(scanner.nextLine());

        CRUDUtils.addRoom(roomID, trainerID);
    }
    private static void updateRoomStatus(Scanner scanner) {
        System.out.println("Enter RoomID: ");
        int roomId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.println("Enter your available hours (type 'Finish' when done): ");
        List<String> availableHours = new ArrayList<>();
        String input;
        while (!(input = scanner.nextLine()).equalsIgnoreCase("Finish")) {
            availableHours.add(input);
        }


        String[] allHours = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] hours = availableHours.toArray(new String[0]);
        String[] statuses = new String[12];
        Arrays.fill(statuses, "CLOSED");


        for (String hour : hours) {
            statuses[Integer.parseInt(hour) - 1] = "OPEN";
        }


        CRUDUtils.updateRoomStatus(roomId, date, allHours, statuses);

        System.out.println("Room status updated successfully.");
    }

}
