package mastermind;
public class Tentative {
    private Couleurs[] combinaisonCouleur;
    public Indice[] indices;


    public Tentative(int tailleCombinaison) {
        combinaisonCouleur = new Couleurs[tailleCombinaison];
        indices = new Indice[tailleCombinaison];
    }

    public void setCombinaisonCouleur(Couleurs[] combinaisonCouleur)
    {
        this.combinaisonCouleur = combinaisonCouleur;
    }

    public Couleurs[] getCombinaison()
    {
        return this.combinaisonCouleur;
    }

    public Indice[] getIndices()
    {
        return this.indices;
    }

    public void setIndicesCouleurs(Indice indice, Integer index){
        this.indices[index]=indice;
    }
}

