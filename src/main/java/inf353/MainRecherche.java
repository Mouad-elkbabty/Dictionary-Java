package inf353;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class MainRecherche {

    public static void main (String[] args) throws IOException {
      /*  Stoplist stoplist = new Stoplist();
        String [] tampon = new String [args.length];
        

        int i = 0;
        int j = 0;
        while (i < args.length){
            if (!stoplist.estPresent(args[i])){
                tampon[j] = args [i];
                j++;
            }
            i++;
        }
        recherches = new String [j];
        j = 0;
        while(j < recherches.length){
            recherches[j] = tampon[j];
            j++;
        }*/
        String recherches = synonymes(args);
        // recherche.presentation(2500);
        if (args.length == 0) throw new Error("Veuillez entrer une requete valide");
        Recherche recherche = new Recherche("./src/main/resources/inf353/indexation/");
        try {
            int n = Integer.parseInt(args[0]);
            if (n < 91 || n > 140) throw new NumberFormatException();
            recherche.requete(n, 500);
        } catch (NumberFormatException e) {
            String res = "";
            int i = 0;
            while (i != args.length) {
                res += args[i] + " ";
                i++;
            }
            File dossier = new File("./src/main/resources/inf353/requetes/");
            if (dossier.listFiles() != null){
                int l = dossier.listFiles().length;
            }
            else {
                int l = 0;
            }
            recherche.requete("requete" + l, res, 500);
        }
    }

}
