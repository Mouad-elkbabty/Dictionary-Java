package inf353;

import java.io.IOException;

public class Recherche {

    String[] recherche;
    Indexation index;
    /**
     * Crée une Recherche
     * @param requete La requête à faire
     * @param chemin Le chemin vers l'Indexation à utiliser
     */
    public Recherche(String[] requete, String chemin) throws IOException{
        recherche = requete;
        index = new Indexation(chemin);
    }

    /**
     * Calcule le score des documents en fonction de l'Indexation et de la recherche
     */
    public void score() {
        int[] valeurs = new int[this.index.dictioDocuments.nbMots()];
        int i = 0;
        while(i < this.index.dictioDocuments.nbMots()) //Parcours de tout les documents
        {
            int j = 0;
            while(j < recherche.length) //parcours la liste des element de la recherche
            {
                valeurs[i] += index.matriceOccurences.val(i,index.dictioMots.indiceMot(recherche[j]));
                j++;
            }
            i++;
        }
    }

    /**
     * Trie les résultats du meilleur au pire score
     */
    public String[] presentation() {
        return new String[1];
    } 

}
