package inf353;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class DictionnaireHash implements Dictionnaire {

    /// attributs
    public int N = 2000;
    public CelluleDictio[] T;
    public int nb;

    /**
     * Crée un DictionnaireHash vide
     */
    public DictionnaireHash() {
        this.T = new CelluleDictio[N];
        this.nb = 0;
    }

    public DictionnaireHash(int n)
    {
        this.N = n;
        this.T = new CelluleDictio[N];
        this.nb = 0;
    }

    public DictionnaireHash(String chemin)
    {
        
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
     * 
     * @param m Le mot à ajouter
     */
    @Override
    public void ajouterMot(String m) {
        if (!contient(m)) {
            int n = Math.abs(m.hashCode() % N);
            T[n] = new CelluleDictio(m, nb, T[n]);
            this.nb += 1;
        }
    }

    /**
     * Retourne l'indice du mot dans le DictionnaireHash ou -1 s'il n'est pas trouvé
     * 
     * @param m Le mot à tester
     * @return L'indice du mot testé
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
     * Retourne le mot contenu à l'indice i dans le DictionnaireHash ou null s'il n'existe pas
     * 
     * @param i L'indice à tester
     * @return Le mot de l'indice testé
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
     * @return
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
     * @return
     */
    @Override
    public String plusLongPrefixeDe(String mot) {
        boolean trouve = this.contient(mot);
        while (mot.length() > 0 && !trouve) {
            mot = mot.substring(0, mot.length()-1);
            trouve = this.contient(mot);
        }
        return mot;
    }

    /**
     * Enregistre le dictionnaire dans le chemin demandé
     * @param chemin le chemin voulu
     */
    public void sauver(String chemin) throws IOException {
        // Chargement du fichier
        File fichier = new File(chemin);
        if (fichier.isDirectory()) throw new IOException("Le chemin \"" + chemin + "\" est un dossier.");

        // Chargement du tableau pour garder l'index des mots
        String[] mots = new String[this.nbMots()];
        int i = 0;
        while (i < this.nbMots()) {
            CelluleDictio cc = this.T[i];
            while (cc != null) {
                mots[cc.ind] = cc.elt;
                cc = cc.suiv;
            }
            i++;
        }

        // Initialisation du Buffer
        BufferedWriter buffer = new BufferedWriter(new FileWriter(chemin, false));

        // Écriture du du contenu du Dictionnaire
        String ligne = "";
        for (int j = 0; j < this.nbMots(); j++) {
            ligne += mots[j] + ",";
        }
        if (ligne != "") ligne = ligne.substring(0, ligne.length()-1);
        buffer.write(ligne);

        // Enregistrement et fermeture du Buffer
        buffer.flush();
        buffer.close();
    }

    /**
     * Charger le dictionnaire stocké dans le chemin demandé
     * @param chemin le chemin voulu
     */
    public void charger(String chemin) throws IOException {
        // Initialisation du fichier et du Buffer
        File fichier = new File(chemin);
        if (!fichier.exists() || !fichier.isFile()) throw new FileNotFoundException("Aucun fichier du nom de " + chemin + " n'a été trouvé.");
        BufferedReader buffer = new BufferedReader(new FileReader(fichier));

        // Vide du Dictionnaire
        this.vider();

        // Remplissage du Dictionnaire
        String ligne = buffer.readLine();
        String[] mots = ligne.split(",");
        for (int m = 0; m < mots.length; m++) {
            this.ajouterMot(mots[m]);
        }
        
        // Fermeture du Buffer
        buffer.close();
    }

}
