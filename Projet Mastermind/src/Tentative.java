public class Tentative {
    private Couleurs[] tentative;
    public Indice[] indices;
    public int pionsBienPlaces;


    public Tentative(int tailleCombinaison) {
        tentative = new Couleurs[tailleCombinaison];
        indices = new Indice[tailleCombinaison];
        pionsBienPlaces=0;
    }

    public Tentative()
    {

    }

    public void setTentative(Couleurs[] tentative)
    {
        this.tentative = tentative;
    }

    public Couleurs[] getTentative()
    {
        return this.tentative;
    }

    public Indice[] getIndices()
    {
        return this.indices;
    }



    public void augmentePionsBienPlace(){
        this.pionsBienPlaces++;
    }
    public void setIndicesCouleurs(Indice indice, Integer index){
        this.indices[index]=indice;
    }
}

