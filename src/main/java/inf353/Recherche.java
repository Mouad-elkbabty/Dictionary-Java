package inf353;

public class Recherche {

    public String[] recherche; // La "phrase" recherchée
    public Indexation index; // L'indexation des documents(faite dans le main avant l'appel de la recherche)
    public int[][] resultat; // Tableau des couples [[score,indice du document],[score, indice du document],...]

    /**
     * Créer une Recherche
     * @param requete La requête faite
     * @param indexation L'Indexation utilisée
     */
    public Recherche(String[] requete, Indexation indexation) {
        this.recherche = requete;
        this.index = indexation;
        this.resultat = new int[index.dictioDocuments.nbMots()][2];
    }

    /**
     * Calcule le score des documents en fonction de l'Indexation
     */
    public void score() {
        for (int i = 0; i < index.dictioDocuments.nbMots(); i++) {
            int score = 0;
            for (int j = 0; j < recherche.length; j++) {
                int valeur = this.index.val(recherche[j], this.index.dictioDocuments.motIndice(i));
                if (valeur != -1) score += valeur;
            }
            resultat[i][0] = score;
            resultat[i][1] = i;
        }
    }

    /**
     * Trier les résultats du meilleur au pire score
     */
    public String[] presentation() {
        int[][] copie = this.resultat.clone();
        for (int i = 0; i < copie.length; i++) {
            for (int j = i+1; j < copie.length; j++) {
                if (copie[j][0] > copie[i][0]) {
                    int[] element = copie[i];
                    copie[i] = copie[j];
                    copie[j] = element;
                }
            }
        }
        String[] resultat = new String[copie.length];
        for (int k = 0; k < resultat.length; k++) {
            resultat[k] = this.index.dictioDocuments.motIndice(copie[k][1]) + " (score: " + copie[k][0] + ")";
        }
        return resultat;
    }

}
