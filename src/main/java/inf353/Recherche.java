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
    public Recherche(String[] requete, String chemin) throws IOException {
        recherche = requete;
        index = new Indexation(chemin);
    }

    /**
     * Calcule le score des documents en fonction de l'Indexation et de la recherche
     */
    public int[] score() {
        int[] valeurs = new int[this.index.dictioDocuments.nbMots()];
        int i = 0;
        while(i < this.index.dictioDocuments.nbMots()) //Parcours de tout les documents
        {
            int j = 0;
            while(j < recherche.length) //parcours la liste des element de la recherche
            {
                valeurs[i] += index.matriceOccurences.val(i, index.dictioMots.indiceMot(recherche[j]));
                j++;
            }
            i++;
        }
        return valeurs;
    }

    /**
     * Trie les résultats du meilleur au pire score et affiche les resultat
     */
    public void presentation() {
        int[] valeurs = score(); 
        int[] positions = new int[valeurs.length];
        for (int p = 0; p < positions.length; p++) {
            positions[p] = p;
        }
        for (int i = 0; i < valeurs.length; i++) {
            for (int j = i + 1; j < valeurs.length; j++) {
                if (valeurs[i] < valeurs[j]) {
                    int v = valeurs[i];
                    valeurs[i] = valeurs[j];
                    valeurs[j] = v;
                    int p = positions[i];
                    positions[i] = positions[j];
                    positions[j] = p;
                }
            }
        }
        for (int r = 0; r < 10 && r < positions.length; r++) {
            String resultat = (r+1) + ". " + index.dictioDocuments.motIndice(positions[r]) + " (score: " + valeurs[r] + ")";
            System.out.println(resultat);
        }
    } 

}
