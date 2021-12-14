package inf353;

import java.io.File;
import java.io.IOException;

public class MainRecherche {

    public static void main (String[] args) throws IOException {
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
            File dossier = new File("./src/main/resources/inf353/requetes");
            int l = dossier.listFiles().length;
            recherche.requete("requete", res, 500);
        }
    }

}
