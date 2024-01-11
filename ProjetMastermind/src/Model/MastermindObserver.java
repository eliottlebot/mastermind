package Model;

public interface MastermindObserver{
    void init(Manche manche, Integer typeIndice, int numManche);
    void showAvaibleColors(Couleurs[] couleursDispo);
    void startTentative(int nbPionsCombi);
    void addTentativeUpdateIndice(Manche manche, Tentative tentative, Indice[] indices, Integer typeIndice, int score);
    void newManche(boolean isWin, int nbTentatives);
}
