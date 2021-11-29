package inf353;

import java.io.File;
import java.io.IOException;

public class MainIndexation {

    public static void main (String[] args) throws IOException {
        String dossier = "/partage_etu/Science/INF/353_projet/french";
        Indexation indexation = new Indexation();
        chargerFichiers(dossier, indexation);
        File sauvegarde = new File("./src/main/resources/inf353/sauvegarde/");
        if (!sauvegarde.isDirectory()) sauvegarde.mkdir();
        // indexation.sauver(...);
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
