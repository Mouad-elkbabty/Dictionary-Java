package inf353;

import inf353.DictionnaireNaif;
import java.io.IOException;

public class Indexation {

    // le DictionnaireNaif de mots
    public DictionnaireNaif dictioMots;
    // la longueur maximale actuelle de dictioMots
    public int maxMots;
    // le DictionnaireNaif de noms de documents
    public DictionnaireNaif dictioDocuments;
    // la longueur maximale actuelle de dictioDocuments
    public int maxDocuments;
    // la MatriceIndexNaive des occurences des mots en fonction du document
    public MatriceIndexNaive matriceOccurences;

    /**
     * Créer une Indexation vierge
     */
    public Indexation() {
        this.dictioMots = null;
        this.maxMots = 0;
        this.dictioDocuments = null;
        this.maxDocuments = 0;
        this.matriceOccurences = null;
    }
    
    /**
     * Créer une Indexation à partir d'un fichier
     * @param nomFichier Le nom du fichier
     */
    public Indexation(String nomFichier) throws IOException {
        // utiliser la méthode charger()
    }

    /**
     * Ajouter un mot à l'Indexation
     * @param mot le mot à ajouter
     */
    public void ajouterMot(String mot) {
        if (this.maxMots == 0) {
            this.maxMots = 10;
            this.dictioMots = new DictionnaireNaif(this.maxMots);
            this.dictioMots.ajouterMot(mot);
            if (this.dictioDocuments != null) this.changerMatrice();
        } else {
            if (this.maxMots == this.dictioMots.nbMots()) {
                this.maxMots *= 2;
                DictionnaireNaif nouveauDictio = new DictionnaireNaif(this.maxMots);
                for (int i = 0; i != this.dictioMots.nbMots(); i++) {
                    nouveauDictio.ajouterMot(this.dictioMots.motIndice(i));
                }
                this.dictioMots = nouveauDictio;
                this.changerMatrice();
            }
            this.dictioMots.ajouterMot(mot);
        }
    }

    /**
     * Ajouter un document à l'Indexation
     * @param document Le document à ajouter
     * @param compter Compter automatiquement le document dans la matrice
     */
    public void ajouterDocument(String document, boolean compter) throws IOException {
        if (this.maxDocuments == 0) {
            this.maxDocuments = 10;
            this.dictioDocuments = new DictionnaireNaif(this.maxDocuments);
            this.dictioDocuments.ajouterMot(document);
            if (this.dictioMots != null) this.changerMatrice();
        } else {
            if (this.maxDocuments == this.dictioDocuments.nbMots()) {
                this.maxDocuments *= 2;
                DictionnaireNaif nouveauDictio = new DictionnaireNaif(this.maxDocuments);
                for (int i = 0; i != this.dictioDocuments.nbMots(); i++) {
                    nouveauDictio.ajouterMot(this.dictioDocuments.motIndice(i));
                }
                this.dictioDocuments = nouveauDictio;
                this.changerMatrice();
            }
            this.dictioDocuments.ajouterMot(document);
        }
        if (compter) this.compter(document);
    }

    /**
     * Changer matriceOccurences avec les nouvelles tailles, ou alors créer si elle n'existe pas
     */
    public void changerMatrice() {
        if (this.matriceOccurences == null) {
            this.matriceOccurences = new MatriceIndexNaive(this.maxDocuments, this.maxMots);
        } else {
            int tailleDocuments = this.matriceOccurences.matrice.length;
            int tailleMots = this.matriceOccurences.matrice[0].length;
            MatriceIndexNaive nouvelleMatrice = new MatriceIndexNaive(this.maxDocuments, this.maxMots);
            for (int d = 0; d != tailleDocuments; d++) {
                for (int m = 0; m != tailleMots; m++) {
                    int valeur = this.matriceOccurences.val(d, m);
                    nouvelleMatrice.affecte(d, m, valeur);
                }
            }
            this.matriceOccurences = nouvelleMatrice;
        }
    }

    /**
     * Affecter la valeur d'un certain mot pour un certain document
     * @param mot Le mot à affecter
     * @param document Le document dans lequel on doit affecter
     * @param n L'entier à affecter
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
     * Incrémenter la valeur d'un certain mot pour un certain document
     * @param mot Le mot à incrémenter
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
     * Renvoie le nombre d'occurences du mot donné dans le document donné, sinon -1 si le couple n'est pas trouvé
     * @param mot Le mot à chercher
     * @param document Le document à chercher
     */
    public int val(String mot, String document) {
        int v = -1;
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
     * Compte l'occurence des mots d'un certain document
     * @param document le document à compter
     */
    public void compter(String document) throws IOException {
        
        LecteurDocumentNaif lecteur = new LecteurDocumentNaif(document);
        lecteur.demarrer();
        while (!lecteur.finDeSequence()) {
            this.ajouterMot(lecteur.elementCourant());
            this.incremente(lecteur.elementCourant(), document);
        }
    }

    /**
     * Sauvegarder l'Indexation dans un certain fichier
     * @param nomFichier Le nom du fichier
     */
    public void sauver(String nomFichier) {
        
    }

    /**
     * Charger une Indexation grâce à un fichier sauvegardé
     * @param nomFichier Le nom du fichier
     */
    public void charger(String nomFichier) {

    }

}
