package inf353;

import java.io.IOException;

public class MainEval {

    public static void main(String[] args) throws IOException{
        Recherche recherche = new Recherche("./src/main/resources/inf353/indexation/");
        int doc = 91;
        while (doc < 141) {
            recherche.requete(doc, 250);
            doc++;
        }
    }

}