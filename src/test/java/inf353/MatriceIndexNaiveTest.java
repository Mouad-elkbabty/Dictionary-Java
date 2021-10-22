package inf353;

import java.io.IOException;

import org.junit.Test;

public class MatriceIndexNaiveTest {
    
    @Test
    public void constructeurTest() throws IOException {
        MatriceIndexNaive matrice1 = new MatriceIndexNaive(10, 10);
        matrice1.sauver("test-matrice1.txt");
    }

}
