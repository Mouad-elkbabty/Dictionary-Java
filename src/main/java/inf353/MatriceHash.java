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
        while (cc != null && cc.ind > nterm) {
            cc = cc.suiv;
        }
        if (cc != null && cc.ind == nterm) {
            v = cc.elt;
        }
        return v;
    }

    @Override
    public void incremente(int ndoc, int nterm) {
        // initialisation avec fictif
        CelluleMatrice cc = T[ndoc];
        CelluleMatrice cp = new CelluleMatrice(cc);
        T[ndoc] = cp;
        // tant qu'on est pas a la fin et que l'indice est plus grand que le terme donne (car ordre decroissant)
        while (cc != null && cc.ind > nterm) {
            cp = cc;
            cc = cc.suiv;
        }
        // si on est pas a la fin et qu'on a trouve le terme, alors on incremente
        // sinon, on ajoute une nouvelle cellule
        if (cc != null && cc.ind == nterm) {
            
            cc.elt += 1;
        } else {
            cp.suiv = new CelluleMatrice(1, nterm, cc);
        }
        // suppression du fictif
        T[ndoc] = T[ndoc].suiv;
    }

    @Override
    public void affecte(int ndoc, int nterm, int val) {
        // initialisation
        CelluleMatrice cc = T[ndoc];
        CelluleMatrice cp = new CelluleMatrice(cc);
        T[ndoc] = cp;
        // tant qu'on est pas a la fin et que l'indice est plus grand que le terme donne (car ordre decroissant)
        while (cc != null && cc.ind > nterm) {
            cp = cc;
            cc = cc.suiv;
        }
        // si on est pas a la fin et qu'on a trouve le terme, alors on affecte
        // sinon, on ajoute une nouvelle cellule
        if (cc != null && cc.ind == nterm) {
            cc.elt = val;
        } else {
            cp.suiv = new CelluleMatrice(val, nterm, cc);
        }
        // suppression du fictif
        T[ndoc] = T[ndoc].suiv;
    }

    /**
     * Sauvegarde la matrice dans un fichier formatté de la manière suivante :
     * indiceMotX:occurenceMotXDoc1,indiceMotY:occurenceMotXDoc1,...
     * indiceMotZ:occurenceMotZDoc2,indiceMotY:occurenceMotYDoc2,...
     * @param chemin Le chemin
     * @throws IOException
     */
    @Override
    public void sauver(String chemin) throws IOException {
        // Chargement du fichier
        File fichier = new File(chemin);
        if (fichier.isDirectory())
            throw new IOException("Le chemin \"" + chemin + "\" est un dossier.");
        File dossier = new File(fichier.getParent());
        if (dossier != null && !dossier.isDirectory()) dossier.mkdir();
        fichier.createNewFile();

        // Initialisation du Buffer
        BufferedWriter buffer = new BufferedWriter(new FileWriter(chemin, false));

        // Écriture du contenu de la MatriceHash
        int i = 0;
        CelluleMatrice cc;
        String ligne = "";
        while (i < N) {
            cc = T[i];
            if (cc != null) {
                while (cc != null) {
                    ligne += cc.ind + ":" + cc.elt + ",";
                    cc = cc.suiv;
                }
                buffer.write(ligne);
                buffer.newLine();
                ligne = "";
            }
            i++;
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
            CelluleMatrice cc = null;
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
                CelluleMatrice cn = new CelluleMatrice(Integer.parseInt(occurence), Integer.parseInt(indice), null);
                if (cc == null) {
                    T[i] = cc = cn;
                } else {
                    cc.suiv = cn;
                    cc = cc.suiv;
                }
            }
            ligne = buffer.readLine();
            i++;
        }
        buffer.close();
    }
}
