package inf353;

import java.io.IOException;

public class MainRecherche {


    public static void main (String[] args) throws IOException {
        Recherche recherche;
        Indexation index;
        index = new Indexation("./src/main/resources/inf353/");
        recherche = new Recherche(args, "./src/main/resources/inf353/");
        recherche.presentation();

    }

}
