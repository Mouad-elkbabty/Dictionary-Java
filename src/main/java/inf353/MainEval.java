package inf353;

import java.io.IOException;

public class MainEval {


    public static void main(String[] args) throws IOException{
        

        int doc = 91;
        String num = "";
        String chemin = "/home/vialf/ubuntu/requete/inf353-tests/C";
        while (doc < 140)
        {
            if(doc < 100)
            {
                num = "0"+doc;
            }
            else
            {
                num = ""+doc;
            }
            calcul((chemin + num) );
            doc ++;
            
        }
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

    public static void calcul(String chemin) throws IOException
    {
        String[] requete = lecture(chemin);
        if(requete.length <= 0)
        {
            System.out.println("La requete est vide!!");
            System.exit(0);
        }
        Recherche recherche = new Recherche(requete,"./src/main/resources/inf353/indexation/");
        recherche.presentationFichiers(20);
    }
}