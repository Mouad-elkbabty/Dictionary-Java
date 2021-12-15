package inf353;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class MainRecherche {

    public static void main (String[] args) throws IOException {
      /*  Stoplist stoplist = new Stoplist();
        String [] tampon = new String [args.length];
        

        int i = 0;
        int j = 0;
        while (i < args.length){
            if (!stoplist.estPresent(args[i])){
                tampon[j] = args [i];
                j++;
            }
            i++;
        }
        recherches = new String [j];
        j = 0;
        while(j < recherches.length){
            recherches[j] = tampon[j];
            j++;
        }*/
        String recherches = synonymes(args);
        // recherche.presentation(2500);
        if (args.length == 0) throw new Error("Veuillez entrer une requete valide");
        Recherche recherche = new Recherche("./src/main/resources/inf353/indexation/");
        try {
            int n = Integer.parseInt(args[0]);
            if (n < 91 || n > 140) throw new NumberFormatException();
            recherche.requete(n, 500);
        } catch (NumberFormatException e) {
            String res = "";
            int i = 0;
            while (i != args.length) {
                res += args[i] + " ";
                i++;
            }
<<<<<<< HEAD
            File dossier = new File("./src/main/resources/inf353/requetes");
            //int l = dossier.listFiles().length;
=======
            File dossier = new File("./src/main/resources/inf353/requetes/");
<<<<<<< HEAD
            if (dossier.listFiles() != null){
                int l = dossier.listFiles().length;
            }
            else {
                int l = 0;
            }
=======
            int l = dossier.listFiles().length;
>>>>>>> b3bebe05e457d3b63af63b9c2761592e33fd3026
>>>>>>> 5209b5af8a76df0b9c6b17150b4b8bf48b8e76f4
            recherche.requete("requete", res, 500);
        }
    }

    /**
     * renvoit la requette ainsi que les synonimes de chaques mots sous la forme d'une chaine de caractères, avec la pondération.
     * exemple: 0.2500:mot1 0.2500:synonime1 0.2500:synonime2 0.2500:synonime3
     * 
     * @param requete la requette marquée en argument de la commande
     * @throws IOException
     */


    public static String synonymes (String[] requete)throws IOException{

            // Initialisation du fichier et du Buffer
            File fichier = new File("src/main/resources/inf353/indexation/synonimes");
            if (!fichier.exists() || !fichier.isFile())
                throw new FileNotFoundException("Aucun dictionnaire de synonimes n'a été trouvé.");
            BufferedReader buffer = new BufferedReader(new FileReader(fichier));
            
            
            
            // Remplissage du Dictionnaire de synonymes
            String ligne = buffer.readLine();
            String[][] mots = new String[61646][2];
            int i = 0;
            int j = 0;
            String motCourant = "";
            while (ligne != null) {
                while (i < ligne.length()){
                if(ligne.charAt(i) != '\t'){
                    motCourant = motCourant + ligne.charAt(i);
                }
                else if(i < ligne.length() - 1){
                mots[j][0] = motCourant; 
                motCourant = "";
                }
                if(i == ligne.length()-1){
                    mots[j][1] = motCourant;
                    j++;
                    motCourant = "";
                }
                i++;

            }
            ligne = buffer.readLine();
            i = 0;
            }


            //comparaison avec la requette
            i = 0;
            j = 0;
            int k = 0;
            int pds = 0;
            String [] res = new String[100];
            while (i < requete.length){
                while(j < 61646){
                if (requete[i].equals(mots[j][0])){
                    pds++;
                    res[k] = mots[j][1];
                    k++;
                    j++;

                }
                else if (requete[i].equals(mots[j][1])){
                    pds++;
                    res[k] = mots[j][0];
                    k++;
                    j++;
                }
                else  {
                    j++;
                }
                }
                pds++;
                String ponderation = ponderationSynonimes(pds);
                res[k] = "1.0000" + requete[i];
                int compte = pds;
                k++;
                while (compte > 1){
                    res[k-compte] = ponderation + res[k-compte];
                    compte--;
                }
                i++;
                pds = 0;
                j = 0;
            }

            //écriture du résultat
            i = 0;
            String res1 = "";
            while(i<k){
                res1 = res1 + " " + res[i];
                System.out.println(res[i]);
                i++;
            }
            return res1;

    }

    /**
     * renvoit la fraction correspondant à l'entier placé en entré, sous la forme d'une chaine de caractères sur 5 décimales (4 après la virgule) suivit de :
     * 
     * 
     * @param pds le nombre de synonimes du mot
     * @throws IOException
     */

    public static String ponderationSynonimes (int pds){
        String res = "";
        if (pds == 1){
            res = "1.0000";
        }
        else{
            int i = 0;
            double frac = 1/(double)pds;
            String res1 = "" + frac;
            while (i != 6 ){
                if (i < res1.length()){
                    res = res + res1.charAt(i);
                }
                else{
                    res = res + "0";
                }
                i++;
            }
            
        }
        return res + ":";
    }

}
