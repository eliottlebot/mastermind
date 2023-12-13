package mastermind;

import java.util.*;

public class Manche {
    private Combinaison combinaisonSecrete;
    private int nbTentativesMax;
    private int tentativesCount = 0;
    private int tailleCombinaison;
    private int nbPionsDispo;
    private Tentative tentativeActuelle;
    private boolean isFinished = false;
    private int score = 0;
    private List<MastermindObserver> listObservers;


    public Manche(int nbPionsDispo, Integer tailleCombinaison, Integer nombreTentatives, List<MastermindObserver> obervers){
        this.nbPionsDispo = nbPionsDispo;
        this.tailleCombinaison = tailleCombinaison;
        combinaisonSecrete = new Combinaison(tailleCombinaison);
        nbTentativesMax = nombreTentatives;
        listObservers = obervers;
    }

    public Tentative createTentative()
    {
        tentativesCount++;
        Tentative tenta = new Tentative(tailleCombinaison);
        this.tentativeActuelle = tenta;
        notifyObserversStartTentative(tailleCombinaison);
        return tenta;
    }

    //----------------------------------------------
    //Générer la combinaison aléatoire de la manche
    //----------------------------------------------
    public void genererCombinaisonAleatoire()
    {
        combinaisonSecrete.genererCombinaisonAleatoire(nbPionsDispo);
    }

    //----------------------------------------------
    //Fonctions de vérification (calcul)
    //----------------------------------------------
    public void verifierCombinaisonIndices(){
        //On parcourt la combinaison secrète en vérifiant deux choses :
        //Si la couleur est la bonne, on donne la couleur Noir au bon index
        //Sinon, on regarde si la couleur est quand même dans la combi secrète
        //Auquel cas on mettra un Blanc, sinon on ne met rien
        boolean finished = true;
        for(int i = 0; i<tailleCombinaison; i++)
        {
            if (combinaisonSecrete.getCombinaison()[i] == tentativeActuelle.getCombinaison().getCombinaison()[i])
            {
                tentativeActuelle.setIndicesCouleurs(Indice.NOIR, i);
            }
            else if (couleurDansCombinaison(combinaisonSecrete, tentativeActuelle.getCombinaison().getCombinaison()[i]))
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
        notifyObserversUpdateIndice(tentativeActuelle.getIndices());

        if(finished)
        {
            notifyOberserversNewManche(true);
        }
        else if(tentativesCount == nbTentativesMax)
        {
            notifyOberserversNewManche(false);
        }
    }
    public Boolean couleurDansCombinaison(Combinaison combinaisonSecrete, Couleurs couleur){
        for(Couleurs couleursSec : combinaisonSecrete.getCombinaison()){
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


    private void notifyObserversStartTentative(int nbPionsCombi)
    {
        for (MastermindObserver observer: listObservers) {
            observer.startTentative(nbPionsCombi);
        }
    }


    private void notifyObserversUpdateIndice(Indice[] indice)
    {
        for (MastermindObserver observer: listObservers) {
            observer.updateIndice(indice);
        }
    }

    private void notifyOberserversNewManche(boolean isFinished)
    {
        for (MastermindObserver observer: listObservers) {
            observer.newManche(isFinished);
        }
    }


}
