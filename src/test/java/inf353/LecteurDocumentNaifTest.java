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

   // @Test
    public void demarrerAvancerEtElementCourantTest() throws IOException {
        LecteurDocumentNaif lecteur = new LecteurDocumentNaif("./src/test/resources/inf353/test-lecteur1.txt");
        // lorsqu'on démarre, on est sur le premier élément
        lecteur.demarrer();
        assertEquals("j", lecteur.elementCourant());
        // puis on avance et on regarde sur quels mots on est
        lecteur.avancer();
        lecteur.avancer();
        assertEquals("une", lecteur.elementCourant());  //vérifie si lect.elementCourant est égale à "une " ou non.
        lecteur.avancer();
        lecteur.avancer();
        assertEquals("phrase", lecteur.elementCourant()); //vérifie si lect.elementCourant est égale à "phrase" ou non.
        lecteur.avancer();
        assertEquals("puis", lecteur.elementCourant()); //vérifie si lect.elementCourant est égale à "Puis" ou non.
        lecteur.avancer();
        assertEquals("aaaeeu", lecteur.elementCourant()); //vérifie si lect.elementCourant enlève les accents
    }

    @Test
    // Test des différents séparateurs de notre liste ainsi que des lettre qui composent nos mots  
    public void estSeparateurTest() {
        // le Teste est  True pour des différents séparateurs de notre liste
        assertTrue(LecteurDocumentNaif.estSeparateur(' '));
        assertTrue(LecteurDocumentNaif.estSeparateur('.'));
        assertTrue(LecteurDocumentNaif.estSeparateur(';'));
        assertTrue(LecteurDocumentNaif.estSeparateur('\n'));
        assertTrue(LecteurDocumentNaif.estSeparateur('('));
        assertTrue(LecteurDocumentNaif.estSeparateur('-'));
        assertTrue(LecteurDocumentNaif.estSeparateur('.'));
        assertTrue(LecteurDocumentNaif.estSeparateur('{'));
        assertTrue(LecteurDocumentNaif.estSeparateur('&'));

        // Le Teste  est False pour  des lettres qui composent nos mots
        assertFalse(LecteurDocumentNaif.estSeparateur('a'));
        assertFalse(LecteurDocumentNaif.estSeparateur('E'));
        assertFalse(LecteurDocumentNaif.estSeparateur('l'));
        assertFalse(LecteurDocumentNaif.estSeparateur('3'));
    }

    @Test
    //Test pour savoir si on n'est pas en fin de séquence dés l'inisialisation ou apres démarrer
    // Test pour savoir si on a une boucle infinie ou non
    public void finDeSequenceTest() throws IOException {
        // lorsqu'on initialise, on n' est pas en fin de séquence
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
