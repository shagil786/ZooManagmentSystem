package org.example;
import org.jetbrains.annotations.NotNull;
import java.util.UUID;
import java.util.List;
import java.util.Scanner;


public class ZooTicketManagement {

    // Issuing a new ticket
    public void issueTicket(@NotNull Scanner scanner, @NotNull List<Ticket> tickets) {
        Ticket ticket = new Ticket();
        tickets.add(ticket);

        // Print a success message with the issued ticket ID
        System.out.println("Ticket issued successfully! Ticket ID: " + ticket.getUuid());
        System.out.println("Copy the Ticket ID to display the ticket for verification!");

        // Prompt the user to enter the number of guests for the ticket
        System.out.print("Enter the number of guests: ");
        int numberOfGuests = scanner.nextInt();

        // Prompt the user to enter the age of each guest and add them to the ticket
        for (int i = 0; i < numberOfGuests; i++) {
            System.out.print("Enter age of Guest " + (i + 1) + ": ");
            int age = scanner.nextInt();
            ticket.addPerson(new Guest(age));
        }
    }

    // Prompt the user to enter the ticket ID for displaying details
    public void displayTicketDetails(@NotNull Scanner scanner, @NotNull List<Ticket> tickets) {
        System.out.print("Enter Ticket ID: ");
        UUID ticketId = UUID.fromString(scanner.next());

        // Find the ticket with the provided ID and display its details
        for (Ticket ticket : tickets) {
            if (ticket.getUuid().equals(ticketId)) {
                System.out.println("\nTicket Details for Ticket ID " + ticketId + ":");
                ticket.displayTicketDetails();
                return;
            }
        }

        // If the ticket with the provided ID is not found, print a message
        System.out.println("Ticket with ID " + ticketId + " not found.");
    }
}
