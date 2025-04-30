import java.util.ArrayList;
import java.util.List;

public class Controller {
    private UserInterface ui;
    private Database db;

    public Controller(UserInterface ui, Database db) {
        this.ui = ui;
        this.db = db;
    }

    public void start() {
        db.loadDatabase();
        ui.show_mainmenu();
    }

    // Add booking
    public String bookFlight(String flightId, String name, int numberOfTickets) {
        String payload = String.format("{\"flightid\":\"%s\", \"name\":\"%s\", \"numberoftickets\":%d}",
                flightId, name, numberOfTickets);
        return db.AddBooking(payload);
    }

    // Cancel booking 
    public String cancelBooking(String bookingId) {
        String payload = String.format("{\"bookingid\":\"%s\"}", bookingId);
        return db.CancelBooking(payload);
    }

    // View bookings by name
    public List<Booking> getAllBookings(String name) {
        List<Booking> userBookings = new ArrayList<>();
        for (Booking b : db.bookings) {
            if (b.getname().equalsIgnoreCase(name)) {
                userBookings.add(b);
            }
        }
        return userBookings;
    }

    // Get flight info by ID
    public Flight getFlightById(String flightId) {
        for (Flight f : db.flights) {
            if (f.flightid.equals(flightId)) {
                return f;
            }
        }
        return null;
    }
}
