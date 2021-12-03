package inf353;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MatriceHash implements MatriceIndex {

    public int N;
    public CelluleMatrice[] T;

    /**
     * Crée une MatriceHash vide
     */
    public MatriceHash() {
        this(100000);
    }

    /**
     * Crée une MatriceHash vide de taille n
     * 
     * @param n la taille de la MatriceHash
     */
    public MatriceHash(int n) {
        this.N = n;
        T = new CelluleMatrice[N];
    }

    public MatriceHash(String chemin) throws IOException {
        this();
        this.charger(chemin);
    }

    @Override
    public int val(int ndoc, int nterm) {
        int v = 0;
        CelluleMatrice cc = T[ndoc];
        while (cc != null && cc.ind != nterm) {
            cc = cc.suiv;
        }
        if (cc != null) {
            v = cc.elt;
        }
        return v;
    }

    @Override
    public void incremente(int ndoc, int nterm) {
        CelluleMatrice cc = T[ndoc];
        while (cc != null && cc.ind != nterm) {
            cc = cc.suiv;
        }
        if (cc != null) {
            cc.elt += 1;
        } else {
            T[ndoc] = new CelluleMatrice(1, nterm, T[ndoc]);
        }

    }

    @Override
    public void affecte(int ndoc, int nterm, int val) {
        CelluleMatrice cc = T[ndoc];
        while (cc != null && cc.ind != nterm) {
            cc = cc.suiv;
        }
        if (cc != null) {
            cc.elt = val;
        } else {
            T[ndoc] = new CelluleMatrice(val, nterm, T[ndoc]);
        }
    }

    /**
     * Sauvegarde la matrice dans un fichier formatté de la manière suivante :
     * indiceMot1:occurenceMot1Doc1,indiceMot2:occurenceMot2Doc1,...
     * indiceMot1:occurenceMot1Doc2,indiceMot3:occurenceMot3Doc2,...
     * ...
     * 
     * @param chemin Le chemin
     * @throws IOException
     */
    @Override
    public void sauver(String chemin) throws IOException {
        // Chargement du fichier
        File fichier = new File(chemin);
        if (fichier.isDirectory())
            throw new IOException("Le chemin \"" + chemin + "\" est un dossier.");

        // Initialisation du Buffer
        fichier.createNewFile();
        
        BufferedWriter buffer = new BufferedWriter(new FileWriter(chemin, false));

        // Écriture du contenu de la MatriceHash
        int i = 0;
        int j = 0;
        int m = 0;
        
        CelluleMatrice cc = T[i];
        String ligne = "";
        while (i < N && T[i] != null ) {
            while (cc != null) {
                ligne += cc.ind + ":" + cc.elt + ",";
                cc = cc.suiv;
                j ++;
            }
            if (ligne != "")
                ligne = ligne.substring(0, ligne.length() - 1);
            buffer.write(ligne);
            buffer.newLine();
            i++;
            cc = T[i];
            ligne = "";
            j ++;
            if (j > 1000){
                m ++;
                System.out.println("" + 1000 * m);
                j = 0;
            }

        }

        // Enregistrement et fermeture du Buffer
        buffer.flush();
        buffer.close();
    }

    // Version ou on ne prend pas en compte la taille de la matrice(on en prend une
    // de 100 000 ligne)
    @Override
    public void charger(String chemin) throws IOException {
        File fichier = new File(chemin);
        if (!fichier.exists() || !fichier.isFile())
            throw new FileNotFoundException("Aucun fichier du nom de " + chemin + " n'a été trouvé.");
        BufferedReader buffer = new BufferedReader(new FileReader(fichier));
        int i = 0;
        String ligne = buffer.readLine();
        while (i < this.N && ligne != null ) {
            CelluleMatrice tete = null;
            int p = 0;
            while (p < ligne.length()) {
                String indice = "";
                while (ligne.charAt(p) != ':') {
                    indice += ligne.charAt(p);
                    p++;
                }
                p++;
                String occurence = "";
                while (p != ligne.length() && ligne.charAt(p) != ',') {
                    occurence += ligne.charAt(p);
                    p++;
                }
                p++;
                tete = new CelluleMatrice(Integer.parseInt(occurence), Integer.parseInt(indice), tete);
            }
            T[i] = tete;
            ligne = buffer.readLine();
            i++;
        }
        buffer.close();
    }
}
