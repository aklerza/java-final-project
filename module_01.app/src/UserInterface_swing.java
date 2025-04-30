import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class UserInterface_swing implements UserInterface {
    private JFrame frame;
    private JTable flightTable;
    private DefaultTableModel tableModel;
    private Database Database;
    
    public UserInterface_swing(Database Database) {
    	this.Database = Database;
    	frame = new JFrame("BHOS - Flight booking system");
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void loadFlightsToTable(Database Database) {
        tableModel.setRowCount(0);
        List<Flight> flights = Database.flights;
        for (Flight f : flights) {
            tableModel.addRow(new Object[]{f.flightid, f.destination, f.date});
        }
    }
    
	public void show_mainmenu() {
	    frame.getContentPane().removeAll();
	    frame.setLayout(new BorderLayout(10, 10));

	    String[] columns = {"Flight ID", "Destination", "Date"};
	    tableModel = new DefaultTableModel(columns, 0);
	    flightTable = new JTable(tableModel);
	    flightTable.getTableHeader().setBackground(Color.DARK_GRAY);
	    flightTable.getTableHeader().setForeground(Color.WHITE);

	    loadFlightsToTable(this.Database);
	    
	    JScrollPane scrollPane = new JScrollPane(flightTable);
	    scrollPane.setPreferredSize(new Dimension(600, 200));
	    frame.add(scrollPane, BorderLayout.NORTH);

	    JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

	    String[] buttonLabels = {"Flight Info", "Book a Flight", "Cancel Booking", "My Bookings", "Exit"};
	    JButton[] buttons = new JButton[buttonLabels.length];

	    for (int i = 0; i < buttonLabels.length; i++) {
	        buttons[i] = new JButton(buttonLabels[i]);// canan
	        buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT); // menem canan, eshq oduna yanan
	        buttons[i].setMaximumSize(new Dimension(200, 30)); // ashiq
	        buttons[i].setFont(new Font("Arial", Font.PLAIN, 14)); // menem ashiq,
	        buttons[i].setBackground(new Color(220, 220, 220)); // ey servi xuraman
	        buttonPanel.add(buttons[i]); // - nizami remzi
	        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	    }

	    buttons[0].addActionListener(e -> show_flight_info());
	    buttons[1].addActionListener(e -> show_book_flight());
	    buttons[2].addActionListener(e -> show_cancel_flight());
	    buttons[3].addActionListener(e -> show_my_flights());
	    buttons[4].addActionListener(e -> System.exit(0));

	    frame.add(buttonPanel, BorderLayout.CENTER);
	    frame.pack();
	    frame.setVisible(true);
    }

    public void show_flight_info() {
    	String flightID = JOptionPane.showInputDialog(frame, "flightid:");
        if (flightID != null) {
            // look for data
            JOptionPane.showMessageDialog(frame, "flightid: " + flightID);
        }
        show_mainmenu();
    }

    public void show_book_flight() {
    JTextField flightIdField = new JTextField();
    JTextField nameField = new JTextField();
    JTextField ticketCountField = new JTextField();

    Object[] message = {
        "Flight ID:", flightIdField,
        "Your Name:", nameField,
        "Number of Tickets:", ticketCountField
    };

    int option = JOptionPane.showConfirmDialog(frame, message, "Book Flight", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
        String flightId = flightIdField.getText();
        String name = nameField.getText();
        int tickets;
        try {
            tickets = Integer.parseInt(ticketCountField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid ticket number.");
            show_mainmenu();
            return;
        }

        String json = "{ \"flightid\":\"" + flightId + "\", \"name\":\"" + name + "\", \"numberoftickets\":" + tickets + " }";
        String response = Database.AddBooking(json);
        JOptionPane.showMessageDialog(frame, "Response: " + response);
    }
    show_mainmenu();
}


    public void show_cancel_flight() {
    String bookingId = JOptionPane.showInputDialog(frame, "Booking ID:");
    if (bookingId != null) {
        String json = "{ \"bookingid\":\"" + bookingId + "\" }";
        String response = Database.CancelBooking(json);
        JOptionPane.showMessageDialog(frame, "Response: " + response);
    }
    show_mainmenu();
}


   public void show_my_flights() {
    String name = JOptionPane.showInputDialog(frame, "Your Name:");
    if (name != null) {
        List<Booking> bookings = controller.getAllBookings(name);
        if (bookings.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No bookings found.");
        } else {
            StringBuilder result = new StringBuilder();
            for (Booking b : bookings) {
                result.append("Booking ID: ").append(b.getbookingid())
                      .append(", Flight ID: ").append(b.getflightid())
                      .append(", Tickets: ").append(b.getnumberoftickets()).append("\n");
            }
            JOptionPane.showMessageDialog(frame, result.toString());
        }
    }
    show_mainmenu();
}

}
