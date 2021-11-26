package inf353;

import java.io.File;
import java.io.IOException;

public class MainIndexation {
    public static String[] commons;
    public static Indexation index;
    public static void main (String[] args) throws IOException
    {
        String dossier = "/partage_etu/Science/INF/353_projet/french";
        index = new Indexation(dossier);
        int i = 0;
        chargerFichiers(dossier);
        /*
        while(i < commons.length)
        {
            index.ajouterDocument(commons[i], true);
            i++;
        }
        */
        File sauvegarde = new File("./src/main/resources/inf353/sauvegarde/");
        if (!sauvegarde.isDirectory()) {
            sauvegarde.mkdir();
        }
        //index.sauver("./src/main/resources/inf353/sauvegarde/Matrice.txt", "./src/main/resources/inf353/sauvegarde/Dictionnaires.txt");
    }

    /**
     * ChargerFichiers charge tout les fichiers d'un dossier ainsi que les fichiers fils
     * chemin est le chemin du dossier
     * @param chemin
     * @throws IOException
     */
    public static void chargerFichiers(String chemin) throws IOException
    {
        System.out.println(chemin);
        File fichier = new File(chemin);
        String[] resultat = fichier.list();
        
        File suivant;
        for(int i = 0; i< resultat.length; i++)
        {
            suivant = new File(chemin+ "/" + resultat[i]);
            if(suivant.isDirectory())
            {
                chargerFichiers(suivant.getPath());
            }
            else
            {
                index.ajouterDocument(suivant.getPath(), true);
            }
        }

        
    }


}
