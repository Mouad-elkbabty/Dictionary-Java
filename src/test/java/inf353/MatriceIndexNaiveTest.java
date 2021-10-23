package inf353;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class MatriceIndexNaiveTest {

    public String matriceToString(int[][] m) {
        String matrice = "";
        for (int i = 0; i < m[0].length; i++) {
            for (int j = 0; j < m.length; j++) {
                matrice += m[i][j] + " ";
            }
            matrice += '\n';
        }
        return matrice;
    }
    
    @Test
    public void constructeursTest() throws IOException {

        MatriceIndexNaive m1 = new MatriceIndexNaive(3,3);
        int[][] attendu1 = {
            {0,0,0},
            {0,0,0},
            {0,0,0}
        };
        assertEquals(matriceToString(attendu1), matriceToString(m1.matrice));

        MatriceIndexNaive m2 = new MatriceIndexNaive("test-matrice.txt");
        int[][] attendu2 = {
            {1,0,0,0,8,0,0,0,0,0},
            {0,2,0,0,0,0,0,0,0,0},
            {0,0,3,0,0,0,0,9,0,0},
            {0,0,0,4,0,0,0,0,0,0},
            {0,0,0,0,5,0,0,0,0,12},
            {0,4,0,0,0,6,0,0,0,0},
            {0,0,0,0,0,0,7,0,0,0},
            {0,0,11,0,0,0,0,8,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {2,0,0,0,3,0,0,0,0,10}
        };
        assertEquals(matriceToString(attendu2), matriceToString(m2.matrice));
        
    }

    public void affecteTest() {
        MatriceIndexNaive m1 = new MatriceIndexNaive(3,3);
        m1.affecte(0, 1, 1);
        m1.affecte(1, 0, 2);
        m1.affecte(2, 2, 3);
        m1.affecte(2, 0, 4);
        m1.affecte(0, 2, 5);
        int[][] attendu1 = {
            { 0, 1, 5 },
            { 2, 0, 0 },
            { 4, 0, 3 }
        };
        assertEquals(matriceToString(attendu1), matriceToString(m1.matrice));
    }

    @Test
    public void valTest() throws IOException {
        MatriceIndexNaive m1 = new MatriceIndexNaive(3,3);
        /**
         * Construction de la matrice
         *  | 0 1 2 |
         *  | 0 2 4 |
         *  | 0 3 6 |  
         */
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                m1.affecte(i, j, (i+1)*(j));
            }
        }
        assertEquals(0, m1.val(0, 0));
        assertEquals(0, m1.val(2, 0));
        assertEquals(2, m1.val(0, 2));
        assertEquals(2, m1.val(1, 1));
        assertEquals(4, m1.val(1, 2));
        assertEquals(6, m1.val(2, 2));
        MatriceIndexNaive m2 = new MatriceIndexNaive("test-matrice.txt");
        assertEquals(1, m2.val(0, 0));
        assertEquals(8, m2.val(0, 4));
        assertEquals(0, m2.val(4, 0));
        assertEquals(4, m2.val(3, 3));
        assertEquals(11, m2.val(7, 2));
        assertEquals(0, m2.val(8, 8));
        assertEquals(3, m2.val(9, 4));
    }

    @Test
    public void incrementeTest() {

    }

    @Test
    public void sauverTest() {
        
    }

}
