package mastermind;

public interface MastermindObserver{
    public void setParameters();

    public void indiceUpdate(Indice[] indices);
    public void tentativeUpdate(Tentative tentative);
    public void scoreUpdate(int score);
}
