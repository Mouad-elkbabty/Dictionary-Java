package inf353;

import java.io.File;
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
        this.N = 2000;
        T = new CelluleMatrice[N];
    }

    public MatriceHash(int n)
    {
        this.N = n;
        T = new CelluleMatrice[N];
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
        int i = 0;
        CelluleMatrice cc;
        while(i < N && T[i] != null )
        {
            cc = T[i];
            while(cc !=null)
            {
                writer.write(cc.elt+","+cc.ind+" ");
                cc = cc.suiv;
            }
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
}
