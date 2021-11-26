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
    
    public Indexation() throws IOException {
        this.maxMots = 100001;
        this.dictioMots = new DictionnaireNaif(this.maxMots);
        this.maxDocuments = 100001;
        this.dictioDocuments = new DictionnaireNaif(this.maxDocuments);
        this.matriceOccurences = new MatriceIndexNaive(this.maxDocuments, this.maxMots);
    }
    
    /**
     * Créer une Indexation à partir d'un fichier
     * @param fichierMatrice Le nom du fichier qui contient la matrice
     * @param fichierDictionnaires Le nom du fichier qui contient les dictionnaires
     */
    public Indexation(String fichierMatrice, String fichierDictionnaires) throws IOException {
        this();
        this.charger(fichierMatrice, fichierDictionnaires);
    }

    /**
     * Ajouter un mot à l'Indexation
     * @param mot le mot à ajouter
     */
    public void ajouterMot(String mot) {
        if (this.dictioMots.estValide(mot)) {
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
    public void ajouterDocument(String document) throws IOException {
        File fichier = new File(document);
        if (!fichier.exists() || !fichier.isFile()) throw new FileNotFoundException("Le fichier " + document + " n'a pas été trouvé.");
        if (this.dictioDocuments.estValide(fichier.getName())) {
            if (this.maxDocuments == this.dictioDocuments.nbMots()) {
                this.maxDocuments *= 2;
                DictionnaireNaif nouveauDictio = new DictionnaireNaif(this.maxDocuments);
                for (int i = 0; i != this.dictioDocuments.nbMots(); i++) {
                    nouveauDictio.ajouterMot(this.dictioDocuments.motIndice(i));
                }
                this.dictioDocuments = nouveauDictio;
                this.changerMatrice();
            }
            this.dictioDocuments.ajouterMot(fichier.getName());
            this.compter(fichier.getPath());
        }
    }

    /**
     * Changer matriceOccurences avec les nouvelles tailles, ou alors créer si elle n'existe pas
     */
    public void changerMatrice() {
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
     * @param nomFichier Le nom du fichier
     */
    public void sauver(String nomDeFichierMatrice, String nomDeFichierMot) throws FileNotFoundException, IOException {
        // Utilisation de la méthode sauver de MatriceIndexNaive
        this.matriceOccurences.sauver(nomDeFichierMatrice);

        // Initialisation du Buffer
        Writer writer = new FileWriter(nomDeFichierMot, false);
        BufferedWriter buffer = new BufferedWriter(writer);

        // Écriture de dictioMots
        String ligne = "";
        for (int mots = 0; mots < this.dictioMots.nbMots(); mots++) {
            ligne += this.dictioMots.motIndice(mots) + ",";
        }
        if (ligne != "") ligne = ligne.substring(0, ligne.length()-1);
        buffer.write(ligne);
        buffer.newLine();

        // Écriture de dictioDocuments
        ligne = "";
        for (int docs = 0; docs < this.dictioDocuments.nbMots(); docs++) {
            ligne += this.dictioDocuments.motIndice(docs) + ",";
        }
        if (ligne != "") ligne = ligne.substring(0, ligne.length()-1);
        buffer.write(ligne);

        // Enregistrement et fermeture du Buffer
        buffer.flush();
        buffer.close();
    }


    /**
     * Charge l'Indexation à l'aide des deux fichiers
     * @param fichierMatrice
     * @param fichierDictionnaires
     * @throws IOException
     */
    public void charger(String fichierMatrice, String fichierDictionnaires) throws IOException {
        // Utilisation du constructeur de MatriceIndexNaive
        this.matriceOccurences = new MatriceIndexNaive(fichierMatrice);

        // Initialisation du fichier et du Buffer
        File fichier = new File(fichierDictionnaires);
        if (!fichier.exists() || !fichier.isFile()) {
            throw new FileNotFoundException("Aucun fichier du nom de " + fichierDictionnaires + " n'a été trouvé.");
        }
        BufferedReader buffer = new BufferedReader(new FileReader(fichier));

        // Remplissage de dictioMots
        String ligne = buffer.readLine();
        String[] mots = ligne.split(",");
        this.maxMots = Math.max(10, mots.length*2);
        this.dictioMots = new DictionnaireNaif(this.maxMots);
        for (int m = 0; m < mots.length; m++) {
            this.dictioMots.ajouterMot(mots[m]);
        }
        
        // Remplissage de dictioDocument
        ligne = buffer.readLine();
        String[] documents = ligne.split(",");
        this.maxDocuments = Math.max(10, documents.length*2);
        this.dictioDocuments = new DictionnaireNaif(this.maxDocuments);
        for (int d = 0; d < documents.length; d++) {
            this.dictioDocuments.ajouterMot(documents[d]);
        }

        // Fermeture du Buffer
        buffer.close();
    }

}
