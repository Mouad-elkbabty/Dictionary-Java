package inf353;


import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Test;

public class LecteurDocumentNaifTest {
   
    @Test
    public void constructeurTest() throws IOException {
        LecteurDocumentNaif lecteur = new LecteurDocumentNaif("./src/test/resources/inf353/test-lecteur1.txt");
        // le lecteur n'a pas été démarré
        assertNotEquals(null, lecteur.fichier);
        assertEquals(null, lecteur.fileReader);
        assertEquals(0, lecteur.caractereLu);
        assertEquals(null, lecteur.mot);
    }

    @Test
    public void demarrerAvancerEtElementCourantTest() throws IOException {
        LecteurDocumentNaif lecteur = new LecteurDocumentNaif("./src/test/resources/inf353/test-lecteur1.txt");
        // lorsqu'on démarre, on est sur le premier élément
        lecteur.demarrer();
        assertEquals("J", lecteur.elementCourant());
        // puis on avance et on regarde sur quels mots on est
        lecteur.avancer();
        lecteur.avancer();
        assertEquals("une", lecteur.elementCourant());
        lecteur.avancer();
        lecteur.avancer();
        assertEquals("phrase", lecteur.elementCourant());
        lecteur.avancer();
        assertEquals("Puis", lecteur.elementCourant());
    }

    @Test
    public void estSeparateurTest() {
        // on teste différents séparateurs de notre liste
        assertTrue(LecteurDocumentNaif.estSeparateur(' '));
        assertTrue(LecteurDocumentNaif.estSeparateur('.'));
        assertTrue(LecteurDocumentNaif.estSeparateur(';'));
        assertTrue(LecteurDocumentNaif.estSeparateur('\n'));
        assertTrue(LecteurDocumentNaif.estSeparateur('('));
        assertTrue(LecteurDocumentNaif.estSeparateur('-'));
        assertTrue(LecteurDocumentNaif.estSeparateur('.'));
        // puis on teste des lettres qui composent nos mots
        assertFalse(LecteurDocumentNaif.estSeparateur('a'));
        assertFalse(LecteurDocumentNaif.estSeparateur('E'));
        assertFalse(LecteurDocumentNaif.estSeparateur('l'));
        assertFalse(LecteurDocumentNaif.estSeparateur('3'));
    }

    @Test
    public void finDeSequenceTest() throws IOException {
        // lorsqu'on initialise, on est pas en fin de séquence
        LecteurDocumentNaif lecteur = new LecteurDocumentNaif("./src/test/resources/inf353/test-lecteur1.txt");
        assertFalse(lecteur.finDeSequence());
        // ni quand on démarre
        lecteur.demarrer();
        assertFalse(lecteur.finDeSequence());
        // on avance jusqu'à la fin pour vérifier que l'on a pas de boucle infinie
        while (!lecteur.finDeSequence()) {
            lecteur.avancer();
        }
        assertTrue(lecteur.finDeSequence());
    }

}
