import java.util.*;
import java.io.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Database{
	private List<Flight> flight = new ArrayList<>();
	private List<Booking> booking = new ArrayList<>();
	
	private File mainfile = new File("database.json");
	
	public String AddFlight(String value) {
		return "";
	}
	
	public String RemoveFlight(String value) {
		return "";
	}
	
	public String AddBooking(String value) {
		return "";
	}
	
	public String CancelBooking(String value) {
		return "";
	}
}
