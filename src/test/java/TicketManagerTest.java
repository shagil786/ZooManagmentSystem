import org.example.Ticket;
import org.example.ZooTicketManagement;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class TicketManagerTest {
    @Test
    public void issueTicket() {
        List<Ticket> tickets = new ArrayList<>();
        ZooTicketManagement ticketManagement = new ZooTicketManagement();

        // Set up the input for the test (simulate user input)
        String simulatedInput = "2\n25\n30\n";
        provideInput(simulatedInput);

        try {
            ticketManagement.issueTicket(new Scanner(System.in), tickets);
        } finally {
            restoreSystemInput();
        }

        // Check if the ticket was issued successfully
        assertEquals(1, tickets.size());

        Ticket issuedTicket = tickets.get(0);
        assertNotNull(issuedTicket.getUuid());
        assertEquals(2, issuedTicket.getPersons().size());
    }

    @Test
    public void displayTicketDetails() {
        List<Ticket> tickets = new ArrayList<>();
        ZooTicketManagement ticketManagement = new ZooTicketManagement();

        // Issue a ticket
        provideInput("2\n25\n30\n");
        try {
            ticketManagement.issueTicket(new Scanner(System.in), tickets);
        } finally {
            restoreSystemInput();
        }

        // Set up the input for the test (simulate user input)
        String simulatedInput = tickets.get(0).getUuid() + "\n";
        provideInput(simulatedInput);

        // Set up output capture
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        try {
            ticketManagement.displayTicketDetails(new Scanner(System.in), tickets);
        } finally {
            restoreSystemInput();
            restoreSystemOutput();
        }

        // Check if the correct ticket details were displayed
        String consoleOutput = outputStream.toString();
        assertNotNull(consoleOutput);
        assertTrue(consoleOutput.contains("Ticket Details for Ticket ID " + tickets.get(0).getUuid()));
    }

    private void provideInput(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
    }

    private void restoreSystemInput() {
        System.setIn(System.in);
    }

    private void restoreSystemOutput() {
        System.setOut(System.out);
    }
}
