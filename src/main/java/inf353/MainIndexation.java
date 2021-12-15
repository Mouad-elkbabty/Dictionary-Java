package inf353;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class MainIndexation {

    public static void main (String[] args) throws IOException {
        Indexation indexation = new Indexation();
        Date debutChargement = new Date();
        System.out.println("Chargement des fichiers en cours...");
        chargerFichiers("/home/vialf/ubuntu/corpusFoireux/corpusfoireux", indexation);
        System.out.println("Chargement terminé.");
        System.out.println(indexation.dictioDocuments.nbMots() + " documents chargés.");
        System.out.println(indexation.dictioMots.nbMots() + " mots différents comptés.");
        System.out.println("Temps écoulé : " + ((new Date()).getTime() - debutChargement.getTime()) + "ms.");
        File sauvegarde = new File("./src/main/resources/inf353/");
        if (!sauvegarde.isDirectory()) sauvegarde.mkdir();
        Date debutSauvegarde = new Date();
        System.out.println("Sauvegarde en cours...");
        indexation.sauver("./src/main/resources/inf353/indexation/");
        System.out.println("Sauvegarde terminée.");
        System.out.println("Temps écoulé : " + ((new Date()).getTime() - debutSauvegarde.getTime()) + "ms.");
        System.out.println("Temps total écoulé : " + ((new Date()).getTime() - debutChargement.getTime()) + "ms.");
    }

    /**
     * Charge tous les documents du corpus
     * @param chemin Le chemin du dossier qui contient le corpus
     * @throws IOException
     */
    public static void chargerFichiers(String chemin, Indexation indexation) throws IOException {
        File fichier = new File(chemin);
        if (!fichier.exists()) throw new IOException("Aucun fichier/dossier du nom de " + fichier.getPath() + " n'a ete trouve.");
        String[] resultat = fichier.list();
        for (int i = 0; i < resultat.length; i++) {
            File suivant = new File(chemin+ "/" + resultat[i]);
            if(suivant.isDirectory()) {
                chargerFichiers(suivant.getPath(), indexation);
            } else {
                System.out.println("Chargement de " + suivant.getName());
                indexation.ajouterDocument(suivant.getPath());
            }
        }
    }

}
