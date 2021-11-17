package inf353;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class IndexationTest {

    @Test
    public void constructeursTest() {
        Indexation indexation1 = new Indexation();
        assertEquals(null, indexation1.matriceOccurences);
        assertEquals(null, indexation1.dictioMots);
        assertEquals(null, indexation1.dictioDocuments);
        assertEquals(0, indexation1.maxMots);
        assertEquals(0, indexation1.maxDocuments);

        // tester le constructeur avec fichier
    }

    @Test
    public void changerMatriceTest() {
        Indexation indexation1 = new Indexation();
        indexation1.changerMatrice();
        assertEquals(0, indexation1.matriceOccurences.matrice.length);
        Indexation indexation2 = new Indexation();
        indexation2.maxMots = 4;
        indexation2.maxDocuments = 8;
        indexation2.changerMatrice();
        assertEquals(4, indexation2.matriceOccurences.matrice[0].length);
        assertEquals(8, indexation2.matriceOccurences.matrice.length);
    }

    @Test
    public void ajouterMotTest() {
        Indexation indexation1 = new Indexation();
        indexation1.ajouterMot("test1");
        assertEquals(10, indexation1.maxMots);
        assertEquals(1, indexation1.dictioMots.nbMots());
        assertEquals("test1", indexation1.dictioMots.motIndice(0));
        for (int i = 2; i < 12; i++) {
            indexation1.ajouterMot("test" + i);
        }
        assertEquals(20, indexation1.maxMots);
        assertEquals(11, indexation1.dictioMots.nbMots());
        assertEquals("test11", indexation1.dictioMots.motIndice(10));  
    }

    @Test
    public void ajouterDocumentTest() throws IOException {
        Indexation indexation1 = new Indexation();
        indexation1.ajouterDocument("test1", false);
        assertEquals(10, indexation1.maxDocuments);
        assertEquals(1, indexation1.dictioDocuments.nbMots());
        assertEquals("test1", indexation1.dictioDocuments.motIndice(0));
        for (int i = 2; i < 12; i++) {
            indexation1.ajouterDocument("test" + i, false);
        }
        assertEquals(20, indexation1.maxDocuments);
        assertEquals(11, indexation1.dictioDocuments.nbMots());
        assertEquals("test11", indexation1.dictioDocuments.motIndice(10));  
    }

    @Test
    public void affecteTest() {
        Indexation indexation1 = new Indexation();

    }

    @Test
    public void incrementeTest() {
        Indexation indexation1 = new Indexation();

    }

    @Test
    public void valTest() {
        Indexation indexation1 = new Indexation();

    }

    @Test
    public void compterTest() {
        Indexation indexation1 = new Indexation();

    }

    @Test
    public void sauverTest() {
        Indexation indexation1 = new Indexation();

    }

}