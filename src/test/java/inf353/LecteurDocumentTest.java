package inf353;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.junit.Test;

public class LecteurDocumentTest {
    @Test
    public void lireFichierTest() throws FileNotFoundException, java.io.IOException
    {
        LecteurDocumentNaif naf = new LecteurDocumentNaif("./src/test/resources/inf353/TestLecteur.txt");
        String mot = "Un mot";
        assertEquals(mot, naf.mot);
    }
}