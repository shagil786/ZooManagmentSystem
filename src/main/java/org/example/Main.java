package org.example;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Create a list to store issued tickets
        List<Ticket> tickets = new ArrayList<>();
        // Create an instance of ZooTicketManagement to manage ticket operations
        ZooTicketManagement ticketManagement = new ZooTicketManagement();

        int choice; // Variable to store the user's menu choice
        do {
            System.out.println("\nZoo Ticketing System");
            System.out.println("1. Issue Ticket");
            System.out.println("2. Display Ticket Details For Verification");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt(); // Read user's choice

            // Switch statement to perform actions based on user's choice
            switch (choice) {
                case 1:
                    // Issue a new ticket
                    ticketManagement.issueTicket(scanner, tickets);
                    break;
                case 2:
                    // Display ticket details for verification
                    ticketManagement.displayTicketDetails(scanner, tickets);
                    break;
                case 3:
                    // Exit the program
                    System.out.println("Exiting the Zoo Ticketing System. Thank you!");
                    scanner.close();
                    System.exit(0);
                default:
                    // Handle invalid choices
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (true);  // Continue the loop until the user chooses to exit (option 3)
    }
}