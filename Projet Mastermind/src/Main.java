import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Nombre de manches : ");
        int nbManches = Integer.parseInt(scanner.nextLine());

        System.out.println("Nombre de pions disponibles : ");
        int nbPionsDispo = Integer.parseInt(scanner.nextLine());

        System.out.println("Nombre de pions combinaisons : ");
        int nbPionsCombinaison = Integer.parseInt(scanner.nextLine());

        System.out.println("Nombre de tentatives : ");
        int nbTentatives = Integer.parseInt(scanner.nextLine());



        System.out.println("------MASTERMIND------\n");


        //Ca c'est juste pour afficher les couleurs disponibles
        System.out.print("Couleurs disponibles : ");
        for(int i = 0; i<nbPionsDispo-1; i++)
        {
            System.out.print(Couleurs.values()[i] + ", ");
        }
        System.out.println(Couleurs.values()[nbPionsDispo-1]);



        //création de la partie avec les bons paramètres
        Partie partie = new Partie(nbManches, nbPionsDispo, nbPionsCombinaison, nbTentatives);
        for(int i = 0; i < nbManches; i++)
        {
            //création de la manche
            Manche manche = partie.createManche(i);
            System.out.println("\n\t--- Manche n°" + i + " ---\n");
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
            System.out.println("\t---- Manche terminée avec comme score : " + manche.getScore() + "----\n\n");
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