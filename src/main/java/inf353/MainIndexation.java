package inf353;

import java.io.File;
import java.io.IOException;

public class MainIndexation {
    public static String[] commons;
    public static Indexation index;
    public static void main (String[] args) throws IOException
    {
        index = new Indexation("./src/main/resources/inf353/documents/");
        File fichier = new File("./src/main/resources/inf353/documents/");
        commons = fichier.list();
        int i = 0;
        while(i < commons.length)
        {
            index.ajouterDocument(commons[i], true);
            i++;
        }
        index.sauver("./src/main/resources/inf353/sauvegarde/Matrice.txt", "./src/main/resources/inf353/sauvegarde/Dictionnaires.txt");
    }



}
