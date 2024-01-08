package mastermind;
public class GameController {
    private Plateau plateau;
    private Partie partie;
    private Manche mancheActuelle;
    private Tentative tentativeActuelle;
    private ViewEnd viewEnd;

    public GameController(Plateau plateau, ViewEnd viewEnd)
    {
        this.viewEnd = viewEnd;
        this.plateau = plateau;
    }

    public void createPartie(String nomJoueur, int nbManches, int nbPionsDispo, int nbPionsCombinaison, int nbTentatives)
    {
        plateau.setJoueur(nomJoueur);
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
        else
        {
            viewEnd.end(plateau.getNomJoueur(), partie.getScore());
        }
    }

    public void validerTentative(Combinaison tentative)
    {
        for (Couleurs c: tentative.getCombinaison())
        {
            if(c == null)
            {
                return;
            }
        }
        tentativeActuelle.setCombinaisonCouleur(tentative);
        mancheActuelle.addTentative(tentativeActuelle);
        if(mancheActuelle.verifierCombinaisonIndices())//manche terminée
        {
            gameStart();
        }
    }


    public void giveUpManche()
    {
        mancheActuelle.giveUp();
        gameStart();
    }
}
