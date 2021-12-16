package inf353;

import java.io.IOException;
import java.io.FileNotFoundException;

public class Synonymes {

    CelluleSynonyme[] T;
    int ind;

    public Synonymes(String recherche) throws IOException {
        // Initialisation du fichier et du Buffer
        File fichier = new File("src/main/resources/inf353/indexation/Synonymes.txt");
        if (!fichier.exists() || !fichier.isFile())
            throw new FileNotFoundException("Aucun dictionnaire de synonymes n'a été trouvé.");
        BufferedReader buffer = new BufferedReader(new FileReader(fichier));
        
        // Remplissage du Dictionnaire de synonymes
        String ligne = buffer.readLine();
        String[][] mots = new String[61646][2];
        int i = 0;
        int j = 0;
        ind = 0;
        T = new CelluleSynonyme [l]
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




        //création du tableau des mots de la requette
        String [] requete = new String [100];
        int l = 0;
        int car = 0;
        String mot = "";
        while (car < recherche.length()){
            if (recherche.charAt(car) == ' '){
                requete [l] = mot;
                mot = "";
                l++;
            }
            else{
                mot = mot + recherche.charAt(car);
            }
            car++;
        }





        //comparaison avec la requette
        i = 0;
        j = 0;
        while (i < requete.length){
            while(j < 61646){
            if (requete[i].equals(mots[j][0])){
                T[ind] = new CelluleSynonyme(mots[0][j]);
                j++;

            }
            else if (requete[i].equals(mots[j][1])){

                T[ind] = new CelluleSynonyme(mots[j][0]);

                j++;
            }
            else  {
                j++;
            }
            }
            pds++;
            String ponderation = ponderationSynonimes(pds);
            T[ind] = new CelluleSynonyme(requete[i], T[ind]);
            ind++;

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