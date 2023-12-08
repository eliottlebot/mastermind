package mastermind;

import mastermind.GameController;

public class Main {
    public static void main(String[] args) {
        Plateau plateau = new Plateau();
        GameController controller = new GameController(plateau);
        PartieVue vue = new PartieVue(controller);
    }
}