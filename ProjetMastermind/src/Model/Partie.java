package Model;
import java.util.List;
public class Partie {
    private int nbManches;
    private int nbPionsDispo;
    private int nbPionsCombinaison;
    private int nbTentatives;
    private int typeIndice;
    private int mancheCount = 0;
    private int score = 0;
    private int[] tabScores;
    private Manche mancheActuelle;
    private List<MastermindObserver> listObservers;


    public Partie(List<MastermindObserver> observers, int nbManches, int nbPionsDispo, int nbPionsCombinaison, int nbTentatives, int typeIndice)
    {
        listObservers = observers;
        this.nbManches = nbManches;
        this.nbPionsDispo = nbPionsDispo;
        this.nbPionsCombinaison = nbPionsCombinaison;
        this.nbTentatives = nbTentatives;
        this.typeIndice = typeIndice;
        tabScores = new int[nbManches];
        notifyObserversShowAvaibleColors();
    }

    public Manche createManche()
    {
        if(getManchesCount() < getNbManches())
        {
            notifyObserversInit(nbTentatives, nbPionsCombinaison);
            mancheCount++;
            mancheActuelle = new Manche(nbPionsDispo, nbPionsCombinaison, nbTentatives, listObservers, typeIndice);
            mancheActuelle.genererCombinaisonAleatoire();
            return mancheActuelle;
        }


        return null;
    }

    public void updateScore()
    {
        int nb = mancheActuelle.getScore();
        tabScores[mancheCount-1] = nb;
        score += nb;
    }

    public int getScore()
    {
        return this.score;
    }

    private void notifyObserversShowAvaibleColors()
    {
        Couleurs[] couleursDispo = new Couleurs[nbPionsDispo];
        Couleurs[] toutesLesCouleurs = Couleurs.values();

        for (int i = 0; i < nbPionsDispo; i++) {
            couleursDispo[i] = toutesLesCouleurs[i];
        }

        for (MastermindObserver observer: listObservers) {
            observer.showAvaibleColors(couleursDispo);
        }
    }

    public int getManchesCount()
    {
        return this.mancheCount;
    }

    public int getNbManches()
    {
        return this.nbManches;
    }

    public int[] getTabScores()
    {
        return this.tabScores;
    }

    private void notifyObserversInit(int nbTentatives, int nbPionsCombi)
    {
        for (MastermindObserver observer: listObservers) {
            observer.init(nbTentatives, nbPionsCombi, typeIndice, mancheCount);
        }
    }
}
