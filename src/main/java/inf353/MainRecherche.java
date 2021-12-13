package inf353;

import java.io.IOException;

public class MainRecherche {

    //
    public static void main (String[] args) throws IOException {
        //Appel la recherche avec le chemin spécifié dans 
        Recherche recherche = new Recherche(args, "./src/main/resources/inf353/");
        recherche.presentation();
    }
  


}
