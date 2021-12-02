package inf353;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MatriceHash implements MatriceIndex{
    public int N;
    public CelluleMatrice[] T;
    /**
     * Crée une MatriceHash vide
     */
    public MatriceHash() 
    {
        this.N = 100000;
        T = new CelluleMatrice[N];
    }

    public MatriceHash(int n)
    {
        this.N = n;
        T = new CelluleMatrice[N];
    }

    public MatriceHash(String chemin)
    {

    }

    /**
     * Sauvegarde la matrice dans un fichier
     * Format  (Document1(T[1]))|elt,ind elt,ind etc...
     *         (Document2(T[2]))|elt,ind elt,ind etc...
     * @param chemin Le chemin 
     * @throws IOException
     */
    @Override
    public void sauver(String chemin) throws IOException {
        File fichier = new File(chemin);
        FileWriter writer = new FileWriter(fichier);
        BufferedWriter bw = new BufferedWriter(writer);
        int i = 0;
        CelluleMatrice cc;
        while(i < N && T[i] != null )
        {
            cc = T[i];
            while(cc !=null)
            {
                bw.write(cc.elt+","+cc.ind+" ");
                cc = cc.suiv;
            }
            bw.newLine();
            i++;
        }
        writer.close();
    }

    @Override
    public int val(int ndoc, int nterm) {
        CelluleMatrice cc = T[ndoc];
        while(cc != null && cc.ind == nterm)
        {
            cc = cc.suiv;
        }
        if(cc == null)
        {
            return 0;
        }
        return cc.elt;
    }

    @Override
    public void incremente(int ndoc, int nterm) {
        CelluleMatrice cc = T[ndoc];
        while (cc != null && cc.ind != nterm)
        {
            cc = cc.suiv;
        }
        if(cc != null)
        {
            cc.elt++;
        }
        
    }

    @Override
    public void affecte(int ndoc, int nterm, int val) {
        CelluleMatrice cc = T[ndoc];
        while (cc != null && cc.ind != nterm)
        {
            cc = cc.suiv;
        }
        if(cc != null)
        {
            cc.elt = val;
        }
    }

    //Version ou on ne prend pas en compte la taille de la matrice(on en prend une de 100 000 ligne)
    @Override
    public void charger(String chemin) throws FileNotFoundException, IOException
    {
        File fichier = new File(chemin);
        BufferedReader buffer = new BufferedReader(new FileReader(fichier));
        int i = 0;
        while(i < this.N  )
        {
            CelluleMatrice matrice = null;
            String ligne = buffer.readLine();
            int j = 0;
            String nombre = "";
            while(j < ligne.length()) //Lecture de la ligne
            {
                int occurence = 0;
                int indice = 0;
                
                nombre = "";
                while(ligne.charAt(j) != ',')//lecture du nombre d'occurence
                {
                    nombre += ligne.charAt(j);
                    j++;
                }            
                occurence = Integer.parseInt(nombre);
                
                nombre = "";
                while(ligne.charAt(j) != ' ') // Lecture de l'indice
                {
                    nombre += ligne.charAt(j);
                    j++;
                }
                indice = Integer.parseInt(nombre);

                matrice = new CelluleMatrice(occurence,indice,matrice); // Creation de la sequence chainee
                j++;
            }
            T[i] = matrice;//remplissage du tableau
            i++;
            
        }
        buffer.close();
    }
}
