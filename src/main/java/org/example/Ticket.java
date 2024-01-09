package org.example;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

// Class representing a Zoo ticket
public class Ticket {
    // Generate a unique identifier for the ticket
    private UUID uuid;
    private List<Person> persons;
    private int totalCharges;

    public Ticket() {
        this.uuid = UUID.randomUUID();
        persons = new ArrayList<>();
        totalCharges = 0;
    }

    public UUID getUuid() {
        return uuid;
    }

    // Add a person to the ticket and calculate charges based on age
    public void addPerson(Person person) {
        persons.add(person);
        calculateCharges(person);
    }

    //Getting the list of persons
    public List<Person> getPersons() {
        return persons;
    }

    // Calculate charges based on the age of the person
    public void calculateCharges(Person person) {
        int age = person.getAge();
        if (age <= 2) {
            totalCharges += 0;
        } else if (age < 18) {
            totalCharges += 100;
        } else if (age < 60) {
            totalCharges += 500;
        } else {
            totalCharges += 300;
        }
    }

    // Display ticket details including persons and total charges
    public void displayTicketDetails() {
        System.out.println("Ticket ID: "+ uuid);
        for(int i=0;i<persons.size();i++) {
            System.out.println(persons.get(i).getPersonType() + " " + (i + 1) + " (age: " + persons.get(i).getAge() + ")");
        }
        System.out.println("Total Charges: INR " + totalCharges);
    }
}
