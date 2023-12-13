package mastermind;
public class Tentative {
    private Combinaison combinaisonCouleur;
    public Indice[] indices;


    public Tentative(int tailleCombinaison) {
        combinaisonCouleur = new Combinaison(tailleCombinaison);
        indices = new Indice[tailleCombinaison];
    }

    public void setCombinaisonCouleur(Combinaison combinaisonCouleur)
    {
        this.combinaisonCouleur = combinaisonCouleur;
    }

    public Combinaison getCombinaison()
    {
        return this.combinaisonCouleur;
    }

    public Couleurs[] getCouleurs()
    {
        return this.getCombinaison().getCombinaison();
    }

    public Indice[] getIndices()
    {
        return this.indices;
    }

    public void setIndicesCouleurs(Indice indice, Integer index){
        this.indices[index]=indice;
    }
}

