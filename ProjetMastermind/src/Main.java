import Controller.GameController;
import Model.Plateau;
import View.ViewEnd;
import View.ViewGame;
import View.ViewStart;

public class Main {
    public static void main(String[] args) {
        Plateau plateau = new Plateau();
        ViewEnd viewEnd = new ViewEnd();
        GameController controller = new GameController(plateau, viewEnd);
        ViewStart vueStart = new ViewStart(controller);
        ViewGame viewGame = new ViewGame(controller);
        plateau.addObserver(viewGame);
    }
}