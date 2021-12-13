package inf353;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOException;

public class MatriceHashTest {

    // impossible de tester tous les constructeurs sans charger
    @Test
    public void chargerTest() throws IOException {
        MatriceHash matrice = new MatriceHash();
        matrice.charger("./src/test/resources/inf353/test-matrice-charger/MatriceOccurrences.txt");
        int[][] valeurs = {
            { 2, 0, 4 },
            { 0, 3, 1 },
            { 1, 3, 3 }
        };
        for (int i = 0; i < valeurs.length; i++) {
            for (int j = 0; j < valeurs[0].length; j++) {
                assertEquals(valeurs[i][j], matrice.val(i, j));
            }
        }
    }

    @Test
    public void constructeursTest() throws IOException {
        MatriceHash matrice1 = new MatriceHash();
        assertEquals(matrice1.N, 100000);
        assertEquals(matrice1.T.length, 100000);
        MatriceHash matrice2 = new MatriceHash(10);
        assertEquals(matrice2.N, 10);
        assertEquals(matrice2.T.length, 10);
        MatriceHash matrice3 = new MatriceHash("./src/test/resources/inf353/test-matrice-charger/MatriceOccurrences.txt");
        assertEquals(matrice3.N, 100000);
        assertEquals(matrice3.T.length, 100000);
        int[][] valeurs = {
            { 2, 0, 4 },
            { 0, 3, 1 },
            { 1, 3, 3 }
        };
        for (int i = 0; i < valeurs.length; i++) {
            for (int j = 0; j < valeurs[0].length; j++) {
                assertEquals(valeurs[i][j], matrice3.val(i, j));
            }
        }
    }

    @Test
    public void valTest() {

    }

    @Test

    public void affecteTest() {
    } 

    @Test
    public void incrementeTest() {

    }

    @Test
    public void sauverTest() throws IOException {
        MatriceHash matrice1 = new MatriceHash(3);
        int[][] valeurs = {
            { 2, 0, 4 },
            { 0, 3, 1 },
            { 1, 3, 3 }
        };
        for (int i = 0; i < valeurs.length; i++) {
            for (int j = 0; j < valeurs[0].length; j++) {
                if (valeurs[i][j] > 0) matrice1.affecte(i, j, valeurs[i][j]);
            }
        }
        matrice1.sauver("./src/test/resources/inf353/test-matrice-sauver/MatriceOccurrences.txt");
        MatriceHash resultat1 = new MatriceHash("./src/test/resources/inf353/test-matrice-sauver/MatriceOccurrences.txt");
        for (int i = 0; i < valeurs.length; i++) {
            for (int j = 0; j < valeurs[0].length; j++) {
                assertEquals(valeurs[i][j], resultat1.val(i, j));
            }
        }

    }
    
}
