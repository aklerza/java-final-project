public class Flight {
    private String flightid;
    private String destination;
    private String date;
    private int freeSeats;
    public Flight(){}
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
    public int getfreeSeats(){
        return freeSeats;
    }
    public void setflightid(String flightid){
        this.flightid = flightid;
    }
    public void setdestination(String destination){
        this.destination = destination;
    }
    public void setdate(String date){
        this.date = date;
    }
    public void setfreeSeats(int freeSeats){
        this.freeSeats = freeSeats;
    }
}
