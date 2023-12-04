import java.lang.reflect.Array;
import java.util.*;

public class Manche {
    private Couleurs[] combinaisonSecrete;
    private Tentative[] tentatives;
    public Manche(Integer tailleCombinaison, Integer nombreTentatives){
        combinaisonSecrete = new Couleurs[tailleCombinaison];
        tentatives = new Tentative[nombreTentatives];
    }
    public Couleurs[] getCombinaisonSecrete(){
        return combinaisonSecrete;
    }


    //----------------------------------------------
    //Générer la combinaison aléatoire de la manche
    //----------------------------------------------
    public void genererCombinaisonAleatoire(){
        Random rnd = new Random();
        int temp;
        //On crée la liste des couleurs disponibles pour la combinaison secrète
        List<Couleurs> dispo = new ArrayList<>();
        for(int j = 0; j<combinaisonSecrete.length; j++){
            dispo.add(Couleurs.values()[j]);
        }

        //La liste de couleurs disponibles est créée, on peut mtn créer notre combinaison secrète
        for(int i =0; i<combinaisonSecrete.length; i++){
            temp = rnd.nextInt(dispo.size());
            combinaisonSecrete[i]=dispo.get(temp);
            dispo.remove(temp);
        }
    }



    //----------------------------------------------
    //Fonctions de vérification (calcul)
    //----------------------------------------------
    public void verifierCombinaisonJoueurInt(Tentative tentative){
        for(int i = 0; i<combinaisonSecrete.length; i++){
            if(combinaisonSecrete[i]==tentative.getCombinaisonJoueur()[i]){
                tentative.augmentePionsBienPlace();
            }
        }
    }
    public void verifierCombinaisonIndices(Tentative tentative){
        //On parcourt la combinaison secrète en vérifiant deux choses :
        //Si la couleur est la bonne, on donne la couleur Noir au bon index
        //Sinon, on regarde si la couleur est quand même dans la combi secrète
        //Auquel cas on mettra un Blanc, sinon on ne met rien
        for(int i = 0; i<combinaisonSecrete.length; i++){
            if(combinaisonSecrete[i]==tentative.getCombinaisonJoueur()[i]){
                tentative.setIndicesCouleurs(Indice.NOIR, i);
            }
            else if(couleurDansCombinaison(combinaisonSecrete, tentative.getCombinaisonJoueur()[i])){
                tentative.setIndicesCouleurs(Indice.BLANC, i);
            }
            else{
                tentative.setIndicesCouleurs(Indice.VIDE, i);
            }
        }
    }
    public Boolean couleurDansCombinaison(Couleurs[] combinaisonSecrete, Couleurs couleur){
        for(Couleurs couleursSec : combinaisonSecrete){
            if(couleur==couleursSec){
                return true;
            }
        }
        return false;
    }
}
