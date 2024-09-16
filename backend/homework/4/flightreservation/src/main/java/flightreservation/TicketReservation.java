package flightreservation;

import LogBack.LogBack;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class TicketReservation {

    private static final int CONFIRMEDLIST_LIMIT = 10;
    private static final int WAITINGLIST_LIMIT = 3;

    private List<Passenger> confirmedList = new ArrayList<>();
    private Deque<Passenger> waitingList = new ArrayDeque<>();

    // This getter is used only by the junit test case.
    public List<Passenger> getConfirmedList() {
          return confirmedList;
    }


    public boolean bookFlight(String firstName, String lastName, int age, String gender, String travelClass, String confirmationNumber) {
        Passenger passenger = new Passenger(firstName, lastName, age, gender, travelClass, confirmationNumber);
       if(confirmedList.size()==CONFIRMEDLIST_LIMIT && waitingList.size()==WAITINGLIST_LIMIT)
           return false;
       if(confirmedList.size()<CONFIRMEDLIST_LIMIT)
           confirmedList.add(passenger);
       if(waitingList.size()<WAITINGLIST_LIMIT)
           waitingList.add(passenger);
       return true;
    }

    public boolean cancel(String confirmationNumber) {

        if(removePassenger(confirmedList.iterator(),confirmationNumber))
        {
            if(waitingList!=null)
            {
             Passenger waitingGetsConfirmed=waitingList.poll();
             waitingList.pop();
             confirmedList.add(waitingGetsConfirmed);
            }
        }
        else
        {
          removePassenger(waitingList.iterator(),confirmationNumber);
        }
        return true;
    }


    public boolean removePassenger(Iterator<Passenger> iterator, String confirmationNumber) {
        while (iterator.hasNext()) {
            Passenger toBeChecked = iterator.next();
            if (toBeChecked.getConfirmationNumber().equals(confirmationNumber)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
     public static void main(String[] args)
     {
         // Create an instance of TicketReservation
         TicketReservation ticketReservation = new TicketReservation();
         // Book a flight
         boolean bookingResult = ticketReservation.bookFlight("John", "Doe", 30, "Male", "Economy", "E1");
         String message=String.format("Booking Result: %s", bookingResult ? "Success" : "Failed");
         LogBack.slf4jLogger.debug(message);
         // Cancel a reservation
         boolean cancelResult = ticketReservation.cancel("123456");
         message=String.format("Cancel Result: %s", cancelResult ? "Success" : "Failed");
         LogBack.slf4jLogger.debug(String.format(message));

     }
     }
