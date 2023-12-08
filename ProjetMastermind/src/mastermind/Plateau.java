package mastermind;

public class Plateau {
    private String nomJoueur;
    private int meilleurScore;
    public Plateau()
    {

    }


    public Partie createPartie(int nbManches, int nbPionsDispo, int nbPionsCombinaison, int nbTentatives)
    {
        Partie partie = new Partie(nbManches, nbPionsDispo, nbPionsCombinaison, nbTentatives);
        return partie;
    }


    public void createJoueur(String nom)
    {

    }
}
