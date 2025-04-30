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
