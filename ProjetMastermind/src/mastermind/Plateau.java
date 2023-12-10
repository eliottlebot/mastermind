package mastermind;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
    private String nomJoueur;
    private int meilleurScore;
    private List<MastermindObserver> listObservers = new ArrayList<>();


    public Partie createPartie(int nbManches, int nbPionsDispo, int nbPionsCombinaison, int nbTentatives)
    {
        notifyObserversInit(nbTentatives, nbPionsCombinaison);
        return new Partie(listObservers, nbManches, nbPionsDispo, nbPionsCombinaison, nbTentatives);
    }

    public void setJoueur(String nom)
    {
        nomJoueur = nom;
    }


    public void addObserver(MastermindObserver mastermindObserver)
    {
        listObservers.add(mastermindObserver);
    }

    private void notifyObserversInit(int nbTentatives, int nbPionsCombi)
    {
        for (MastermindObserver observer: listObservers) {
            observer.init(nbTentatives, nbPionsCombi);
        }
    }
}
