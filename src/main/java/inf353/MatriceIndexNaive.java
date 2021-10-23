package inf353;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;

public class MatriceIndexNaive implements MatriceIndex {

    public int[][] matrice;

    public MatriceIndexNaive(int docs, int termes) {
        this.matrice = new int[docs][termes];
    }

    public MatriceIndexNaive(String nomFichier) throws IOException {
        File fichier = new File(nomFichier);
        if (!fichier.exists() || !fichier.isFile()) {
            throw new FileNotFoundException("Aucun fichier du nom de " + nomFichier + " n'a été trouvé.");
        }
        BufferedReader buffer = new BufferedReader(new FileReader(fichier));
        String ligne = buffer.readLine();
        int docs = 0;
        int termes = 0;
        while (ligne != null) {
            docs = docs+1;
            termes = Math.max(termes, ligne.split(",").length);
            ligne = buffer.readLine();
        }
        buffer.close();
        this.matrice = new int[docs][termes];
        buffer = new BufferedReader(new FileReader(fichier));
        ligne = buffer.readLine();
        for (int i = 0; i < docs; i++) {
            String[] tableau = ligne.split(",");
            for (int j = 0; j < termes; j++) {
                if (j < tableau.length) {
                    this.affecte(i, j, Integer.valueOf(tableau[j]));
                } else {
                    this.affecte(i, j, 0);
                }
            }
            ligne = buffer.readLine();
        }
        buffer.close();
    }
    
    /**
     * Sauvegarde de la matrice d'occurence dans le fichier nomDeFichier. Le format est libre,
     * mais doit privilégier la vitesse de chargement et la compacité (taille du fichier).
     *
     * @param nomDeFichier
     */
    public void sauver(String nomDeFichier) throws FileNotFoundException, IOException {
        File fichier = new File(nomDeFichier);
        if (fichier.exists() && fichier.isFile()) {
            fichier.delete();
        }
        fichier.createNewFile();
        Writer writer = new FileWriter(fichier, StandardCharsets.UTF_8);
        BufferedWriter buffer = new BufferedWriter(writer);
        String ligne = "";
        for (int mots = 0; mots < this.matrice[0].length; mots++) {
            for (int documents = 0; documents < this.matrice.length; documents++) {
                ligne += this.matrice[mots][documents] + ",";
            }
            ligne = ligne.substring(0, ligne.length()-1);
            buffer.write(ligne);
            buffer.newLine();
            ligne = "";
        }
        buffer.flush();
        buffer.close();
    }

    /**
     * retourne le nombre d'occurences du terme numéro nterm dans le document numéro ndoc.
     * @param  ndoc  le numéro du document
     * @param  nterm le numéro du terme
     * @return le nombre d'occurences du terme dans le document
     */
    public int val(int ndoc, int nterm) {
        return this.matrice[ndoc][nterm];
    }

    /**
     * Ajoute 1 au nombre d'occurences du terme numéro nterm dans le document numéro ndoc.
     * @param  ndoc  le numéro du document
     * @param  nterm le numéro du terme
     */
    public void incremente(int ndoc, int nterm) {
		this.matrice[ndoc][nterm] = this.matrice[ndoc][nterm] + 1;
    }

    /**
     * affecte à val le numéro d'occurences du terme numéro nterm dans le document numéro ndoc.
     * @param  ndoc  le numéro du document
     * @param  nterm le numéro du terme
     * @param  val   la nouvelle valeur du nombre d'occurence
     */
    public void affecte(int ndoc, int nterm, int val) {
		this.matrice[ndoc][nterm] = val;
    }

    
}
