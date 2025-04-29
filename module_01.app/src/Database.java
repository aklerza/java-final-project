import java.util.*;
import java.io.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Database{
    private List<Flight> flight = new ArrayList<>();
    private List<Booking> booking = new ArrayList<>();
    private File databaseFile = new File("database.json");
    private ObjectMapper newmapper = new ObjectMapper();

    public Database(){
        load();
    }

    private void load(){
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
//from json data
    public String AddFlight(String value) {
        String flightid = null;
        try {
            Flight f = newmapper.readValue(value, Flight.class);
            flightid = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
            f.setflightid(flightid);
            this.flight.add(f);
            this.savetofile();
            return "{\"status\": 1,\"flightid\": \"" +flightid + "\"}";
        }catch (Exception e) {

            return "{\"status\": 0,\"flightid\": \"" +flightid + "\"}";;
        }
    }

    public String RemoveFlight(String value) {
        try{
           Map<String, String> data = newmapper.readValue(value,Map.class);
           String flightid = data.get("flightid");
           int t=0;
           for (Flight f : this.flight) {
               if (f.getflightid().equals(flightid)) {
                   this.flight.remove(f);
                   t=1;
                   break;
               }
           }
           this.savetofile();
           if (t==0){return "{\"status\": 0}";}
           else{return "{\"status\": 1}";}
        }catch (Exception e) {
            return "{\"status\": 0}";
        }
    }

    public String AddBooking(String value) {
        String bookingid = null;
        try {
            Booking b = newmapper.readValue(value, Booking.class);
            bookingid = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
            b.setbookingid(bookingid);
            int t=0;
            for (Flight f : this.flight) {
                if (f.getflightid().equals(b.getflightid())) {
                    if (f.getfreeSeats()>=b.getnumberoftickets()){
                        this.booking.add(b);
                        f.setfreeSeats(f.getfreeSeats()-b.getnumberoftickets());
                        t=1;
                        break;
                    }
                }
            }
            this.savetofile();
            if (t==0){return "{\"status\": 0,\"bookingid\": \"" +bookingid + "\"}";}
            else{return "{\"status\": 1,\"bookingid\": \"" +bookingid + "\"}";}
        }catch (Exception e) {

            return "{\"status\": 0,\"bookingid\": \"" +bookingid + "\"}";
        }
    }

    public String CancelBooking(String value) {
        try{
            Map<String, String> data = newmapper.readValue(value,Map.class);
            String bookingid = data.get("bookingid");
            int t=0;
            for (Booking b : this.booking) {
                if (b.getbookingid().equals(bookingid)) {
                    for (Flight f : this.flight) {
                        if (f.getflightid().equals(b.getflightid())) {
                            f.setfreeSeats(f.getfreeSeats()+b.getnumberoftickets());
                            this.booking.remove(b);
                            t=1;
                            break;
                        }
                    }
                }
            }
            this.savetofile();
            if (t==0){return "{\"status\": 0}";}
            else{return "{\"status\": 1}";}
        }catch (Exception e) {
            return "{\"status\": 0}";
        }
    }
}
