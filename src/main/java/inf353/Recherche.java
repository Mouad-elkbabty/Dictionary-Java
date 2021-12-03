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
                valeurs[i] += index.matriceOccurences.val(i,index.dictioMots.indiceMot(recherche[j]));
                System.out.println(index.dictioMots.indiceMot(recherche[j]));
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
        int l = 0;
        while (l < valeurs.length)
        {
            System.out.println(valeurs[l]);
            l++;
        }
        int[] tab = new int[10];
        int i = 0;
        int j = 0;
        int k = 0;
        int max = 0;
        int indicemax = 0;
        while (j < 10 && j < valeurs.length)
        {
            indicemax =0;
            i = 0;
            max = 0;
            while(i < valeurs.length)
            {
                if(valeurs[i] > max)
                {
                    max = valeurs[i];
                    indicemax = i;
                }
                i++;
            }
            valeurs[indicemax] = 0;
            tab[j] = indicemax;
            j++;
        }
        
        while(k < 10 && k < valeurs.length) 
        {
            System.out.println(index.dictioDocuments.motIndice(tab[k]));
            k++;
        }
    } 

}
