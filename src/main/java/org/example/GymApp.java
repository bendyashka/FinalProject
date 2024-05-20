package org.example;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class GymApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("name");
        String name = scanner.nextLine();

        System.out.println("lastname");
        String lastname = scanner.nextLine();
        System.out.println("date");
        String date = scanner.nextLine();

        System.out.println("active?");
        Boolean active = scanner.nextBoolean();
        System.out.println("group id");
        int groupId = scanner.nextInt();

        CRUDUtils.saveVisitorData(name, lastname, date, active, groupId);




    }
}




