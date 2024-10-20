package GameOfLifeGUI;
public class GameOfLifeGUI {
    public static void main(String[] args) {
        Verden verden = new Verden(80, 60);
        GameOfLifeKontroller kontroller = new GameOfLifeKontroller(verden);
        kontroller.start();
    }
}
