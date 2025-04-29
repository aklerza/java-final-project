import java.util.*;
import java.io.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Database{
	
	
	private List<Flight> flights = new ArrayList<>();
	private List<Booking> bookings = new ArrayList<>();
	
	private File DBFile = new File("database.json");
	
	public String AddFlight(String payload) {
		return "";
	}
	
	public String RemoveFlight(String payload) {
		return "";
	}
	
	public String AddBooking(String payload) {
		return "";
	}
	
	public String CancelBooking(String payload) {
		return "";
	}
}
