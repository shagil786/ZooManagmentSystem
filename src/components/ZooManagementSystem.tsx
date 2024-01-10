import React, { useState } from "react";
import "./ZooTicketManagement.css"; // Import the CSS file for styling
import { Ticket } from "../utils/types";

const ZooTicketManagement: React.FC = () => {
  const [numberOfGuests, setNumberOfGuests] = useState<number>(0);
  const [ageOfGuests, setAgeOfGuests] = useState<string>("");
  const [issuedTickets, setIssuedTickets] = useState<Ticket[]>([]);
  const [message, setMessage] = useState<string>("");
  const [displayTicketId, setDisplayTicketId] = useState<string>("");

  const handleNumberOfGuestsChange = (
    event: React.ChangeEvent<HTMLInputElement>,
  ) => {
    setNumberOfGuests(parseInt(event.target.value, 10) || 0);
  };

  const handleAgeOfGuestsChange = (
    event: React.ChangeEvent<HTMLInputElement>,
  ) => {
    setAgeOfGuests(event.target.value);
  };

  const calculateCharges = (age: number): number => {
    if (age <= 2) {
      return 0;
    } else if (age < 18) {
      return 100;
    } else if (age < 60) {
      return 500;
    } else {
      return 300;
    }
  };

  const issueTicket = () => {
    const ageList = ageOfGuests.split(",").map((age) => parseInt(age, 10) || 0);

    const newTicket: Ticket = {
      ticketId: `TICKET-${issuedTickets.length + 1}`,
      numberOfGuests: numberOfGuests,
      ageOfGuests: ageList,
      displayDetails: false,
      totalCharges: ageList.reduce(
        (total, age) => total + calculateCharges(age),
        0,
      ),
    };

    setIssuedTickets([...issuedTickets, newTicket]);

    // Reset form fields
    setNumberOfGuests(0);
    setAgeOfGuests("");
    setMessage("Your details have been saved successfully!");

    console.log("Ticket issued successfully! Ticket ID:", newTicket.ticketId);
  };

  const displayDetails = (ticket: Ticket) => {
    return (
      <div>
        <p>Details for Ticket ID: {ticket.ticketId}</p>
        {ticket.ageOfGuests.map((age, index) => (
          <p key={index}>
            Guest {index + 1} (age: {age})
          </p>
        ))}
        <p>Total Charges: {ticket.totalCharges}</p>
      </div>
    );
  };

  const toggleDetails = (ticketId: string) => {
    setIssuedTickets((prevTickets) =>
      prevTickets.map((ticket) =>
        ticket.ticketId === ticketId
          ? { ...ticket, displayDetails: !ticket.displayDetails }
          : ticket,
      ),
    );
  };

  const handleDisplayTicketIdChange = (
    event: React.ChangeEvent<HTMLInputElement>,
  ) => {
    setDisplayTicketId(event.target.value);
  };

  const getDetails = () => {
    const ticket = issuedTickets.find((t) => t.ticketId === displayTicketId);

    if (ticket) {
      setMessage(""); // Clear previous messages
      toggleDetails(displayTicketId);
    } else {
      setMessage("Ticket with ID " + displayTicketId + " not found.");
    }
  };

  return (
    <div className="zoo-ticket-management-container">
      <div className="issued-tickets-container">
        <h2>Issued Tickets</h2>
        <div>
          <label>
            Enter Number of Guests:
            <input
              type="number"
              value={numberOfGuests}
              onChange={handleNumberOfGuestsChange}
            />
          </label>
        </div>
        <br />
        <div>
          <label>
            Enter Age of Guests (comma-separated):
            <input
              type="text"
              value={ageOfGuests}
              onChange={handleAgeOfGuestsChange}
            />
          </label>
        </div>
        <br />
        <button onClick={issueTicket}>Issue Ticket</button>
        <p>{message}</p>
        {issuedTickets.map((ticket) => (
          <div key={ticket.ticketId}>
            <p>Ticket ID: {ticket.ticketId}</p>
            <button onClick={() => toggleDetails(ticket.ticketId)}>
              {ticket.displayDetails ? "Hide Details" : "Display Details"}
            </button>
          </div>
        ))}
      </div>

      <div className="ticket-details-container">
        <h2>Display Ticket Details</h2>
        <label>
          Enter Ticket ID:
          <input
            type="text"
            value={displayTicketId}
            onChange={handleDisplayTicketIdChange}
          />
        </label>
        <button onClick={getDetails}>Get Details</button>
        {issuedTickets.map(
          (ticket) =>
            ticket.displayDetails && (
              <div key={ticket.ticketId}>{displayDetails(ticket)}</div>
            ),
        )}
      </div>

      <div className="clear-both"></div>
    </div>
  );
};

export default ZooTicketManagement;
