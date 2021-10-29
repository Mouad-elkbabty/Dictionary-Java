package inf353;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.junit.Test;

public class LecteurDocumentTest {
    

    @Test // Passe
    public void constructeurTest() throws FileNotFoundException, java.io.IOException
    {
        LecteurDocumentNaif naf = new LecteurDocumentNaif("./src/test/resources/inf353/TestLecteur.txt");
        naf.demarrer();
        assertEquals("Un", naf.elementCourant());
    }

    @Test // Passe
    public void constructeurTest2() throws FileNotFoundException, java.io.IOException
    {
        LecteurDocumentNaif naf = new LecteurDocumentNaif("./src/test/resources/inf353/TestLecteur.txt");
        naf.demarrer();
        naf.avancer();
        assertEquals("mot", naf.elementCourant());
    }

    @Test // Passe
    public void constructeurTest3() throws FileNotFoundException, java.io.IOException
    {
        LecteurDocumentNaif naf = new LecteurDocumentNaif("./src/test/resources/inf353/TestLecteur.txt");
        naf.demarrer();
        naf.avancer();
        naf.avancer();

        assertEquals("Je", naf.elementCourant());
    }

}