package inf353;

import static org.junit.Assert.*;

import org.junit.Test;

public class DictionnaireHashTest {
    
    /*
    @Test
    public void constructeurTest() {
        DictionnaireHash dictio = new DictionnaireHash();
        assertEquals(100000, dictio.T.length);
        assertEquals(0, dictio.nb);
    }

    @Test
    public void nbMotsTest() {
        DictionnaireHash dictio = new DictionnaireHash();
        assertEquals(0, dictio.nbMots());
        dictio.ajouterMot("test1");
        assertEquals(1, dictio.nbMots());
        for (int i = 2; i <= 10; i++) {
            dictio.ajouterMot("test" + i);
        }
        assertEquals(10, dictio.nbMots());
    }

    // impossible d'utiliser ajouterMot sans vérifier indiceMot
    public void indiceMotTest() {
        DictionnaireHash dictio = new DictionnaireHash();
        dictio.T["test".hashCode()%DictionnaireHash.N] = new CelluleDictio("test", 0, null);
        dictio.nb += 1;
        assertEquals(0, dictio.indiceMot("test"));
        assertEquals(-1, dictio.indiceMot("pas test"));
    }

    // impossible d'utiliser ajouterMot sans vérifier contient
    @Test
    public void contientTest() {
        DictionnaireHash dictio = new DictionnaireHash();
        dictio.T["test".hashCode()%DictionnaireHash.N] = new CelluleDictio("test", 0, null);
        dictio.nb += 1;
        assertEquals(true, dictio.contient("test"));
        assertEquals(false, dictio.contient("pas test"));
    }

    @Test
    public void ajouterMotTest() {
        DictionnaireHash dictio = new DictionnaireHash();
        dictio.ajouterMot("test");
        assertNotEquals(null, dictio.T["test".hashCode()%DictionnaireHash.N]);
        assertEquals(1, dictio.nbMots());
        dictio.ajouterMot("un autre test");
        dictio.ajouterMot("puis encore un");
        assertEquals(3, dictio.nbMots());
    }

    @Test
    public void motIndiceTest() {
        DictionnaireHash dictio = new DictionnaireHash();
        for (int i = 0; i < 10; i++) {
            dictio.ajouterMot("test" + i);
        }
        for (int i = 0; i < 10; i++) {
            assertEquals("test" + i, dictio.motIndice(i));
        }
    }

    @Test
    public void viderTest() {
        DictionnaireHash dictio = new DictionnaireHash();
        for (int i = 0; i < 10; i++) {
            dictio.ajouterMot("test" + i);
        }
        dictio.vider();
        assertEquals(0, dictio.nbMots());
    }

    @Test
    public void contientPrefixeTest() {
        DictionnaireHash dictio = new DictionnaireHash();
        String[] mots = { "salut", "je", "fais", "des", "tests" };
        for (int i = 0; i < mots.length; i++) {
            dictio.ajouterMot(mots[i]);
        }
        assertTrue(dictio.contientPrefixe(""));
        assertTrue(dictio.contientPrefixe("sal"));
        assertTrue(dictio.contientPrefixe("salut"));
        assertTrue(dictio.contientPrefixe("tes"));
        assertFalse(dictio.contientPrefixe("jee"));
        assertFalse(dictio.contientPrefixe("b"));
    }
    */

    @Test
    public void plusLongPrefixeDeTest() {
        DictionnaireHash dictio = new DictionnaireHash();
        String[] mots = { "prefi", "pre", "pres", "pr", "pp" };
        for (int i = 0; i < mots.length; i++) {
            dictio.ajouterMot(mots[i]);
        }
        assertEquals("prefi", dictio.plusLongPrefixeDe("prefix"));
        assertEquals("pre", dictio.plusLongPrefixeDe("pretendre"));
        assertEquals("pr", dictio.plusLongPrefixeDe("prier"));
        assertEquals("", dictio.plusLongPrefixeDe("plaie"));
        assertEquals("pres", dictio.plusLongPrefixeDe("pres"));
    }

}
