package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Combinaison {
    private Couleurs[] combinaison;

    public Combinaison(Integer tailleCombinaison){
        combinaison = new Couleurs[tailleCombinaison];
    }

    public Couleurs[] getCombinaison(){
        return combinaison;
    }

    public void setCouleur(Couleurs couleur, int index){
        combinaison[index] = couleur;
    }

    public int getLength()
    {
        return combinaison.length;
    }

    public void genererCombinaisonAleatoire(Integer nbPionsDispo){


        Random rnd = new Random();
        int temp;
        //On crée la liste des couleurs disponibles pour la combinaison secrète
        List<Couleurs> dispo = new ArrayList<>();

        for(int j=0; j<nbPionsDispo; j++){
            dispo.add(Couleurs.values()[j]);
        }
        //La liste de couleurs disponibles est créée, on peut mtn créer notre combinaison secrète
        for(int i =0; i<combinaison.length; i++)
        {
            temp = rnd.nextInt(dispo.size());
            combinaison[i]=dispo.get(temp);
        }
    }
}
