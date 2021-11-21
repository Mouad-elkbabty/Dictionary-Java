package inf353;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Writer;
import java.text.Normalizer;
import java.util.regex.Pattern;
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
    // le dossier utilisé pour charger les documents
    public String nomDossier;
    // liste des accents sous forme d'un tableau de tableaux { caractère, liste des accents }
    public static String[][] accents = {
        { "A", "ÀÁÄÂ" },
        { "C", "Ç" },
        { "E", "ÉÈËÊ" },
        { "I", "ÏÎÍÌ" },
        { "O", "ÔÖÓÒ" },
        { "U", "ÛÜÚÙ" },
        { "a", "àáäâ" },
        { "c", "ç" },
        { "e", "éèëê" },
        { "i", "ïîíì" },
        { "o", "ôöóò" },
        { "u", "ûüúù" }
    };

    /**
     * Créer une Indexation vierge
     */
    public Indexation(String nomDossier) {
        this.dictioMots = null;
        this.maxMots = 0;
        this.dictioDocuments = null;
        this.maxDocuments = 0;
        this.matriceOccurences = null;
        this.nomDossier = nomDossier;
    }
    
    /**
     * Créer une Indexation à partir d'un fichier
     * /!\ LE FICHIER DOIT OBLIGATOIREMENT SE TROUVER DANS LE CHEMIN INDIQUÉ
     * @param nomDossier Le dossier utilisé pour charger les documents
     * @param nomFichierMatrice Le nom du fichier qui contient la matrice
     * @param nomFichierDictionnaires Le nom du fichier qui contient les dictionnaires
     */
    public Indexation(String nomDossier, String nomFichierMatrice, String nomFichierDictionnaires) throws IOException {
        this(nomDossier);
        this.charger(nomFichierMatrice, nomFichierDictionnaires);
    }

    /**
     * Ajouter un mot à l'Indexation
     * @param mot le mot à ajouter
     */
    public void ajouterMot(String mot) {
        if (mot.length() > 39) throw new Error("Le mot donné fait 40 caractères ou plus.");
        if (this.maxMots == 0) {
            this.maxMots = 10;
            this.dictioMots = new DictionnaireNaif(this.maxMots);
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
        }
        this.dictioMots.ajouterMot(mot);
    }

    /**
     * Ajouter un document à l'Indexation
     * @param document Le document à ajouter
     * @param compter Compter automatiquement le document dans la matrice
     */
    public void ajouterDocument(String document, boolean compter) throws IOException {
        if (document.length() > 39) throw new Error("Le document donné fait 40 caractères ou plus.");
        if (this.maxDocuments == 0) {
            this.maxDocuments = 10;
            this.dictioDocuments = new DictionnaireNaif(this.maxDocuments);
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
        }
        this.dictioDocuments.ajouterMot(document);
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
        LecteurDocumentNaif lecteur = new LecteurDocumentNaif(this.nomDossier + document);
        lecteur.demarrer();
        while (!lecteur.finDeSequence()) {
            System.out.println(lecteur.elementCourant());
            String mot = retirerAccents(lecteur.elementCourant()).toLowerCase();
            System.out.println(mot);
            this.ajouterMot(mot);
            this.incremente(mot, document);
            lecteur.avancer();
        }
    }

    /**
     * Retirer les accents d'un texte
     * https://stackoverflow.com/a/46118158/14464264
     * @param texte Le texte voulu
     */
    public static String retirerAccents(String texte) {
        String resultat = "";
        for (int i = 0; i < texte.length(); i++) {
            char caractere = texte.charAt(i);
            for (int a = 0; a < accents.length; a++) {
                if (accents[a][0].equals(caractere + "")) {
                    for (int e = 0; e < accents[a][1].length(); e++) {
                        if (accents[a][1].charAt(e) == caractere) caractere = accents[a][1].charAt(e);
                    }
                }
            }
            resultat += caractere;
        }
        return resultat;
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
     * Charger une Indexation grâce à un fichier sauvegardé
     * /!\ LE FICHIER DOIT OBLIGATOIREMENT SE TROUVER DANS LE CHEMIN INDIQUÉ
     * @param nomFichier Le nom du fichier
     */
    public void charger(String nomDeFichierMatrice, String nomDeFichierMot) throws IOException  {
        // Utilisation du constructeur de MatriceIndexNaive
        this.matriceOccurences = new MatriceIndexNaive(this.nomDossier + nomDeFichierMatrice);

        // Initialisation du fichier et du Buffer
        File fichier = new File(this.nomDossier + nomDeFichierMot);
        if (!fichier.exists() || !fichier.isFile()) {
            throw new FileNotFoundException("Aucun fichier du nom de " + this.nomDossier + nomDeFichierMot + " n'a été trouvé.");
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
