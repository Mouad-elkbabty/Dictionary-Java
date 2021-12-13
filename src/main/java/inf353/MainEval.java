package inf353;

import java.io.IOException;

public class MainEval {


    public static void main(String[] args) throws IOException{
        
        String chemin = "./home/combegae/ubuntu/requete/inf353-tests/C091";
        
        
        
        String[] requete = lecture("./home/combegae/ubuntu/requete/inf353-tests/C091");
        if(requete.length <= 0)
        {
            System.out.println("La requete est vide!!");
            System.exit(0);
        }
        Recherche recherche = new Recherche(requete,chemin);
        recherche.presentation(2500);
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