package inf353;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Indexation {

    public DictionnaireHash dictioMots;
    public DictionnaireHash dictioDocuments;
    public MatriceHash matriceOccurences;

    /**
     * Créer une Indexation vierge
     */
    
    public Indexation() throws IOException {
        this.dictioMots = new DictionnaireHash();
        this.dictioDocuments = new DictionnaireHash();
        this.matriceOccurences = new MatriceHash();
    }
    
    /**
     * Créer une Indexation à partir d'un fichier
     * @param chemin Le chemin vers le fichier
     */
    public Indexation(String chemin) throws IOException {
        this();
        this.charger(chemin);
    }

    /**
     * Ajouter un mot à l'Indexation
     * @param mot le mot à ajouter
     */
    public void ajouterMot(String mot) {
        this.dictioMots.ajouterMot(mot);
    }

    /**
     * Ajouter un document à l'Indexation
     * @param document Le document à ajouter
     * @param compter Compter automatiquement le document dans la matrice
     */
    public void ajouterDocument(String document) throws IOException {
        File fichier = new File(document);
        if (!fichier.exists() || !fichier.isFile()) throw new FileNotFoundException("Le fichier " + document + " n'a pas été trouvé.");
        this.dictioDocuments.ajouterMot(fichier.getName());
        this.compter(fichier.getPath());
    }

    /**
     * Affecter la valeur d'un certain mot pour un certain document
     * @param mot Le mot à affecter
     * @param document Le document dans lequel on doit affecter
     * @param n L'entier à affecter
     */
    public void affecte(String mot, String document, int n) {
        if (this.dictioMots != null && this.dictioDocuments != null) {
            int m = this.dictioMots.indiceMot(mot);
            if (m != -1) {
                int d = this.dictioDocuments.indiceMot(document);
                if (d != -1) {
                    this.matriceOccurences.affecte(d, m, n);
                }
            }
        }
    }

    /**
     * Incrémenter la valeur d'un certain mot pour un certain document
     * @param mot Le mot à incrémenter
     * @param document Le document dans lequel on doit incrémenter
     */
    public void incremente(String mot, String document) {
        if (this.dictioMots != null && this.dictioDocuments != null) {
            int m = this.dictioMots.indiceMot(mot);
            if (m != -1) {
                int d = this.dictioDocuments.indiceMot(document);
                if (d != -1) {
                    this.matriceOccurences.incremente(d, m);
                }
            }
        }
    }

    /**
     * Renvoie le nombre d'occurences du mot donné dans le document donné, sinon -1 si le couple n'est pas trouvé
     * @param mot Le mot à chercher
     * @param document Le document à chercher
     */
    public int val(String mot, String document) {
        int v = -1;
        if (this.dictioMots != null && this.dictioDocuments != null) {
            int m = this.dictioMots.indiceMot(mot);
            if (m != -1) {
                int d = this.dictioDocuments.indiceMot(document);
                if (d != -1) {
                    v = this.matriceOccurences.val(d, m);
                }
            }
        }
        return v;
    }

    /**
     * Compte l'occurence des mots d'un certain document
     * @param document le document à compter
     * @throws IOException
     */
    public void compter(String document) throws IOException {
        LecteurDocumentNaif lecteur = new LecteurDocumentNaif(document);
        lecteur.demarrer();
        while (!lecteur.finDeSequence()) {
            this.ajouterMot(lecteur.elementCourant());
            this.incremente(lecteur.elementCourant(), document);
            lecteur.avancer();
        }
    }

    /**
     * Sauvegarder l'Indexation dans un certain fichier
     * @param chemin Le chemin vers le fichier
     * @throws IOException
     */
    public void sauver(String chemin) throws IOException {
        return;
    }


    /**
     * Charge l'Indexation à l'aide des deux fichiers
     * @param chemin Le chemin vers le fichier
     * @throws IOException
     */
    public void charger(String chemin) throws IOException {
        return;
    }

}
