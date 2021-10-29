package inf353;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class LecteurDocumentNaif implements AccesSequentielModele1<String> {
    
    /**
     * Initialisation du parcours.
     */
    public char[] separateurs = {',','?','.',';',':','/',' ','\n','(',')','\"','-','\'','[',']','_'}; // liste des separateurs
    public int i; // indice du caractere dans le texte
    public String mot;
    public BufferedReader br;

    public LecteurDocumentNaif(String file) throws FileNotFoundException, java.io.IOException {
        br = new BufferedReader(new FileReader(file));
        String strCurrentLine;
        i = 0;
        while((strCurrentLine = br.readLine()) != null)
        {
            mot += strCurrentLine;
            System.out.println(strCurrentLine);
        }

    }

    public void demarrer() {
        i= 0;
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
        return i == mot.length();
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
