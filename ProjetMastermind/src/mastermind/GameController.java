package mastermind;

import java.util.Scanner;
public class GameController {
    private Plateau plateau;
    private Partie partie;
    private int nbManches;
    private int nbPionsDispo;
    private int nbPionsCombinaison;
    private int nbTentatives;


    public GameController(Plateau plateau)
    {
        this.plateau = plateau;
    }

    public void createPartie(int nbManches, int nbPionsDispo, int nbPionsCombinaison, int nbTentatives)
    {
        partie = plateau.createPartie(nbManches, nbPionsDispo, nbPionsCombinaison, nbTentatives);
        gameStart();
    }


    public void gameStart()
    {
        Scanner scanner = new Scanner(System.in);


        //Ca c'est juste pour afficher les couleurs disponibles
        System.out.print("Couleurs disponibles : ");
        for(int i = 0; i<nbPionsDispo-1; i++)
        {
            System.out.print(Couleurs.values()[i] + ", ");
        }
        System.out.println(Couleurs.values()[nbPionsDispo-1]);


        for(int i = 0; i < nbManches; i++)
        {
            //création de la manche
            Manche manche = partie.createManche(i);
            System.out.println("\n\t--- mastermind.Manche n°" + i + " ---\n");
            for(int j = 0; j < nbTentatives && !manche.isFinished(); j++)
            {
                //augmenter le score
                manche.upgradeScore();
                System.out.println("\t\t--- Quelle est votre tentative (couleur couleur couleur...) : " + j);

                //créer la tentative
                Tentative tenta = manche.createTentative(j);

                //récupérer les couleurs du joueur et mise en place de la tentative
                Couleurs[] tableauCouleurs = getInput();
                tenta.setTentative(tableauCouleurs);

                //verif de la tentative et récupérer l'indice
                manche.verifierCombinaisonIndices();
                Indice indice[] = tenta.getIndices();

                //afficher l'indice (jpense t'avais capté sans le commentaire)
                afficherTableauIndices(indice);
            }
            System.out.println("\t---- mastermind.Manche terminée avec comme score : " + manche.getScore() + "----\n\n");
            partie.upgradeScore(manche.getScore());
        }
        System.out.println("--- Partie terminée avec comme score : " + partie.getScore() + "\n");
    }




    public static Couleurs[] getInput()
    {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] couleursSaisies = input.split(" ");
        Couleurs[] tableauCouleurs = new Couleurs[couleursSaisies.length];
        for (int k = 0; k < couleursSaisies.length; k++)
        {
            tableauCouleurs[k] = Couleurs.valueOf(couleursSaisies[k].toUpperCase());
        }

        return tableauCouleurs;
    }

    public static void afficherTableauIndices(Indice[] tableau) {
        for (Indice indice : tableau) {
            System.out.print(indice + " ");
        }
        System.out.println();
    }

    public static void afficherIndicesInt(Tentative tentative){
        System.out.println(tentative.pionsBienPlaces+ " : pions biens placés");
        System.out.println(tentative.indices.length-(tentative.pionsBienPlaces)+ " : pions mal placés");
    }
}
