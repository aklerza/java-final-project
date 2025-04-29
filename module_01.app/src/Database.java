import java.util.*;
import java.io.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Database{
    public List<Flight> flight = new ArrayList<>();
    public List<Booking> booking = new ArrayList<>();
    private File databaseFile = new File("database.txt");
    private ObjectMapper newmapper = new ObjectMapper();
    public Database(){
        try{
            if(databaseFile.exists()){
                Database saving = newmapper.readValue(databaseFile, Database.class);
                this.flight = saving.flight;
                this.booking = saving.booking;
            }
        } catch (Exception e) {System.out.println("Error! -> " + e.getMessage());}
    }
    
    public void savetofile(){
        try{
            newmapper.writerWithDefaultPrettyPrinter().writeValue(databaseFile, this);
        } catch (Exception e){System.out.println("Error! -> " + e.getMessage());}
    }

    public void AddFlight(String Destination, String date, int freeSeats){
        String flightid ="Ft" + (this.flight.size()+1);
        this.flight.add(new Flight(flightid,Destination,date,freeSeats));
        this.savetofile();
    }
    
    public String JsonValuesFlight(){
        try {
            return newmapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.flight);
        } catch (Exception e) {
            return "Smth went wrong , pffff!!";
        }
    }

    public String JsonValuesBookings(){
        try {
            return newmapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.booking);
        } catch (Exception e) {
            return "Smth went wrong , pffff!!";
        }
    }

    public void BookFlight(String name, String flightid, int numberoftickets){
        int t=0;
        for(Flight f : this.flight){
            if(f.flightid.equals(flightid) && f.freeSeats>=numberoftickets){
                String bookingid="Bg" + (this.booking.size() + 1);
                this.booking.add(new Booking(bookingid,name,flightid,numberoftickets));
                f.freeSeats-=numberoftickets;
                this.savetofile();
                System.out.println("Reserved successfully! ");
                t=1;
            }
        }
        if(t==0){System.out.println("Invalid Input! ");}
    }

    public void CancelBooking(String bookingid){
        int t=0;
        for(Booking b : this.booking){
            if(b.bookingid.equals(bookingid)){
                for(Flight f : this.flight){
                    if(f.flightid.equals(b.flightid)){
                        f.freeSeats+=b.numberoftickets;
                    }
                }
                this.booking.remove(b);
                this.savetofile();
                System.out.println("Reservation cancelled successfully! ");
                t=1;
            }
        }
        if(t==0){System.out.println("Reservation not found! ");}
    }
}
