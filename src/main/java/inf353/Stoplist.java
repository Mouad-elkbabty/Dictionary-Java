package inf353;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class Stoplist {
 /*   public String [] list;
    public String [] tampon;



    
    public Stoplist() throws IOException{
        tampon = new String [1000];
        int i  = 0;
        int k = 0;
        int j = 0;

        File fichier = new File("src/main/resources/inf353/stoplist");
        if (!fichier.exists() || !fichier.isFile())
            throw new FileNotFoundException("Aucune stoplist n'a été trouvé.");
        BufferedReader buffer = new BufferedReader(new FileReader(fichier));

        String ligne = buffer.readLine();
        while(!ligne.equals("")){
            String motCourant = "";
            while (i <  ligne.length() && ligne.charAt(i) != ' ') {
                motCourant = motCourant + ligne.charAt(i);
                i++;
            }
            tampon[k] = motCourant;
            ligne = buffer.readLine();
            i = 0;
        }
        list = new String[k];
        while(j<k){
            list[j] = tampon[j];
            j++;
        }
        }
    

    public boolean estPresent(String mot){
        int i = 0;
        while(i < list.length && !list[i].equals(mot)){
            i++;
        }
        return i != list.length;
    }*/

}