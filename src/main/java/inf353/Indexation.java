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
    // le dossier utilisé pour charger les documents
    public String nomDossier;
    public int nbMotInterdit = 495;
    //public String[] stopList;
    /**
     * Créer une Indexation vierge
     */
    public Indexation(String nomDossier) throws IOException, FileNotFoundException{
        this.dictioMots = null;
        this.maxMots = 0;
        this.dictioDocuments = null;
        this.maxDocuments = 0;
        this.matriceOccurences = null;
        this.nomDossier = nomDossier;

        /*
        //stopList = new String[nbMotInterdit];
        File file = new File ("./src/main/resources/inf353/DictionnaireStoplist.txt");

        if (!file.exists() || !file.isFile()) {
            throw new FileNotFoundException("Aucun fichier stoplist n'a été trouvé.");
        }

        BufferedReader buffer = new BufferedReader(new FileReader(file));
        int i = 0;
        String ligne = buffer.readLine();
        while(ligne != null && i < nbMotInterdit) // Rempli la stopList
        {
            //stopList[i] = this.format(ligne);
            ligne = buffer.readLine();
            i++;
        }
        buffer.close();
        */

    }
    
    /**
     * Créer une Indexation à partir d'un fichier
     * /!\ LE FICHIER DOIT OBLIGATOIREMENT SE TROUVER DANS LE CHEMIN INDIQUÉ
     * @param nomDossier Le dossier utilisé pour charger les documents
     * @param nomFichierMatrice Le nom du fichier qui contient la matrice
     * @param nomFichierDictionnaires Le nom du fichier qui contient les dictionnaires
     */
    public Indexation(String nomDossier, String nomFichierMatrice, String nomFichierDictionnaires) throws IOException, FileNotFoundException {
        this(nomDossier);
        this.charger(nomFichierMatrice, nomFichierDictionnaires);
    }

    /**
     * Ajouter un mot à l'Indexation
     * @param mot le mot à ajouter
     */
    public void ajouterMot(String mot) {
        mot = this.format(mot);
            if (mot.length() > 39) throw new Error("Le mot donné fait 40 caractères ou plus.");
            if (this.maxMots == 0) {
                this.maxMots = 10;
                this.dictioMots = new DictionnaireNaif(this.maxMots);
                if (this.dictioDocuments != null) this.changerMatrice();
            } 
            else {
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
        if (this.dictioMots != null && this.dictioDocuments != null) {
            mot = this.format(mot);
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
            mot = this.format(mot);
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
            mot = this.format(mot);
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
        LecteurDocumentNaif lecteur = new LecteurDocumentNaif(this.nomDossier + document);
        lecteur.demarrer();
        while (!lecteur.finDeSequence()) {
            String mot = this.supprimerAccents(lecteur.elementCourant());
            this.ajouterMot(mot);
            this.incremente(mot, document);
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
     * Charger une Indexation grâce à un fichier sauvegardé
     * /!\ LE FICHIER DOIT OBLIGATOIREMENT SE TROUVER DANS LE CHEMIN INDIQUÉ
     * @param nomFichier Le nom du fichier
     */
    public void charger(String nomDeFichierMatrice, String nomDeFichierMot) throws IOException, FileNotFoundException  {
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

    /**
     * Renvoie true si le mot considéré est un mot interdit par la stopList
     * @param mot Le mot considéré
     */
    /*
    public boolean estInterdit(String mot) {
        int i = 0;
        while(i < this.nbMotInterdit && stopList[i] != null && !mot.equals(stopList[i]))
        {
            i++;
        }
        return !(i == this.nbMotInterdit);
    }
*/
    /**
	 * Renvoie le mot m sans accents ni majuscules
	 */
    public String supprimerAccents(String m) {
        String r = "";
        int i = 0;
        int j = 0;
        char[] a = {'Ç','ç','é','è','ê','ë','ù','ü','ô','ö','æ','à','É','È','Ê','Ë','Ù','Ü','Ô','Ö','Æ','À'};
        char[] as = {'C','c','e','e','e','e','u','u','o','o','e','a','E','E','E','E','U','U','O','O','E','A'};
    
    
        while(i < m.length()) {
        if(m.charAt(i) >= 65 && m.charAt(i) <= 90){
            r = r + (char)(m.charAt(i)+32);
            i++;
            }
        else if(m.charAt(i) >= 97 && m.charAt(i) <= 122){
            r = r + m.charAt(i);
            i++;
            }
        else{
            j=0;
            while(j < a.length && m.charAt(i) != a[j]){
                j++;
                }
            if (j<a.length){
                r = r + as[j];
                i++;
                }
            else{
                r = r +m.charAt(i);
                i++;
                }
            }
        }
        return r;
    }  

    public String format(String mot) {
        return this.supprimerAccents(mot).toLowerCase();
    }
}
