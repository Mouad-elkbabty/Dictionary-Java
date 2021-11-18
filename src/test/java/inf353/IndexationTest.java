package inf353;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class IndexationTest {

    static String nomFichierMatrice1 = "test-indexation1-matrice.txt";
    static String nomFichierDictio1 = "test-indexation1-dictio.txt";

    @Test
    public void constructeursTest() throws IOException {
        Indexation indexation1 = new Indexation();
        assertEquals(null, indexation1.matriceOccurences);
        assertEquals(null, indexation1.dictioMots);
        assertEquals(null, indexation1.dictioDocuments);
        assertEquals(0, indexation1.maxMots);
        assertEquals(0, indexation1.maxDocuments);

        // ici, on teste donc en même temps la méthode charger
        Indexation indexation2 = new Indexation(nomFichierMatrice1, nomFichierDictio1);
        int[][] matrice = {
            { 1, 2, 3, 4, 5 },
            { 3, 5, 7, 9, 11 },
            { 0, 0, 10, 0, 0 },
            { 9, 7, 5, 3, 1 },
            { 0, 0, 0, 0, 0 }
        };
        assertEquals(5, indexation2.dictioMots.nbMots());
        assertEquals(10, indexation2.maxMots);
        for (int m = 0; m < indexation2.dictioMots.nbMots(); m++) {
            assertEquals("mot" + (m+1), indexation2.dictioMots.motIndice(m));
        }
        assertEquals(5, indexation2.dictioDocuments.nbMots());
        assertEquals(10, indexation2.maxDocuments);
        for (int d = 0; d < indexation2.dictioMots.nbMots(); d++) {
            assertEquals("doc" + (d+1), indexation2.dictioDocuments.motIndice(d));
        }
        assertArrayEquals(matrice, indexation2.matriceOccurences.matrice);
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
    public void valTest() throws IOException {
        Indexation indexation1 = new Indexation(nomFichierMatrice1, nomFichierDictio1);
        assertEquals(10, indexation1.val("mot3", "doc3"));
        assertEquals(1, indexation1.val("mot5", "doc4"));
        assertEquals(9, indexation1.val("mot1", "doc4"));
        assertEquals(-1, indexation1.val("mot8", "doc1"));
        assertEquals(-1, indexation1.val("mot2", "doc7"));
    }

    @Test
    public void affecteTest() throws IOException {
        Indexation indexation1 = new Indexation(nomFichierMatrice1, nomFichierDictio1);
        indexation1.affecte("mot3", "doc3", 100);
        assertEquals(100, indexation1.val("mot3", "doc3"));
        indexation1.affecte("mot5", "doc1", 12);
        assertEquals(12, indexation1.val("mot5", "doc1"));
        indexation1.affecte("mot2", "doc4", 0);
        assertEquals(0, indexation1.val("mot2", "doc4"));
        indexation1.affecte("mot6", "doc1", 0);
        indexation1.affecte("mot3", "doc9", 0);
    }

    @Test
    public void incrementeTest() throws IOException {
        Indexation indexation1 = new Indexation(nomFichierMatrice1, nomFichierDictio1);
        indexation1.incremente("mot1", "doc1");
        assertEquals(2, indexation1.val("mot1", "doc1"));
        indexation1.incremente("mot2", "doc4");
        assertEquals(8, indexation1.val("mot2", "doc4"));
        indexation1.incremente("mot5", "doc3");
        assertEquals(1, indexation1.val("mot5", "doc3"));
        indexation1.incremente("mot7", "doc3");
        indexation1.incremente("mot1", "doc8");
    }

    @Test
    public void compterTest() throws IOException {
        Indexation indexation1 = new Indexation();
        String document = "test-lecteur1.txt";
        indexation1.ajouterDocument(document, true);
        String mots[] = { "un", "mot", "je", "rajoute", "une", "ligne" };
        for (int m = 0; m < mots.length; m++) {
            assertEquals(1, indexation1.val(mots[m], document));
        }
    }

    @Test
    public void sauverTest() throws IOException {
        Indexation indexation1 = new Indexation();
        String document = "test-lecteur1.txt";
        indexation1.ajouterDocument(document, true);
        String cheminMatrice = "output-indexation1-matrice.txt";
        String cheminDictio = "output-indexation1-dictio.txt";
        indexation1.sauver(Indexation.chemin + cheminMatrice, Indexation.chemin + cheminDictio);

        Indexation chargement1 = new Indexation(cheminMatrice, cheminDictio);
        assertEquals(document, chargement1.dictioDocuments.motIndice(0));
        String mots[] = { "un", "mot", "je", "rajoute", "une", "ligne" };
        for (int m = 0; m < mots.length; m++) {
            assertEquals(mots[m], chargement1.dictioMots.motIndice(m));
            assertEquals(1, chargement1.val(mots[m], document));
        }
    }

}