package mastermind;

import java.util.*;

public class Manche {
    private Combinaison combinaisonSecrete;
    private int nbTentativesMax;
    private int tailleCombinaison;
    private int nbPionsDispo;

    private List<Tentative> listTentatives = new ArrayList<>();
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
        Tentative tenta = new Tentative(tailleCombinaison);
        notifyObserversStartTentative(tailleCombinaison);
        return tenta;
    }

    public void addTentative(Tentative tentative)
    {
        listTentatives.add(tentative);
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
    public boolean verifierCombinaisonIndices(){
        //On parcourt la combinaison secrète en vérifiant deux choses :
        //Si la couleur est la bonne, on donne la couleur Noir au bon index
        //Sinon, on regarde si la couleur est quand même dans la combi secrète
        //Auquel cas on mettra un Blanc, sinon on ne met rien
        boolean finished = true;
        Tentative tentativeActuelle = listTentatives.get(listTentatives.size() - 1);

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


        notifyOberserversAddTentativeUpdateIndice(tentativeActuelle, tentativeActuelle.getIndices());

        if(finished)
        {
            notifyOberserversNewManche(true);
        }
        else if((listTentatives.size() - 1) == nbTentativesMax)
        {
            notifyOberserversNewManche(false);
        }

        return finished;
    }
    public Boolean couleurDansCombinaison(Combinaison combinaisonSecrete, Couleurs couleur){
        for(Couleurs couleursSec : combinaisonSecrete.getCombinaison()){
            if(couleur==couleursSec){
                return true;
            }
        }
        return false;
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

    private void notifyOberserversNewManche(boolean isFinished)
    {
        for (MastermindObserver observer: listObservers) {
            observer.newManche(isFinished);
        }
    }
    private void notifyOberserversAddTentativeUpdateIndice(Tentative tentative, Indice[] indices)
    {
        for (MastermindObserver observer: listObservers) {
            observer.addTentativeUpdateIndice(tentative, indices);
        }
    }



}
