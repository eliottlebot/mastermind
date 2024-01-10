package Model;

public interface MastermindObserver{
    public void init(Manche manche, Integer typeIndice, int numManche);
    public void showAvaibleColors(Couleurs[] couleursDispo);
    public void startTentative(int nbPionsCombi);
    public void addTentativeUpdateIndice(Manche manche, Tentative tentative, Indice[] indices, Integer typeIndice, int score);
    public void newManche(boolean isWin, int nbTentatives);
}
