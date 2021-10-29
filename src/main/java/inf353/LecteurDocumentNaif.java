package inf353;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;

public class LecteurDocumentNaif implements AccesSequentielModele1<String> {
    
    public char[] separateurs = {',', '?', '.', ';', '/', '!', ' ', '\n', '(', ')', '"', '-', '\'', '[', ']'};
    public int i;
    public String texte;
    public BufferedReader br;

    public LecteurDocumentNaif(String nomFichier) throws IOException {
        this.texte = "";
        br = new BufferedReader(new FileReader(nomFichier));
        String ligne = br.readLine();
        
    }

    public void demarrer() {
        this.i = 0;
        if (estSeparateur(texte.charAt(0))) this.avancer();
    }

    /**
     * Passage à l'élément suivant
     */
    public void avancer() {
        while(!finDeSequence() && !estSeparateur(texte.charAt(i))) {
            this.i++;
        }
        while (!finDeSequence() && estSeparateur(texte.charAt(i))) {
            this.i++;
        }
    }

    /**
     * vrai ssi la séquence est épuisée
     * @return
     */
    public boolean finDeSequence() {
        return i == texte.length();
    }

    /**
     * renvoie l'élément courant
     * @return
     */
    public String elementCourant() {
        String res = "";
        int j = i;
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
