package inf353;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class DictionnaireHash implements Dictionnaire {

    /**
     * Attributs de la classe : le tableau T de taille N ainsi que le nombre de mots
     * totaux nb
     */
    public int N;
    public CelluleDictio[] T;
    public int nb;

    /**
     * Crée un DictionnaireHash vide
     */
    public DictionnaireHash() {
        this(4000);
    }

    /**
     * Crée un DictionnaireHash vide de taille n
     * 
     * @param n la taille du DictionnaireHash
     */
    public DictionnaireHash(int n) {
        this.N = n;
        this.T = new CelluleDictio[N];
        this.nb = 0;
    }

    /**
     * Crée un DictionnaireHash à partir du fichier demandé
     * 
     * @param chemin le chemin où le fichier est stocké
     */
    public DictionnaireHash(String chemin) throws IOException {
        this();
        this.charger(chemin);
    }


    /**
     * Vide le dictionnaire
     */
    @Override
    public void vider() {
        this.T = new CelluleDictio[N];
        this.nb = 0;
    }

    /**
     * Ajoute un mot au dictionnaire si il n'y est pas déjà présent
     * sinon incremente l'attribut occ de la cellule
     * 
     * @param m Le mot à ajouter
     */
    @Override
    public void ajouterMot(String m) {
        if (!contient(m)) {
            int n = Math.abs(m.hashCode() % N);
            T[n] = new CelluleDictio(m, this.nb, 1, 0, T[n]);
            this.nb += 1;
        } else {
            int n = Math.abs(m.hashCode() % N);
            CelluleDictio cc =  this.T[n];
            while(cc != null && !cc.elt.equals(m)){
                cc =cc.suiv;
            }
            if(cc != null){
                cc.occ ++;
            }
        }
    }

    /**
     * Ajoute un mot au dictionnaire si il n'y est pas déjà présent
     * 
     * @param m Le mot à ajouter
     * @param occ l'occurence du mot à ajouter
     * @param doc l'occurence des documents contenant ce mot
     */
    public void ajouterMot(String m, int occ, int df)
    {
        if (!contient(m)) {
            int n = Math.abs(m.hashCode() % N);
            T[n] = new CelluleDictio(m, nb, occ, df, T[n]);
            this.nb += 1;
        }
    }

    /**
     * incrémente le nombre de documents contenant le mot en paramètre
     * @param m Le mot à incrémenter
     */
    public void incrementeDf(String m){
        if(contient(m)){
            int n = Math.abs(m.hashCode() % N);
            CelluleDictio cc = T[n];
            while(!cc.elt.equals(m)){
                cc = cc.suiv;
            }
            cc.df++;
        }
    }


    /**
     * Retourne l'indice du mot dans le DictionnaireHash ou -1 s'il n'est pas trouvé
     * 
     * @param m Le mot à tester
     */
    @Override
    public int indiceMot(String m) {
        int n = -1;
        int i = Math.abs(m.hashCode() % N);
        CelluleDictio cc = T[i];
        while (cc != null && !cc.elt.equals(m)) {
            cc = cc.suiv;
        }
        if (cc != null) {
            n = cc.ind;
        }
        return n;
    }


        /**
     * Retourne le nombre de document contennant le mot m (df). Renvoie 0 s'il n'est pas trouvé
     * 
     * @param m Le mot à tester
     */
    public int dfMot(String m) {
        int n = 0;
        int i = Math.abs(m.hashCode() % N);
        CelluleDictio cc = T[i];
        while (cc != null && !cc.elt.equals(m)) {
            cc = cc.suiv;
        }
        if (cc != null) {
            n = cc.df;
        }
        return n;
    }
        /**
     * Retourne le nombre d'occurrence total du mot m dans le corpus (occ). Renvoie 0 s'il n'est pas trouvé
     * 
     * @param m Le mot à tester
     */

    public int nbOccMot(String m){
        int n = 0;
        int i = Math.abs(m.hashCode() % N);
        CelluleDictio cc = T[i];
        while (cc != null && !cc.elt.equals(m)) {
            cc = cc.suiv;
        }
        if (cc != null) {
            n = cc.occ;
        }
        return n;
    }
    /**
     * Retourne le mot contenu à l'indice i dans le DictionnaireHash. Renvoie null s'il
     * n'existe pas
     * 
     * @param i L'indice à tester
     */
    @Override
    public String motIndice(int i) {
        String s = null;
        if (this.nb > i) {
            int j = 0;
            while (j < N && s == null) {
                CelluleDictio cc = this.T[j];
                while (cc != null && cc.ind != i) {
                    cc = cc.suiv;
                }
                if (cc != null) {
                    s = cc.elt;
                }
                j++;
            }
        }
        return s;
    }

    /**
     * Renvoie vrai si le mot est contenu dans le DictionnaireHash
     * 
     * @param m Le mot à tester
     * @return
     */
    @Override
    public boolean contient(String m) {
        return indiceMot(m) != -1;
    }

    /**
     * Retourne le nombre de mots dans le DictionnaireHash
     * 
     * @return
     */
    @Override
    public int nbMots() {
        return this.nb;
    }

    /**
     * Retourne vrai si le préfixe est contenu dans l'un des mots du
     * DictionnaireHash
     * 
     * @param p Le préfixe à tester
     */
    @Override
    public boolean contientPrefixe(String p) {
        boolean trouve = false;
        int j = 0;
        while (j < N && !trouve) {
            CelluleDictio cc = this.T[j];
            while (cc != null && !cc.elt.startsWith(p)) {
                cc = cc.suiv;
            }
            if (cc != null) {
                trouve = true;
            }
            j++;
        }
        return trouve;
    }
    /**
     * Retourne le préfixe le plus long du mot contenu dans le DictionnaireHash
     * 
     * @param mot Le mot à tester
     */
    @Override
    public String plusLongPrefixeDe(String mot) {
        boolean trouve = this.contient(mot);
        while (mot.length() > 0 && !trouve) {
            mot = mot.substring(0, mot.length() - 1);
            trouve = this.contient(mot);
        }
        return mot;
    }


    /**
     * Enregistre le dictionnaire dans le chemin demandé
     * la première ligne est la liste des elt (mots ou documents)
     * la deuxième ligne est le nombre total d'occurrence dans le corpus (occ)
     * la troisième ligne est le nombre de doc ou elt apparait dans le corpus (df)
     *
     * @param chemin le chemin vers le fichier où sera stocké le DictionnaireHash
     */
    public void sauver(String chemin) throws IOException {
        // Chargement du fichier
        File fichier = new File(chemin);
        if (fichier.isDirectory())
            throw new IOException("Le chemin \"" + chemin + "\" est un dossier.");
        File dossier = new File(fichier.getParent());
        if (dossier != null && !dossier.isDirectory()) dossier.mkdir();
        fichier.createNewFile();

        // Chargement du tableau pour garder l'index des mots
        String[] mots = new String[this.nbMots()];
        int[] occ = new int[this.nbMots()];
        int[] df = new int[this.nbMots()];
        int i = 0;
        while (i < this.N) {
            CelluleDictio cc = this.T[i];
            while (cc != null) {
                mots[cc.ind] = cc.elt;
                occ[cc.ind] = cc.occ;
                df[cc.ind] = cc.df;
                cc = cc.suiv;
            }
            i++;
        }
        // Initialisation du Buffer
        BufferedWriter buffer = new BufferedWriter(new FileWriter(chemin, false));
        // Écriture du contenu du DictionnaireHash
        //Ecriture de la ligne contennant les mots
        String ligne = "";
        for (int j = 0; j < this.nbMots(); j++) {
            ligne += mots[j] + ",";
        }
        if (ligne != "")
        buffer.write(ligne);
        buffer.newLine();

        //Ecriture de la ligne contennant les occurence des mots
        ligne = "";
        for (int j = 0; j < this.nbMots(); j++) {
            ligne += occ[j] + ",";
        }
        if (ligne != "")
        buffer.write(ligne);
        buffer.newLine();

        //Ecriture de la ligne contennant le nombre de document contenants les mots
        ligne = "";
        for (int j = 0; j < this.nbMots(); j++) {
            ligne += df[j] + ",";
        }
        buffer.write(ligne);


        // Enregistrement et fermeture du Buffer
        buffer.flush();
        buffer.close();
    }


    /**
     * Charge le DictionnaireHash stocké dans le chemin demandé avec les attributs suivant
     * charge la première ligne qui est la liste des elt (mots ou documents).
     * charge deuxième ligne qui est le nombre total d'occurrence dans le corpus (occ).
     * charge troisième ligne qui est le nombre de doc ou elt apparait dans le corpus (df).
     * 
     * @param chemin le chemin où le fichier est stocké
     */
    public void charger(String chemin) throws IOException {
        // Initialisation du fichier et du Buffer
        File fichier = new File(chemin);
        if (!fichier.exists() || !fichier.isFile())
            throw new FileNotFoundException("Aucun fichier du nom de " + chemin + " n'a été trouvé.");
        BufferedReader buffer = new BufferedReader(new FileReader(fichier));

        // Vide du Dictionnaire
        this.vider();

        // Remplissage du Dictionnaire
        String ligne = buffer.readLine();
        String[] mots = new String[this.N];
        String motCourant = "";
        int i = 0;
        int j = 0;
        while (i <  ligne.length() && j < mots.length) {
            if (ligne.charAt(i) != ','){
                motCourant = motCourant + ligne.charAt(i);
            }   
            else{
                mots[j] = motCourant;
                motCourant = "";
                j++;
            }
            i++;
        }

        ligne = buffer.readLine();
        i = 0;
        j = 0;
        motCourant = "";
        String[] occString = new String[this.N];
        while ( i <  ligne.length() && j < occString.length){
            if (ligne.charAt(i) != ','){
                motCourant = motCourant + ligne.charAt(i);
            }   
            else{
                occString[j] = motCourant;
                motCourant = "";
                j++;
            }
            i++;
        }
        if (i < ligne.length()) {
            buffer.close();
            throw new Error("Tous les mots n'ont pas pu etre ajoutes au dictionnaire lors de son chargement.");
        }


        ligne = buffer.readLine();
        i = 0;
        j = 0;
        motCourant = "";
        String[] dfString = new String[this.N];
        while ( i <  ligne.length() && j < dfString.length){
            if (ligne.charAt(i) != ','){
                motCourant = motCourant + ligne.charAt(i);
            }   
            else{
                dfString[j] = motCourant;
                motCourant = "";
                j++;
            }
            i++;
        }
        if (i < ligne.length()) {
            buffer.close();
            throw new Error("Tous les documents n'ont pas pu etre ajoutes au dictionnaire lors de son chargement.");
        }

        for(int m = 0; m < mots.length && mots[m] != null; m++) {
            this.ajouterMot(mots[m], Integer.parseInt(occString[m]),Integer.parseInt(dfString[m]));
        }
        buffer.close();
    }

}
