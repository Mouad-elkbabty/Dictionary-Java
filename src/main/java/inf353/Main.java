package inf353;

import java.io.IOException;

public class Main {
    public static Indexation indexation;
    public static int nbResultatAfficher;
    public static Recherche recherche;

    public static void main (String[] args) throws IOException {

        // à modifier
        indexation = new Indexation();
        recherche = new Recherche(args, indexation);
        nbResultatAfficher = 15;
        int i = 0;
        recherche.score();
        String[] sortie = recherche.presentation();
        while(i < nbResultatAfficher && i < sortie.length) {
            if(sortie[i] != null) {
                System.out.println(sortie[i]);
            }
            i++;
        }   
    }

}
