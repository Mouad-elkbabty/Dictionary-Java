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
        CelluleMatrice cc = T[ndoc];
        while (cc != null && cc.ind == nterm) {
            cc = cc.suiv;
        }
        if (cc == null) {
            return 0;
        }
        return cc.elt;
    }

    @Override
    public void incremente(int ndoc, int nterm) {
        CelluleMatrice cc = T[ndoc];
        while (cc != null && cc.ind != nterm) {
            cc = cc.suiv;
        }
        if (cc != null) {
            cc.elt++;
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
        }
        else
        {
            T[ndoc] = (cc = new CelluleMatrice(val,nterm, cc));
        }
    }

    /**
     * Sauvegarde la matrice dans un fichier
     * Format (Document1(T[1]))|elt,ind elt,ind etc...
     * (Document2(T[2]))|elt,ind elt,ind etc...
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
        BufferedWriter buffer = new BufferedWriter(new FileWriter(chemin, true));

        // Écriture du contenu de la MatriceHash
        int i = 0;
        
        CelluleMatrice cc = T[i];
        System.out.println()
        
        while (i < N && T[i] != null ) {
            while (cc != null) {
                buffer.write(cc.elt + "," + cc.ind + " ");
                cc = cc.suiv;
            }
            buffer.newLine();
            i++;
            cc = T[i];
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
            CelluleMatrice matrice = null;
            int j = 0;
            String nombre = "";
            while (j < ligne.length()) // Lecture de la ligne
            {
                int occurence = 0;
                int indice = 0;

                nombre = "";
                while (ligne.charAt(j) != ',')// lecture du nombre d'occurence
                {
                    nombre += ligne.charAt(j);
                    j++;
                }
                occurence = Integer.parseInt(nombre);

                nombre = "";
                while (ligne.charAt(j) != ' ') // Lecture de l'indice
                {
                    nombre += ligne.charAt(j);
                    j++;
                }
                indice = Integer.parseInt(nombre);

                matrice = new CelluleMatrice(occurence, indice, matrice); // Creation de la sequence chainee
                j++;
            }
            T[i] = matrice;// remplissage du tableau
            i++;
            ligne = buffer.readLine();

        }
        buffer.close();
    }
}
