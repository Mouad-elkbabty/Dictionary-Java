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
        // String recherches = synonimes(args);
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
            File dossier = new File("./src/main/resources/inf353/requetes/");
            //int l = dossier.listFiles().length;
            recherche.requete("requete", res, 500);
        }
    }




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
            String res1 = "";
            while(i<k){
                res1 = res1 + " " + res[i];
                System.out.println(res[i]);
                i++;
            }
            return res1;

    }

}
