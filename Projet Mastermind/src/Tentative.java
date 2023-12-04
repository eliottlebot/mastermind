public class Tentative {
    private Couleurs[] combinaisonJoueur;
    public Indice[] indices;
    public int pionsBienPlaces;


    public Tentative(int tailleCombinaison) {
        combinaisonJoueur = new Couleurs[tailleCombinaison];
        indices = new Indice[tailleCombinaison];
        pionsBienPlaces=0;
    }

    public Couleurs[] getCombinaisonJoueur(){
        return combinaisonJoueur;
    }

    public void rempliCombinaisonJoueur(){
        for(int i =0; i<combinaisonJoueur.length; i++){
            Couleurs couleur = Couleurs.BLEU;
            combinaisonJoueur[i]=couleur;
        }
    }

    public void augmentePionsBienPlace(){
        this.pionsBienPlaces++;
    }
    public void setIndicesCouleurs(Indice indice, Integer index){
        this.indices[index]=indice;
    }
}

