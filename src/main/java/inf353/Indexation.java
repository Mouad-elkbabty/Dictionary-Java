package inf353;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

public class Indexation {

    /**
     * Les attributs de la classe : deux DictionnaireHash pour les mots et les
     * documents ainsi qu'une MatriceHash pour les occurences
     */
    public DictionnaireHash dictioMots;
    public DictionnaireHash dictioDocuments;
    public MatriceHash matriceOccurences;

    /**
     * Crée une Indexation vierge
     */
    public Indexation() throws IOException {
        this.dictioMots = new DictionnaireHash(50000);
        this.dictioDocuments = new DictionnaireHash(50000);
        this.matriceOccurences = new MatriceHash();
    }

    /**
     * Crée une Indexation à partir d'un dossier
     * Les noms des fichiers contenant les documents, les mots et la matrice seront
     * respectivement
     * DictionnaireDocuments, DictionnaireMots et MatriceOccurences avec l'extension
     * txt
     * 
     * @param chemin Le chemin vers le dossier
     */
    public Indexation(String chemin) throws IOException {
        this();
        this.charger(chemin);
    }

    /**
     * Ajoute un mot à l'Indexation
     * 
     * @param mot le mot à ajouter
     */
    public void ajouterMot(String mot) {
        this.dictioMots.ajouterMot(mot);
    }

    /**
     * Ajoute un document à l'Indexation et le compte
     * 
     * @param document Le document à ajouter
     */
    public void ajouterDocument(String document) throws IOException {
        File fichier = new File(document);
        if (!fichier.exists() || !fichier.isFile())
            throw new FileNotFoundException("Le fichier " + document + " n'a pas été trouvé.");
        this.dictioDocuments.ajouterMot(fichier.getName());
        LecteurDocumentNaif lecteur = new LecteurDocumentNaif(document);
        lecteur.demarrer();
        while (!lecteur.finDeSequence()) {
            this.ajouterMot(lecteur.elementCourant());
            this.incremente(lecteur.elementCourant(), fichier.getName());
            lecteur.avancer();
        }
    }

    /**
     * Affecter la valeur d'un certain mot pour un certain document
     * 
     * @param mot      Le mot à affecter
     * @param document Le document dans lequel on doit affecter
     * @param n        L'entier à affecter
     */
    public void affecte(String mot, String document, int n) {
        int m = this.dictioMots.indiceMot(mot);
        if (m != -1) {
            int d = this.dictioDocuments.indiceMot(document);
            if (d != -1) {
                this.matriceOccurences.affecte(d, m, n);
            }
        }
    }

    /**
     * Incrémente la valeur d'un certain mot pour un certain document
     * 
     * @param mot      Le mot à incrémenter
     * @param document Le document dans lequel on doit incrémenter
     */
    public void incremente(String mot, String document) {
        int m = this.dictioMots.indiceMot(mot);
        if (m != -1) {
            int d = this.dictioDocuments.indiceMot(document);
            if (d != -1) {
                this.matriceOccurences.incremente(d, m);
            }
        }
    }

    /**
     * Renvoie le nombre d'occurences du mot donné dans le document donné, sinon 0
     * si le couple n'est pas trouvé
     * 
     * @param mot      Le mot à chercher
     * @param document Le document à chercher
     */
    public int val(String mot, String document) {
        int v = 0;
        int m = this.dictioMots.indiceMot(mot);
        if (m != -1) {
            int d = this.dictioDocuments.indiceMot(document);
            if (d != -1) {
                v = this.matriceOccurences.val(d, m);
            }
        }
        return v;
    }

    /**
     * Renvoie l'occurence maximale trouvée dans le document
     * Si null ou "" est donné, cherche dans l'entièreté de l'Indexation
     * Renvoie -1 si le document n'est pas trouvé
     * @param document le document dans lequel faire la recherche ou null ou ""
     */
    public int maxOccurrence(String document) {
        int val = -1;
        CelluleMatrice cc = null;
        int index = 0;
        if (document == null || document == "") {
            index = this.dictioDocuments.indiceMot(document);
            if (index != -1) {
                val = 0;
                cc = this.matriceOccurences.T[index];
                while (cc != null) {
                    if (cc.elt > val) {
                        val = cc.elt;
                    }
                    cc = cc.suiv;
                }
            }
        } else {
            val = 0;
            while (index != this.dictioDocuments.nbMots()) {
                cc = this.matriceOccurences.T[index];
                while (cc != null) {
                    if (cc.elt > val) {
                        val = cc.elt;
                    }
                    cc = cc.suiv;
                }
                index++;
            }
        }
        return val;
    }

    /**
     * Sauvegarde l'Indexation dans un certain dossier
     * Les noms des fichiers contenant les documents, les mots et la matrice seront
     * respectivement
     * DictionnaireDocuments, DictionnaireMots et MatriceOccurences avec l'extension
     * txt
     * 
     * @param chemin Le chemin vers le dossier
     * @throws IOException
     */
    public void sauver(String chemin) throws IOException {
        File dossier = new File(chemin);
        if (!dossier.isDirectory()) {
            dossier.mkdirs();
        }
        this.dictioDocuments.sauver(chemin + "DictionnaireDocuments.txt");
        this.dictioMots.sauver(chemin + "DictionnaireMots.txt");
        this.matriceOccurences.sauver(chemin + "MatriceOccurences.txt");
    }

    /**
     * Charge l'Indexation contenu dans un certain dossier
     * Les noms des fichiers contenant les documents, les mots et la matrice seront
     * respectivement
     * DictionnaireDocuments, DictionnaireMots et MatriceOccurences avec l'extension
     * txt
     * 
     * @param chemin Le chemin vers le dossier
     * @throws IOException
     */
    public void charger(String chemin) throws IOException {
        this.dictioDocuments.charger(chemin + "DictionnaireDocuments.txt");
        this.dictioMots.charger(chemin + "DictionnaireMots.txt");
        this.matriceOccurences.charger(chemin + "MatriceOccurences.txt");
    }

}