package mastermind;

import java.util.Scanner;
public class GameController {
    private Plateau plateau;
    private Partie partie;
    private Manche mancheActuelle;
    private Tentative tentativeActuelle;
    private int nbManches;
    private int nbPionsDispo;
    private int nbPionsCombinaison;
    private int nbTentatives;
    private boolean tentativeValidee = false;

    private int manchesCount = 0;


    public GameController(Plateau plateau)
    {
        this.plateau = plateau;
    }

    public void createPartie(int nbManches, int nbPionsDispo, int nbPionsCombinaison, int nbTentatives)
    {
        this.nbManches = nbManches;
        this.nbPionsCombinaison = nbPionsCombinaison;
        this.nbTentatives = nbTentatives;
        partie = plateau.createPartie(nbManches, nbPionsDispo, nbPionsCombinaison, nbTentatives);
        gameStart();
    }


    public void gameStart()
    {
        if(manchesCount < nbManches)
        {
            manchesCount++;
            mancheActuelle = partie.createManche();
            tentativeActuelle = mancheActuelle.createTentative();
        }
        /*for(int i = 0; i < nbManches; i++)
        {
            //création de la manche
            System.out.println("\n\t--- mastermind.Manche n°" + i + " ---\n");
            for(int j = 0; j < nbTentatives && !mancheActuelle.isFinished(); j++)
            {
                //augmenter le score
                mancheActuelle.upgradeScore();
                System.out.println("\t\t--- Quelle est votre tentative (couleur couleur couleur...) : " + j);

                //créer la tentative

                while(!tentativeValidee)
                {

                }

                //verif de la tentative et récupérer l'indice
                mancheActuelle.verifierCombinaisonIndices();
                Indice indice[] = tentativeActuelle.getIndices();

                //afficher l'indice (jpense t'avais capté sans le commentaire)
                afficherTableauIndices(indice);
            }
            System.out.println("\t---- mastermind.Manche terminée avec comme score : " + manche.getScore() + "----\n\n");
            partie.upgradeScore(manche.getScore());
        }
        System.out.println("--- Partie terminée avec comme score : " + partie.getScore() + "\n");*/
    }

    public void validerTentative(Combinaison tentative)
    {
        tentativeActuelle.setCombinaisonCouleur(tentative);
        mancheActuelle.addTentative(tentativeActuelle);
        boolean isFinished = mancheActuelle.verifierCombinaisonIndices();
    }



}
