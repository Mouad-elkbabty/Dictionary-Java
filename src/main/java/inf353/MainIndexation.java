package inf353;

import java.io.File;
import java.io.IOException;

public class MainIndexation {

    public static void main (String[] args) throws IOException {
        Indexation indexation = new Indexation();
        chargerFichiers("C:/Users/val0u/Desktop/echantillon_100/", indexation);
        File sauvegarde = new File("./src/main/resources/inf353/");
        if (!sauvegarde.isDirectory()) sauvegarde.mkdir();
        indexation.sauver("./src/main/resources/inf353/");
    }

    /**
     * Charge tous les documents du corpus
     * @param chemin Le chemin du dossier qui contient le corpus
     * @throws IOException
     */
    public static void chargerFichiers(String chemin, Indexation indexation) throws IOException {
        File fichier = new File(chemin);
        String[] resultat = fichier.list();
        for(int i = 0; i< resultat.length; i++) {
            File suivant = new File(chemin+ "/" + resultat[i]);
            if(suivant.isDirectory()) {
                chargerFichiers(suivant.getPath(), indexation);
            } else {
                indexation.ajouterDocument(suivant.getPath());
            }
        }
    }

}
