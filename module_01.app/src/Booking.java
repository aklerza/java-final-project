public class Booking {
    private String bookingid;
    private String name;
    private String flightid;
    private int numberoftickets;
    public Booking(){}
    public Booking(String bookingid, String name, String flightid, int numberoftickets){
        this.bookingid = bookingid;
        this.name = name;
        this.flightid = flightid;
        this.numberoftickets = numberoftickets;
    }
    public String getbookingid(){
        return bookingid;
    }
    public String getname(){
        return name;
    }
    public String getflightid(){
        return flightid;
    }
    public int getnumberoftickets(){
        return numberoftickets;
    }
}
