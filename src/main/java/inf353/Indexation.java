package inf353;

public class Indexation {

    public DictionnaireNaif dictioMots;
    public int nbMots;
    public int maxMots;
    public DictionnaireNaif dictioDocuments;
    public int nbDocuments;
    public int maxDocuments;
    public MatriceIndexNaive matriceOccurences;

    /**
     * Crée une Indexation
     */
    public Indexation() {
        this.dictioMots = null;
        this.maxMots = 0;
        this.dictioDocuments = null;
        this.maxDocuments = 0;
        this.matriceOccurences = null;
    }

    /**
     * Ajouter un mot à l'Indexation
     * @param mot le mot à ajouter
     */
    public void ajouterMot(String mot) {
        if (this.maxMots == 0) {
            this.maxMots = 10;
            this.dictioMots = new DictionnaireNaif(this.maxMots);
            dictioMots.ajouterMot(mot);
            if (this.dictioDocuments != null) this.changerMatriceMots();
        } else {
            if (this.maxMots == this.dictioMots.nbMots()) {
                this.maxMots *= 2;
                DictionnaireNaif nouveauDictio = new DictionnaireNaif(this.maxMots);
                for (int i = 0; i != this.dictioMots.nbMots(); i++) {
                    nouveauDictio.ajouterMot(this.dictioMots.motIndice(i));
                }
                this.dictioMots = nouveauDictio;
                this.changerMatriceMots();
            }
            this.dictioMots.ajouterMot(mot);
        }
    }

    /**
     * Ajouter un document à l'Indexation
     * @param document le document à ajouter
     */
    public void ajouterDocument(String document) {
        if (this.maxDocuments == 0) {
            this.maxDocuments = 10;
            this.dictioDocuments = new DictionnaireNaif(this.maxDocuments);
            dictioDocuments.ajouterMot(document);
            if (this.dictioMots != null) this.changerMatriceDocuments();
        } else {
            if (this.maxDocuments == this.dictioDocuments.nbMots()) {
                this.maxDocuments *= 2;
                DictionnaireNaif nouveauDictio = new DictionnaireNaif(this.maxDocuments);
                for (int i = 0; i != this.dictioDocuments.nbMots(); i++) {
                    nouveauDictio.ajouterMot(this.dictioDocuments.motIndice(i));
                }
                this.dictioDocuments = nouveauDictio;
                this.changerMatriceDocuments();
            }
            this.dictioDocuments.ajouterMot(document);
        }
        this.compter(document);
    }

    /**
     * Changer la matrice d'occurences en fonction des mots
     */
    private void changerMatriceMots() {
        if (this.matriceOccurences == null) {
            this.matriceOccurences = new MatriceIndexNaive(this.maxDocuments, this.maxMots);
        } else {
            int tailleActuelle = this.matriceOccurences.matrice[0].length;
            MatriceIndexNaive nouvelleMatrice = new MatriceIndexNaive(this.maxDocuments, this.maxMots);
            
        }
    }

    /**
     * Changer la matrice d'occurences en fonction des mots
     */
    private void changerMatriceDocuments() {
        if (this.matriceOccurences == null) {
            this.matriceOccurences = new MatriceIndexNaive(this.maxDocuments, this.maxMots);
        } else {
            if (this.matriceOccurences.matrice.length != this.maxDocuments) {

            }
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
     * Compte l'occurence des mots d'un certain document
     * @param document 
     */
    public void compter(String document) {

    }

    /**
     * Sauvegarder l'Indexation dans un certain fichier
     * @param nomFichier le nom du fichier
     */
    public void sauver(String nomFichier) {

    }

}
