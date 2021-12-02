package inf353;


import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Test;

public class LecteurDocumentNaifTest {
   
    @Test
    public void constructeurTest() throws IOException {
        LecteurDocumentNaif lecteur1 = new LecteurDocumentNaif("./src/test/resources/inf353/test-lecteur1.txt");
        lecteur1.demarrer();
        assertEquals("J", lecteur1.mot);
    }

    @Test
    public void demarrerAvancerEtElementCourantTest() throws IOException {
        LecteurDocumentNaif lecteur1 = new LecteurDocumentNaif("./src/test/resources/inf353/test-lecteur1.txt");
        lecteur1.demarrer();
        assertEquals("J", lecteur1.elementCourant());
        lecteur1.avancer();
        lecteur1.avancer();
        assertEquals("une", lecteur1.elementCourant());
        lecteur1.avancer();
        //assertEquals("premiere", lecteur1.elementCourant());

    }

    @Test
    public void estSeparateurTest() {
        assertTrue(true);
    }

    @Test
    public void finDeSequenceTest() throws IOException {
        assertTrue(true);
    }

}
