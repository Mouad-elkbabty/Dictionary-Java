package inf353;

import java.io.IOException;

public class Main {
    public static Indexation indexation;
    public static int nbResultatAfficher;
    public static Recherche recherche;

    public static void main (String[] args) throws IOException {
        indexation = new Indexation("./src/main/resources/inf353/sauvegarde/","Matrice.txt","Dictionnaires.txt");
        for (int a = 0; a < args.length; a++) {
            args[a] = Indexation.retirerAccents(args[a].toLowerCase());
        }

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
