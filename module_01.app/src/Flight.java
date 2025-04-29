public class Flight {
    private String flightid;
    private String destination;
    private String date;
    private int freeSeats;
    private Flight(){}
    public Flight(String flightid,String destination, String date, int freeSeats){
        this.flightid = flightid;
        this.destination = destination;
        this.date = date;
        this.freeSeats = freeSeats;
    }
    public String getflightid(){
        return flightid;
    }
    public String getdestination(){
        return destination;
    }
    public String getdate(){
        return date;
    }
    public String getfreeSeats(){
        return freeSeats;
    }
    
    
}
