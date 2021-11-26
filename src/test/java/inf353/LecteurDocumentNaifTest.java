package inf353;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import org.junit.Test;

public class LecteurDocumentNaifTest {
   /*
    @Test
    public void constructeurTest() throws IOException {
        LecteurDocumentNaif lecteur1 = new LecteurDocumentNaif("./src/test/resources/inf353/test-lecteur1.txt");
        //assertEquals(".\\src\\test\\resources\\inf353\\test-lecteur1.txt", lecteur1.fichier.getPath());
        assertEquals(null, lecteur1.fileReader);
        assertEquals('\0', lecteur1.caractereLu);
        assertEquals(null, lecteur1.mot);
    }

    @Test
    public void demarrerAvancerEtElementCourantTest() throws IOException {
        LecteurDocumentNaif lecteur1 = new LecteurDocumentNaif("./src/test/resources/inf353/test-lecteur1.txt");
        lecteur1.demarrer();
        assertEquals("Je", lecteur1.elementCourant());
        lecteur1.avancer();
        assertEquals("rajoute", lecteur1.elementCourant());
        lecteur1.avancer();
        assertEquals("une", lecteur1.elementCourant());
        lecteur1.avancer();
        assertEquals("première", lecteur1.elementCourant());
        lecteur1.avancer();
        assertEquals("ligne", lecteur1.elementCourant());
    }

    @Test
    public void estSeparateurTest() {
        assertTrue(LecteurDocumentNaif.estSeparateur(','));
        assertTrue(LecteurDocumentNaif.estSeparateur('_'));
        assertFalse(LecteurDocumentNaif.estSeparateur('x'));
        assertTrue(LecteurDocumentNaif.estSeparateur(';'));
        assertTrue(LecteurDocumentNaif.estSeparateur('?'));
        assertTrue(LecteurDocumentNaif.estSeparateur('\t'));
        assertTrue(LecteurDocumentNaif.estSeparateur('\n'));
        assertFalse(LecteurDocumentNaif.estSeparateur('f'));//** Test valide pour une lettre aléatoire f
        assertTrue(LecteurDocumentNaif.estSeparateur('.'));
    }

    @Test
    public void finDeSequenceTest() throws IOException {
        LecteurDocumentNaif lecteur1 = new LecteurDocumentNaif("./src/test/resources/inf353/test-lecteur1.txt");
        assertFalse(lecteur1.finDeSequence());
        lecteur1.demarrer();
        assertFalse(lecteur1.finDeSequence());
        while (!lecteur1.finDeSequence()) {
            lecteur1.avancer();
        }
        assertEquals("", lecteur1.elementCourant());
    }
*/
}
