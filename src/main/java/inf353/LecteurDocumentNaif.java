package inf353;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;

public class LecteurDocumentNaif implements AccesSequentielModele1<String> {
    
    public String texte;

    /**
     * Créer le lecteur en donnant le fichier
     * @param fichier le fichier
     * @throws IOException
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

    public LecteurDocumentNaif(File fichier) throws IOException {
        this.mot = "";
        this.curseur = 0;
        br = new BufferedReader(new FileReader(fichier));
    }


    /**
     * Crée le lecteur en donnant le nom du fichier
     * @param nomFichier le nom du fichier
     * @throws IOException
     */
    public LecteurDocumentNaif(String nomFichier) throws IOException {
        this(new File(nomFichier));
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
