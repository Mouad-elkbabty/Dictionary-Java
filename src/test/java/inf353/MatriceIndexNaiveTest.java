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
        assertEquals(matriceToString(m1.matrice), matriceToString(attendu1));

        MatriceIndexNaive m2 = new MatriceIndexNaive("test-matrice1.txt");
        int[][] attendu2 = {
            {1,0,0,0,0,0,0,0,0,0},
            {0,2,0,0,0,0,0,0,0,0},
            {0,0,3,0,0,0,0,0,0,0},
            {0,0,0,4,0,0,0,0,0,0},
            {0,0,0,0,5,0,0,0,0,0},
            {0,0,0,0,0,6,0,0,0,0},
            {0,0,0,0,0,0,7,0,0,0},
            {0,0,0,0,0,0,0,8,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,10}
        };
        assertEquals(matriceToString(m2.matrice), matriceToString(attendu2));
        
    }

}
