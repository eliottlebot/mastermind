package Model;

public interface MastermindObserver{
    public void init(int nbTentatives, int nbPionsCombinaison, Integer typeIndice, int numManche);
    public void showAvaibleColors(Couleurs[] couleursDispo);
    public void startTentative(int nbPionsCombi);
    public void addTentativeUpdateIndice(Tentative tentative, Indice[] indices, Integer typeIndice, int score);
    public void newManche(boolean isWin, int nbTentatives);
}
