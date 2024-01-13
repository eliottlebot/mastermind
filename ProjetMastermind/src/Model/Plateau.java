package Model;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
    private String nomJoueur;
    private List<MastermindObserver> listObservers = new ArrayList<>();


    public Partie createPartie(int nbManches, int nbPionsDispo, int nbPionsCombinaison, int nbTentatives, int typeIndice)
    {
        return new Partie(listObservers, nbManches, nbPionsDispo, nbPionsCombinaison, nbTentatives, typeIndice);
    }
    public void setJoueur(String nom)
    {
        nomJoueur = nom;
    }
    public String getNomJoueur()
    {
        return this.nomJoueur;
    }
    public void addObserver(MastermindObserver mastermindObserver)
    {
        listObservers.add(mastermindObserver);
    }

}
