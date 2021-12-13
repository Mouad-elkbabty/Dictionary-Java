package inf353;

import java.io.IOException;

public class MainRecherche {

    public static void main (String[] args) throws IOException {
        Recherche recherche = new Recherche(args, "./src/main/resources/inf353/indexation/");
        recherche.presentation();
    }

}
