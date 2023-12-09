package mastermind;

public class Main {
    public static void main(String[] args) {
        Plateau plateau = new Plateau();
        GameController controller = new GameController(plateau);
        ViewStart vueStart = new ViewStart(controller);
        ViewGame viewGame = new ViewGame(controller);
        plateau.addObserver(viewGame);
    }
}