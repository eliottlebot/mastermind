import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Manche manche1 = new Manche(5, 4);
        manche1.genererCombinaisonAleatoire();
        Couleurs[] qdfg = manche1.getCombinaisonSecrete();
        Tentative tentative1Joueur = new Tentative(5);
        tentative1Joueur.rempliCombinaisonJoueur();
        manche1.verifierCombinaisonIndices(tentative1Joueur);
        manche1.verifierCombinaisonJoueurInt(tentative1Joueur);

        afficherIndices(tentative1Joueur);
        afficherIndicesInt(tentative1Joueur);
    }

    public static void afficherIndices(Tentative tentative){
        for(Indice indices : tentative.indices){
            System.out.print(indices + " ");
        }
        System.out.println();
    }

    public static void afficherIndicesInt(Tentative tentative){
        System.out.println(tentative.pionsBienPlaces+ " : pions biens placés");
        System.out.println(tentative.indices.length-(tentative.pionsBienPlaces)+ " : pions mal placés");
    }
}