package inf353;

public class LecteurDocumentNaif implements AccesSequentielModele1<String> {
    
    /**
     * Initialisation du parcours.
     */
    public int nbSep = 17; // nombre de separateurs
    public char[] separateur; // liste des separateurs
    public int i = 0; // indice du caractere dans le texte
    public String mot;
    public BufferedReader br

    public LecteurDocumentNaif(File file)
    {
        br = new BufferedReader(new FileReader(file));
        separateur = new char[nbSep];
        separateur[0] = ',';
        separateur[1] = '?';
        separateur[2] = '.';
        separateur[4] = ';';
        separateur[5] = ':';
        separateur[6] = '/';
        separateur[7] = '!';
        separateur[8] = ' ';
        separateur[9] = '\n'; // retour chariot
        separateur[10] = '(';
        separateur[11] = ')';
        separateur[12] = '\"';
        separateur[13] = '-';
        separateur[14] = '\'';// apostrophe
        separateur[15] = '[';
        separateur[16] = ']';

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
        while (j < nbSep && separateur[j] != c)
        {
            j++;
        }
        return j < nbSep;
    }
}
