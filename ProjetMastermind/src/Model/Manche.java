package Model;

import java.util.*;

public class Manche {
    private Combinaison combinaisonSecrete;
    private int nbTentativesMax;
    private int tailleCombinaison;
    private int nbPionsDispo;
    private int typeIndice;
    private List<Tentative> listTentatives = new ArrayList<>();
    private int score = 0;
    private List<MastermindObserver> listObservers;
    private HashMap<Couleurs, Integer> compteurCouleurs = new HashMap<Couleurs, Integer>();


    public Manche(int nbPionsDispo, Integer tailleCombinaison, Integer nombreTentatives, List<MastermindObserver> obervers, int typeIndice){
        this.nbPionsDispo = nbPionsDispo;
        this.tailleCombinaison = tailleCombinaison;
        this.typeIndice = typeIndice;
        combinaisonSecrete = new Combinaison(tailleCombinaison);
        nbTentativesMax = nombreTentatives;
        listObservers = obervers;
        initCompteur(compteurCouleurs);
    }

    public Tentative createTentative()
    {
        Tentative tenta = new Tentative(tailleCombinaison);
        notifyObserversStartTentative(tailleCombinaison);
        return tenta;
    }

    public void addTentative(Tentative tentative)
    {
        listTentatives.add(tentative);
    }

    //----------------------------------------------
    //Générer la combinaison aléatoire de la manche
    //----------------------------------------------
    public void genererCombinaisonAleatoire()
    {
        combinaisonSecrete.genererCombinaisonAleatoire(nbPionsDispo);
        Integer value;
        for(int i =0; i<tailleCombinaison; i++){
            Couleurs temp = combinaisonSecrete.getCombinaison()[i];
            compteurCouleurs.put(temp, compteurCouleurs.get(temp)+1);
        }
    }

    //----------------------------------------------
    //Fonctions de vérification (calcul)
    //----------------------------------------------
    public boolean verifierCombinaisonIndices(){
        //On parcourt la combinaison secrète en vérifiant deux choses :
        //Si la couleur est la bonne, on donne la couleur Noir au bon index
        //Sinon, on regarde si la couleur est quand même dans la combi secrète
        //Auquel cas on mettra un Blanc, sinon on ne met rien

        boolean finished = true;


        //On récupère la tentative actuelle (la dernière tentative ajoutée à la manche actuelle)
        Tentative tentativeActuelle = listTentatives.get(listTentatives.size() - 1);

        //On réinitialise le tableau d'indices de la tentative, comme ça on peut repartir de 0 et éviter les bugs
        Arrays.fill(tentativeActuelle.getIndices(), Indice.VIDE);

        //Nouveau compteur de couleurs qui permettra de savoir si toutes les occurences d'une couleur ont été trouvées
        HashMap<Couleurs, Integer> compteurTemp = new HashMap<Couleurs, Integer>();
        initCompteur(compteurTemp);
        int nbPionsBienPlaces = 0;

        if(typeIndice == 1)
        {
            score += 4;
        }

        //Premier tour de boucle parcourt la liste des couleurs, pour placer des poins noirs au bon endroit
        for(int i =0; i<tailleCombinaison; i++) {
            Couleurs couleurTentative = tentativeActuelle.getCombinaison().getCombinaison()[i];
            Couleurs couleurSecrete = combinaisonSecrete.getCombinaison()[i];
            if (couleurSecrete == couleurTentative) {
                //On ajoute l'indice noir dans le tableau d'indices, et on incrémente la valeur du nb d'occurences
                tentativeActuelle.setIndicesCouleurs(Indice.NOIR, i);
                compteurTemp.put(couleurTentative, compteurTemp.get(couleurTentative) + 1);
                nbPionsBienPlaces += 1;
            }
        }

        score += 3*nbPionsBienPlaces;

        //Deuxieme tour de boucle qui vérifie d'abord que l'indice à l'index donné nest pas déja noir, puis voit si elle met un indice blanc ou vide, en fonction du compteur de couleurs
        for(int i =0; i<tailleCombinaison; i++){
            Couleurs couleurTentative = tentativeActuelle.getCombinaison().getCombinaison()[i];
            Couleurs couleurSecrete = combinaisonSecrete.getCombinaison()[i];
            if(tentativeActuelle.getIndices()[i]!=Indice.NOIR){
                if (couleurDansCombinaison(combinaisonSecrete, couleurTentative))
                {
                    if (Objects.equals(compteurTemp.get(couleurTentative), compteurCouleurs.get(couleurTentative))) {
                        tentativeActuelle.setIndicesCouleurs(Indice.VIDE, i);
                    } else {
                        tentativeActuelle.setIndicesCouleurs(Indice.BLANC, i);
                        score++;
                    }
                }
                else{
                    tentativeActuelle.setIndicesCouleurs(Indice.VIDE, i);
                }
                finished = false;
            }

        }
        //System.out.println(Arrays.toString(tentativeActuelle.getIndices()));

        notifyOberserversAddTentativeUpdateIndice(tentativeActuelle, tentativeActuelle.getIndices());

        //System.out.println("fini : " + finished);

        if(finished) {
            notifyOberserversNewManche(true, listTentatives.size());
        }
        else if((listTentatives.size()) == nbTentativesMax){
            notifyOberserversNewManche(false, listTentatives.size());
        }

        //System.out.println("listTentatives.size() - 1 : " + (listTentatives.size() - 1) + " == " + nbTentativesMax);

        return finished||(listTentatives.size()) == nbTentativesMax;
    }
    public Boolean couleurDansCombinaison(Combinaison combinaisonSecrete, Couleurs couleur) {
        for (Couleurs couleursSec : combinaisonSecrete.getCombinaison()) {
            if (couleur == couleursSec) {
                return true;
            }
        }
        return false;
    }

    public void initCompteur(HashMap<Couleurs, Integer> compteur) {
        for (Couleurs couleur : Couleurs.values()) {
            compteur.put(couleur, 0);
        }
    }

    public int getScore()
    {
        return this.score;
    }


    public void giveUp()
    {
        notifyOberserversNewManche(false, listTentatives.size());
    }


    private void notifyObserversStartTentative(int nbPionsCombi)
    {
        for (MastermindObserver observer: listObservers) {
            observer.startTentative(nbPionsCombi);
        }
    }

    private void notifyOberserversNewManche(boolean isWin, int nbTentatives)
    {
        for (MastermindObserver observer: listObservers) {
            observer.newManche(isWin, nbTentatives);
        }
    }
    private void notifyOberserversAddTentativeUpdateIndice(Tentative tentative, Indice[] indices)
    {
        for (MastermindObserver observer: listObservers) {
            observer.addTentativeUpdateIndice(tentative, indices, typeIndice, getScore());
        }
    }



}
