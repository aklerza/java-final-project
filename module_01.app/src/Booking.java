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
    public void setbookingid(String bookingid){
        this.bookingid = bookingid;
    }
    public void setname(String name){
        this.name = name;
    }
    public void setflightid(String flightid){
        this.flightid = flightid;
    }
    public void setnumberoftickets(int numberoftickets){
        this.numberoftickets = numberoftickets;
    }
}
