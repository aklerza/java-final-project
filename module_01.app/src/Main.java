public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        Controller controller = null;
        UserInterface_swing ui = new UserInterface_swing(db);
        controller = new Controller(ui, db);
        ui.setdamncontroller(controller);
        controller.start();
    }
}
