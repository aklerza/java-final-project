import java.util.*;
import java.io.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Flight {
    public String flightid;
    public String destination;
    public String date;
    public int freeSeats;
    public Flight(){}
    public Flight(String flightid,String destination, String date, int freeSeats){
        this.flightid = flightid;
        this.destination = destination;
        this.date = date;
        this.freeSeats = freeSeats;
    }
}
public class Booking {
    public String bookingid;
    public String name;
    public String flightid;
    public int numberoftickets;
    public Booking(){}
    public Booking(String bookingid, String name, String flightid, int numberoftickets){
        this.bookingid = bookingid;
        this.name = name;
        this.flightid = flightid;
        this.numberoftickets = numberoftickets;
    }
}
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
            newmapper.writeValue(databaseFile, this);
        } catch (Exception e){System.out.println("Error! -> " + e.getMessage());}
    }
    public void ShowMainBoard(){
        for(Flight f : this.flight){
            System.out.println(f.flightid +" from: Kiev destination: "+f.destination+" "+f.date+" the number of seats: "+f.freeSeats);
        }
    }
    public void ShowBooking(){
        for(Booking b : this.booking){
            System.out.println(b.bookingid +" "+b.name+" flight ID: "+b.flightid+" tickets: "+b.numberoftickets);
        }
    }
    public void BookFlight(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        System.out.println("Enter your flight number: ");
        String flightid = sc.nextLine();
        System.out.println("Enter your number of tickets: ");
        int numberoftickets = sc.nextInt();
        sc.nextLine();
        int t=0;
        for(Flight f : this.flight){
            if(f.flightid.equals(flightid) && f.freeSeats>=numberoftickets){
                String bookingid="" + (this.booking.size() + 1);
                this.booking.add(new Booking(bookingid,name,flightid,numberoftickets));
                f.freeSeats-=numberoftickets;
                this.savetofile();
                System.out.println("Reserved successfully! ");
                t=1;
            }
        }
        if(t==0){System.out.println("Invalid Input! ");}
    }
    public void CancelBooking(){
        System.out.println("Enter Booking ID: ");
        Scanner sc = new Scanner(System.in);
        String bookingid = sc.nextLine();
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


