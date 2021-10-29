package inf353;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class LecteurDocumentNaif implements AccesSequentielModele1<String> {
    
    /**
     * Initialisation du parcours.
     */
    public String separateurs = ",?.;:/ \n()\"-\'[]_@"; // liste des separateurs
    public int curseur; // indice du mot dans la ligne
    public String mot;
    public BufferedReader br;
    String[] ligneCourante = null; 

    public LecteurDocumentNaif(String file) throws FileNotFoundException, java.io.IOException {
        br = new BufferedReader(new FileReader(file));
        this.curseur = 0;
        this.mot = "";

    }

    public void demarrer() throws java.io.IOException
    {
        this.curseur= 0;
        ligneCourante = br.readLine().split(",|?|.|;|:|/| |\n|(|)|\"|-|\'|[|]|_|@");
    }

    /**
     * Passage à l'élément suivant
     */
    public void avancer() throws java.io.IOException {
        if(!finDeSequence())
        {
            if(this.curseur < ligneCourante.length-1)
            {
                this.curseur++;
            }
            else
            {
                ligneCourante = br.readLine().split(",|?|.|;|:|/| |\n|(|)|\"|-|\'|[|]|_|@");
                this.curseur = 0;
            }
        }
    }

    /**
     * vrai ssi la séquence est épuisée
     * @return
     */
    public boolean finDeSequence()  {
        return ligneCourante == null;
    }

    /**
     * renvoie l'élément courant
     * @return
     */
    public String elementCourant() {
        String res = "";
        if(!finDeSequence())
        {
            res = ligneCourante[this.curseur];
            
        }
        return res;
    }
}
