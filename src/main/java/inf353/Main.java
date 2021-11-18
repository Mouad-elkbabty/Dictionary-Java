package inf353;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static Indexation indexation;
    public static int nbResultatAfficher;
    public static Recherche recherche;
    public static void main (String[] args) throws IOException
    {
        indexation = new Indexation("./src/main/resources/inf353/sauvegarde/","Matrice.txt","Dictionnaire.txt");
        recherche = new Recherche(args, indexation);
        nbResultatAfficher = 15;
        int i = 0;
        recherche.score();
        String[] sorti = recherche.presentation();
        while(i < nbResultatAfficher && i< sorti.length )
        {
            if(sorti[i] != null)
            {
                System.out.println(sorti[i]);
            }
            i++;
        }
        
        
    }

}
