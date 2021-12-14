package inf353;

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
        String [] recherches = synonimes(args);

        Recherche recherche = new Recherche(recherches, "./src/main/resources/inf353/indexation/");
        recherche.presentation(2500);
    }




    public static String [] synonimes (String[] requete)throws IOException{

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
            String [] res = new String[100];
            while (i < requete.length){
                while(j < 61646){
                if (requete[i].equals(mots[j][0])){
                    res[k] = mots[j][1];
                    k++;
                    j++;
                }
                else if (requete[i].equals(mots[j][1])){
                    res[k] = mots[j][0];
                    k++;
                    j++;
                }
                else  {
                    j++;
                }
                }
                res[k] = requete[i];
                k++;
                i++;
                j = 0;
            }
            i = 0;
            String [] res1 = new String[k];
            while(i<k){
                res1[i] = res[i];
                System.out.println(res1[i]);
                i++;
            }
            return res1;

    }

}
