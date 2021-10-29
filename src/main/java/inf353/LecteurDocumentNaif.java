package inf353;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class LecteurDocumentNaif implements AccesSequentielModele1<String> {
    
<<<<<<< HEAD
    public char[] separateurs = {',', '?', '.', ';', '/', '!', ' ', '\n', '(', ')', '"', '-', '\'', '[', ']'};
    public int i;
    public String texte;
    public BufferedReader br;

    public LecteurDocumentNaif(String nomFichier) throws FileNotFoundException {
        this.texte = "";
        br = new BufferedReader(new FileReader(nomFichier));
        String ligne = br.readLine();
    }

    public void demarrer() {
        this.i = 0;
        if (estSeparateur(texte.charAt(0))) this.avancer();
=======
    /**
     * Initialisation du parcours.
     */
    public char[] separateurs = {',','?','.',';',':','/',' ','\n','(',')','\"','-','\'','[',']','_'}; // liste des separateurs
    public int i; // indice du caractere dans le texte
    public String mot;
    public BufferedReader br;

    public LecteurDocumentNaif(String file) throws FileNotFoundException, java.io.IOException {
        br = new BufferedReader(new FileReader(file));
        String strCurrentLine = "";
        i = 0;
        while((strCurrentLine = br.readLine()) != null)
        {
            mot += strCurrentLine;
            System.out.println(strCurrentLine);
        }

    }

    public void demarrer() {
        i= 0;
>>>>>>> refs/remotes/origin/main
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
<<<<<<< HEAD
        while (j < this.separateurs.length && this.separateurs[j] != c) {
            j++;
        }
        return j < this.separateurs.length;
=======
        while (j < separateurs.length && separateurs[j] != c)
        {
            j++;
        }
        return j < separateurs.length;
>>>>>>> refs/remotes/origin/main
    }
}
