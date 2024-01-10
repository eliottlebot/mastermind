package Controller;

import Model.*;
import View.ViewEnd;

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

    public void createPartie(String nomJoueur, int nbManches, int nbPionsDispo, int nbPionsCombinaison, int nbTentatives, int typeIndice)
    {
        plateau.setJoueur(nomJoueur);
        partie = plateau.createPartie(nbManches, nbPionsDispo, nbPionsCombinaison, nbTentatives, typeIndice);
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
            viewEnd.end(plateau.getNomJoueur(), partie.getScore(), partie.getTabScores());
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
            partie.updateScore();
            gameStart();
        }
    }


    public void giveUpManche()
    {
        partie.updateScore();
        mancheActuelle.giveUp();
        gameStart();
    }
}
