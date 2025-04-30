public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        UserInterface_swing ui = new UserInterface_swing(db);
        Controller controller = new Controller(ui, db);
        controller.start();
    }
}
