package inf353;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

public class Indexation {

    // attributs
    public DictionnaireHash dictioMots;
    public DictionnaireHash dictioDocuments;
    public MatriceHash matriceOccurences;

    /**
     * Crée une Indexation vierge
     */
    public Indexation() throws IOException {
        this.dictioMots = new DictionnaireHash();
        this.dictioDocuments = new DictionnaireHash();
        this.matriceOccurences = new MatriceHash();
    }
    
    /**
     * Crée une Indexation à partir d'un dossier
     * Les noms pour les documents, les mots puis les matrices seront respectivement
     * DictionnaireDocuments, DictionnaireMots et MatriceOccurences avec l'extension txt
     * @param chemin Le chemin vers le fichier
     */
    public Indexation(String chemin) throws IOException {
        this();
        this.charger(chemin);
    }

    /**
     * Ajoute un mot à l'Indexation
     * @param mot le mot à ajouter
     */
    public void ajouterMot(String mot) {
        this.dictioMots.ajouterMot(mot);
    }

    /**
     * Ajoute un document à l'Indexation et le compte
     * @param document Le document à ajouter
     */
    public void ajouterDocument(String document) throws IOException {
        File fichier = new File(document);
        if (!fichier.exists() || !fichier.isFile()) throw new FileNotFoundException("Le fichier " + document + " n'a pas été trouvé.");
        this.dictioDocuments.ajouterMot(fichier.getName());
        LecteurDocumentNaif lecteur = new LecteurDocumentNaif(document);
        lecteur.demarrer();
        while (!lecteur.finDeSequence()) {
            this.ajouterMot(lecteur.elementCourant());
            this.incremente(lecteur.elementCourant(), document);
            lecteur.avancer();
        }
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
     * Incrémente la valeur d'un certain mot pour un certain document
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
     * Sauvegarde l'Indexation dans un certain dossier
     * Les noms pour les documents, les mots puis les matrices seront respectivement
     * DictionnaireDocuments, DictionnaireMots et MatriceOccurences avec l'extension txt
     * @param chemin Le chemin vers le dossier
     * @throws IOException
     */
    public void sauver(String chemin) throws IOException {
        this.dictioDocuments.sauver(chemin + "DictionnaireDocuments.txt");
        this.dictioMots.sauver(chemin + "DictionnaireMots.txt");
        this.matriceOccurences.sauver(chemin + "MatriceOccurences.txt");
    }

    /**
     * Charge l'Indexation contenu dans un certain dossier
     * Les noms pour les documents, les mots puis les matrices doivent respectivement être
     * DictionnaireDocuments, DictionnaireMots et MatriceOccurences avec l'extension txt
     * @param chemin Le chemin vers le dossier
     * @throws IOException
     */
    public void charger(String chemin) throws IOException {
        this.dictioDocuments.charger(chemin + "DictionnaireDocumentSave.txt");
        this.dictioMots.charger(chemin + "DictionnaireDocumentMot.txt");
        this.matriceOccurences.charger(chemin + "MatriceOccurence.txt");
    }

}
