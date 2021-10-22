package inf353;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.File;

public class MatriceIndexNaive implements MatriceIndex {

    int[][] matrice;

    public MatriceIndexNaive(int documents, int mots) {
        this.matrice = new int[documents][mots];
    }

    public MatriceIndexNaive(File fichier) {

    }
    
    /**
     * Sauvegarde de la matrice d'occurence dans le fichier nomDeFichier. Le format est libre,
     * mais doit privilégier la vitesse de chargement et la compacité (taille du fichier).
     *
     * @param nomDeFichier
     */
    public void sauver(String nomDeFichier) throws FileNotFoundException, IOException {
        String contenu = "";
        for (int mots = 0; mots < this.matrice[0].length; mots++) {
            for (int documents = 0; documents < this.matrice.length; documents++) {
                contenu += this.matrice[mots][documents] + ",";
            }
            contenu += "\n";
        }
        RandomAccessFile fichier = new RandomAccessFile(nomDeFichier, "rwd");
        fichier.writeChars(contenu);
        fichier.close();
    }

    /**
     * retourne le nombre d'occurences du terme numéro nterm dans le document numéro ndoc.
     * @param  ndoc  le numéro du document
     * @param  nterm le numéro du terme
     * @return       le nombre d'occurences du terme dans le document
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
     * @param val    la nouvelle valeur du nombre d'occurence
     */
    public void affecte(int ndoc, int nterm, int val) {
		this.matrice[ndoc][nterm] = val;
    }

    
}
