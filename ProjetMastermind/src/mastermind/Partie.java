package mastermind;
import java.util.Scanner;
public class Partie {
    private int nbManches;
    private int nbPionsDispo;
    private int nbPionsCombinaison;
    private int nbTentatives;
    private int score = 0;
    private Manche mancheActuelle;


    public Partie(int nbManches, int nbPionsDispo, int nbPionsCombinaison, int nbTentatives)
    {
        this.nbManches = nbManches;
        this.nbPionsDispo = nbPionsDispo;
        this.nbPionsCombinaison = nbPionsCombinaison;
        this.nbTentatives = nbTentatives;
    }

    public Manche createManche(int i)
    {
        Manche manche = new Manche(nbPionsCombinaison, nbTentatives);
        mancheActuelle = manche;
        manche.genererCombinaisonAleatoire();
        return manche;
    }

    public void upgradeScore(int nb)
    {
        score += nb;
    }

    public int getScore()
    {
        return this.score;
    }

}
