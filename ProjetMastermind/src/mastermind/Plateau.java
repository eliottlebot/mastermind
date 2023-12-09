package mastermind;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
    private String nomJoueur;
    private int meilleurScore;
    private List<MastermindObserver> listObservers = new ArrayList<>();

    public Plateau()
    {

    }


    public Partie createPartie(int nbManches, int nbPionsDispo, int nbPionsCombinaison, int nbTentatives)
    {
        Partie partie = new Partie(listObservers, nbManches, nbPionsDispo, nbPionsCombinaison, nbTentatives);
        return partie;
    }


    public void createJoueur(String nom)
    {

    }

    public void addObserver(MastermindObserver mastermindObserver)
    {
        listObservers.add(mastermindObserver);
    }
}
