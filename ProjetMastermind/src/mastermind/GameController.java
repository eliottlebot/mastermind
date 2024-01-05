package mastermind;
public class GameController {
    private Plateau plateau;
    private Partie partie;
    private Manche mancheActuelle;
    private Tentative tentativeActuelle;
    private int nbManches;
    private int manchesCount = 0;


    public GameController(Plateau plateau)
    {
        this.plateau = plateau;
    }

    public void createPartie(int nbManches, int nbPionsDispo, int nbPionsCombinaison, int nbTentatives)
    {

        this.nbManches = nbManches;
        partie = plateau.createPartie(nbManches, nbPionsDispo, nbPionsCombinaison, nbTentatives);
        gameStart();
    }


    public void gameStart()
    {
        if(partie.getManchesCount() < partie.getNbManches())
        {
            mancheActuelle = partie.createManche();
            tentativeActuelle = mancheActuelle.createTentative();
        }
    }

    public void validerTentative(Combinaison tentative)
    {
        tentativeActuelle.setCombinaisonCouleur(tentative);
        mancheActuelle.addTentative(tentativeActuelle);
        if(mancheActuelle.verifierCombinaisonIndices())//manche terminée
        {
            gameStart();
        }
    }
}
