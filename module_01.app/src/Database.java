import java.util.*;
import java.io.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Database{
	
	@JsonProperty("flights")
	public List<Flight> flights = new ArrayList<>();
	
	@JsonProperty("bookings")
	public List<Booking> bookings = new ArrayList<>();
	
	private static final File DBFile = new File("database.json");
	
	public void saveDatabase() {
		ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(DBFile, this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
	}
	
	public void loadDatabase() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (DBFile.exists()) {
            	Database db = mapper.readValue(DBFile, Database.class);
                this.flights = db.flights;
                this.bookings = db.bookings;
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
	
	
	/*
	 	{
			"destination": "DESTINATION",
			"date": "FLIGHT_DATE",
			"freeseats": 0
		}
	 */
	public String AddFlight(String payload) {
		try { 
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(payload);
			
			String random_hex = Integer.toHexString(new Random().nextInt());
			
			Flight temp = new Flight(
					random_hex,
					node.get("destination").asText(),
					node.get("date").asText(),
					node.get("freeseats").asInt()
					);
			
			this.flights.add(temp);
			
			this.saveDatabase();
			
			return "{ \"status\":1,\"flightid\":\"+random_hex+\" }";
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	/*
	 *  {
			"flightid": "3e89a7ec"
		}
	*/
	public String RemoveFlight(String payload) {
		
		try { 
	        ObjectMapper mapper = new ObjectMapper();
	        JsonNode node = mapper.readTree(payload);
	        
	        String flightid = node.get("flightid").asText();
	        
	        for (Flight flight : this.flights) {
	            if (flight.flightid.equals(flightid)) {
	                this.flights.remove(flight);
	                break;
	            }
	        }

	        this.saveDatabase();
	        
	        return "{ \"status\":1 }";
	    } catch (JsonProcessingException e) {
	    	throw new RuntimeException(e);
	    }
		
	}
	
	
	/*
	 * {
  			"flightid": "3e89a7ec",
  			"name": "John Doe",
  			"numberoftickets": 1
		}*/
	public String AddBooking(String payload) {
		
		try { 
	        ObjectMapper mapper = new ObjectMapper();
	        JsonNode node = mapper.readTree(payload);
	        
	        String flightId = node.get("flightid").asText();
	        String name = node.get("name").asText();
	        int numberOfTickets = node.get("numberoftickets").asInt();
	        
	        Flight flight = null;
	        for (Flight f : this.flights) {
	            if (f.flightid.equals(flightId)) {
	                flight = f;
	                break;
	            }
	        }
	        
	        if (flight != null && flight.freeSeats >= numberOfTickets) {
	            String bookingId = Integer.toHexString(new Random().nextInt());
	            Booking newBooking = new Booking(bookingId, name, flightId, numberOfTickets);
	            this.bookings.add(newBooking);
	            
	            flight.freeSeats -= numberOfTickets;
	            
	            this.saveDatabase();
	            
	            return "{ \"status\":1, \"bookingid\":\"" + bookingId + "\" }";
	        } else {
	            return "{ \"status\":0, \"bookingid\":\"\" }";
	        }
	    } catch (JsonProcessingException e) {
	    	throw new RuntimeException(e);
	    }
		
	}
	
	
	
	/*
	 * 
	 * {
  "bookingid": "a2c1b289"
}*/
	public String CancelBooking(String payload) {
		
		try { 
	        ObjectMapper mapper = new ObjectMapper();
	        JsonNode node = mapper.readTree(payload);
	        
	        String bookingid = node.get("bookingid").asText();
	        
	        for (Booking booking : this.bookings) {
	            if (booking.bookingid.equals(bookingid)) {
	                this.bookings.remove(booking);

	                for (Flight flight : this.flights) {
	                    if (flight.flightid.equals(booking.flightid)) {
	                        flight.freeSeats += booking.numberoftickets;
	                        break;
	                    }
	                }
	                
	                break;
	            }
	        }
	        
	        this.saveDatabase();
	        
	        return "{ \"status\":1 }";
	    } catch (JsonProcessingException e) {
	    	throw new RuntimeException(e);
	    }
		
	}
}
