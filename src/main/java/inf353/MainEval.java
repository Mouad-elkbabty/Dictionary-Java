package inf353;

import java.io.IOException;

public class MainEval {


    public static void main(String[] args) throws IOException{
        
        String chemin = "/home/vialf/ubuntu/requete/inf353-tests/C091";
        
        
        
        String[] requete = lecture(chemin);
        int i = 0;
        while(i < requete.length)
        {
            System.out.println(requete[i]);
            i++;
        }
        if(requete.length <= 0)
        {
            System.out.println("La requete est vide!!");
            System.exit(0);
        }
        Recherche recherche = new Recherche(requete,"./src/main/resources/inf353/");
        recherche.presentationFichier();
    }

    public static String[] lecture (String chemin) throws IOException{
        int i = 0;
        LecteurDocumentNaif lect = new LecteurDocumentNaif(chemin);
        lect.demarrer();
        while(!lect.finDeSequence())
        {
            lect.avancer();
            i++;
        }
        String[] res = new String[i];
        lect.demarrer();
        i = 0;
        while(!lect.finDeSequence())
        {
            res[i] = lect.elementCourant();
            lect.avancer();
            i++;
        }
        if(i == 0)
        {
            return null;
        }
        return res;
    }
}