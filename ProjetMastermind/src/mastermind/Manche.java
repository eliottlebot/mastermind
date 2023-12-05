package mastermind;

import java.lang.reflect.Array;
import java.util.*;

public class Manche {
    private Couleurs[] combinaisonSecrete;
    private int nbTentativesMax;
    private int tailleCombinaison;
    private Tentative tentativeActuelle;
    private boolean isFinished = false;
    private int score = 0;


    public Manche(Integer tailleCombinaison, Integer nombreTentatives){
        this.tailleCombinaison = tailleCombinaison;
        combinaisonSecrete = new Couleurs[tailleCombinaison];
        nbTentativesMax = nombreTentatives;
    }

    public Tentative createTentative(int i)
    {
        Tentative tenta = new Tentative(tailleCombinaison);
        this.tentativeActuelle = tenta;
        return tenta;
    }

    public Tentative getTentativeActuelle()
    {
        return this.tentativeActuelle;
    }

    public Couleurs[] getCombinaisonSecrete(){
        return combinaisonSecrete;
    }



    //----------------------------------------------
    //Générer la combinaison aléatoire de la manche
    //----------------------------------------------
    public void genererCombinaisonAleatoire()
    {
        Random rnd = new Random();
        int temp;
        //On crée la liste des couleurs disponibles pour la combinaison secrète
        List<Couleurs> dispo = new ArrayList<>();

        for(int j = 0; j<tailleCombinaison; j++)
        {
            dispo.add(Couleurs.values()[j]);
        }

        //La liste de couleurs disponibles est créée, on peut mtn créer notre combinaison secrète
        for(int i =0; i<tailleCombinaison; i++){
            temp = rnd.nextInt(dispo.size());
            combinaisonSecrete[i]=dispo.get(temp);
            dispo.remove(temp);
        }
    }




    //----------------------------------------------
    //Fonctions de vérification (calcul)
    //----------------------------------------------
    public void verifierCombinaisonJoueurInt()
    {
        boolean finished = true;
        for(int i = 0; i<combinaisonSecrete.length; i++)
        {
            if(combinaisonSecrete[i]==tentativeActuelle.getTentative()[i])
            {
                tentativeActuelle.augmentePionsBienPlace();
            }
            else{
                finished = false;
            }
        }

        this.isFinished = finished;
    }
    public void verifierCombinaisonIndices(){
        //On parcourt la combinaison secrète en vérifiant deux choses :
        //Si la couleur est la bonne, on donne la couleur Noir au bon index
        //Sinon, on regarde si la couleur est quand même dans la combi secrète
        //Auquel cas on mettra un Blanc, sinon on ne met rien
        boolean finished = true;
        for(int i = 0; i<combinaisonSecrete.length; i++)
        {
            if(combinaisonSecrete[i]==tentativeActuelle.getTentative()[i])
            {
                tentativeActuelle.setIndicesCouleurs(Indice.NOIR, i);
            }
            else if(couleurDansCombinaison(combinaisonSecrete, tentativeActuelle.getTentative()[i]))
            {
                tentativeActuelle.setIndicesCouleurs(Indice.BLANC, i);
                finished = false;
            }
            else
            {
                tentativeActuelle.setIndicesCouleurs(Indice.VIDE, i);
                finished = false;
            }
        }

        this.isFinished = finished;
    }
    public Boolean couleurDansCombinaison(Couleurs[] combinaisonSecrete, Couleurs couleur){
        for(Couleurs couleursSec : combinaisonSecrete){
            if(couleur==couleursSec){
                return true;
            }
        }
        return false;
    }

    public boolean isFinished()
    {
        return this.isFinished;
    }

    public void upgradeScore()
    {
        score++;
    }

    public int getScore()
    {
        return this.score;
    }
}
