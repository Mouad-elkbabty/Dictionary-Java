package inf353;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

public class Indexation {

    /**
     * Les attributs de la classe : deux DictionnaireHash pour les mots et les
     * documents ainsi qu'une MatriceHash pour les Occurrences
     */
    public DictionnaireHash dictioMots;
    public DictionnaireHash dictioDocuments;
    public MatriceHash matriceOccurrences;

    /**
     * Crée une Indexation vierge
     */
    public Indexation() {
        this(20000, 100000, 100000);
    }

    /**
     * Crée une Indexation avec des tailles
     * @param tailleMots la taille du dictionnaire de mots
     * @param tailleDocuments la taille du dictionnaire de documents
     * @param tailleOccurrences la taille de la matrice d'occurrences
     */
    public Indexation(int tailleMots, int tailleDocuments, int tailleOccurrences) {
        this.dictioMots = new DictionnaireHash(tailleMots);
        this.dictioDocuments = new DictionnaireHash(tailleDocuments);
        this.matriceOccurrences = new MatriceHash(tailleOccurrences);
    }

    /**
     * Crée une Indexation à partir d'un dossier
     * Les noms des fichiers contenant les documents, les mots et la matrice seront
     * respectivement
     * DictionnaireDocuments, DictionnaireMots et MatriceOccurrences avec l'extension
     * txt
     * 
     * @param chemin Le chemin vers le dossier
     */
    public Indexation(String chemin) throws IOException {
        this(250000, 100000, 100000);
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
            System.out.println(lecteur.elementCourant());
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
                this.matriceOccurrences.affecte(d, m, n);
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
                this.matriceOccurrences.incremente(d, m);
                if(this.matriceOccurrences.val(d, m) == 1){
                    this.dictioMots.ajoutenbDoc(mot);
                }
            }
        }
    }

    /**
     * Renvoie le nombre d'Occurrences du mot donné dans le document donné, sinon 0
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
                v = this.matriceOccurrences.val(d, m);
            }
        }
        return v;
    }

    /**
     * Renvoie l'occurence maximale trouvée parmis les documents
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
                cc = this.matriceOccurrences.T[index];
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
                cc = this.matriceOccurrences.T[index];
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
     * DictionnaireDocuments, DictionnaireMots et MatriceOccurrences avec l'extension
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
        this.matriceOccurrences.sauver(chemin + "MatriceOccurrences.txt");
    }

    /**
     * Charge l'Indexation contenu dans un certain dossier
     * Les noms des fichiers contenant les documents, les mots et la matrice seront
     * respectivement
     * DictionnaireDocuments, DictionnaireMots et MatriceOccurrences avec l'extension
     * txt
     * 
     * @param chemin Le chemin vers le dossier
     * @throws IOException
     */
    public void charger(String chemin) throws IOException {
        File dossier  = new File(chemin);
        if(!dossier.isDirectory())
        {
            dossier.mkdirs();
        }
        this.dictioDocuments.charger(chemin + "DictionnaireDocuments.txt");
        this.dictioMots.charger(chemin + "DictionnaireMots.txt");
        this.matriceOccurrences.charger(chemin + "MatriceOccurrences.txt");
    }

}