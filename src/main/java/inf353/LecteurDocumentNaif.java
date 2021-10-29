package inf353;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;

public class LecteurDocumentNaif implements AccesSequentielModele1<String> {
    
    public char[] separateurs = {',', '?', '.', ';', '/', '!', ' ', '\n', '(', ')', '"', '-', '\'', '[', ']'};
    public int curseur;
    public String texte;

    /**
     * Créer le lecteur en donnant le fichier
     * @param fichier le fichier
     * @throws IOException
     */
    public LecteurDocumentNaif(File fichier) throws IOException {
        this.texte = "";
        BufferedReader buffer = new BufferedReader(new FileReader(fichier));
        String ligne = buffer.readLine();
        while (ligne != null) {
            this.texte += ligne + "\n";
            ligne = buffer.readLine();
        }
        buffer.close();
    }

    /**
     * Crée le lecteur en donnant le nom du fichier
     * @param nomFichier le nom du fichier
     * @throws IOException
     */
    public LecteurDocumentNaif(String nomFichier) throws IOException {
        this(new File(nomFichier));
    }

    public void demarrer() {
        this.curseur = 0;
        if (estSeparateur(texte.charAt(0))) this.avancer();
    }

    /**
     * Passage à l'élément suivant
     */
    public void avancer() {
        while(!finDeSequence() && !estSeparateur(texte.charAt(curseur))) {
            this.curseur++;
        }
        while (!finDeSequence() && estSeparateur(texte.charAt(curseur))) {
            this.curseur++;
        }
    }

    /**
     * vrai ssi la séquence est épuisée
     * @return
     */
    public boolean finDeSequence() {
        return curseur == texte.length();
    }

    /**
     * renvoie l'élément courant
     * @return
     */
    public String elementCourant() {
        String res = "";
        int j = curseur;
        while(!finDeSequence() && !estSeparateur(texte.charAt(j))) {
            res = res + texte.charAt(j);
            j++;
        }
        return res;
    }

    /**
     * Renvoie vrai si le caractère est un séparateur
     * @param c le caractère à tester
     * @return
     */
    public boolean estSeparateur(char c) {
        int j = 0;
        while (j < this.separateurs.length && this.separateurs[j] != c) {
            j++;
        }
        return j < this.separateurs.length;
    }
}
