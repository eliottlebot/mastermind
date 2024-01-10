package mastermind;

public interface MastermindObserver{
    public void init(int nbTentatives, int nbPionsCombinaison, Integer typeIndice);
    public void showAvaibleColors(Couleurs[] couleursDispo);
    public void startTentative(int nbPionsCombi);
    public void addTentativeUpdateIndice(Tentative tentative, Indice[] indices, Integer typeIndice);
    public void newManche(boolean isWin);

    /*public void indiceUpdate(Indice[] indices);
    public void tentativeUpdate(Tentative tentative);
    public void scoreUpdate(int score);*/
}
