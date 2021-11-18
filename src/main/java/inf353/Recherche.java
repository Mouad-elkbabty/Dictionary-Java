package inf353;

public class Recherche {
    public String[] recherche; // La "phrase" recherchée
    public Indexation index; // L'indexation des documents(faite dans le main avant l'appel de la recherche)
    public int[][] resultat; // Tableau des couples [[score,indice du document],[score, indice du document],...]

    public Recherche(String[] requete, Indexation indexation)
    {
        this.recherche = requete;
        this.index = indexation;
        this.resultat = new int[index.maxDocuments][2];
    }

    //Score parait OK
    public int[][] score ()
    {
        int i = 0;
        while(i != index.maxDocuments)
        {
            int j = 0;
            int score = 0;
            while(j != recherche.length)
            {
                int element = index.dictioMots.indiceMot(recherche[j]);
                int occurence = index.matriceOccurences.val(i,element);
                score = score + occurence;
            }
            resultat[i][0] = score;
            resultat[i][1] = i;
        } 
        return resultat;
    }

    public String[] presentation()
    {
        String[] res = new String[this.index.maxDocuments];
        int i = 0;
        while(i != resultat.length) 
        {
            int max = resultat[i][0];
            int j = i+1;
            int indiceMax = i;
            int indiceDoc = resultat[i][1];
            while(j < resultat.length) //Trouve le score max parmi tout les documents
            {
                if(resultat[j][0] >= max)
                {
                    max = resultat[j][0];
                    indiceDoc = resultat[j][1];
                    indiceMax = j;
                }
                j++;
            }
            resultat[indiceMax] = resultat[i];
            resultat[i][0] = max;
            resultat[i][1] = indiceDoc;
            i++;
        }
        int w = 0;
        while(w < resultat.length)
        {
            res[w] = this.index.dictioDocuments.motIndice(resultat[w][1]);
            w++;
        }


        return res;
    }

}