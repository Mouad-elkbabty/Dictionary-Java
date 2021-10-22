package inf353;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class LecteurDocumentNaif implements AccesSequentielModele1<String> {
    
    /**
     * Initialisation du parcours.
     */
    public int nbSep = 17; // nombre de separateurs
    public char[] separateurs; // liste des separateurs
    public int i = 0; // indice du caractere dans le texte
    public String mot;
    public BufferedReader br;

    public LecteurDocumentNaif(File file) throws FileNotFoundException {
        br = new BufferedReader(new FileReader(file));
        separateurs = new char[nbSep];
        separateurs[0] = ',';
        separateurs[1] = '?';
        separateurs[2] = '.';
        separateurs[4] = ';';
        separateurs[5] = ':';
        separateurs[6] = '/';
        separateurs[7] = '!';
        separateurs[8] = ' ';
        separateurs[9] = '\n'; // retour chariot
        separateurs[10] = '(';
        separateurs[11] = ')';
        separateurs[12] = '\"';
        separateurs[13] = '-';
        separateurs[14] = '\'';// apostrophe
        separateurs[15] = '[';
        separateurs[16] = ']';
    }

    public void demarrer() {

    }

    /**
     * Passage à l'élément suivant
     */
    public void avancer() {
        if(!finDeSequence())
        {
            while(!finDeSequence() && !estSeparateur(mot.charAt(i)))
            {
                i++;
            }
            if(!finDeSequence())
            {
                i++;
            }
            
        }
    }

    /**
     * vrai ssi la séquence est épuisée
     * @return
     */
    public boolean finDeSequence() {
        return true;
    }

    /**
     * renvoie l'élément courant
     * @return
     */
    public String elementCourant() {
        String res = "";
        int j = i;
        if(!finDeSequence())
        {
            while(!finDeSequence() && !estSeparateur(mot.charAt(j)))
            {
                res = res + mot.charAt(j);
                j++;
            }
            
        }
        return res;
    }


    public boolean estSeparateur(char c) //Renvoie vrai si le caractères est separateur
    {
        int j = 0;
        while (j < nbSep && separateurs[j] != c)
        {
            j++;
        }
        return j < nbSep;
    }
}
