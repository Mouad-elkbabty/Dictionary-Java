package inf353;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import java.util.Arrays;

public class DictionnaireNaifTest {
/*
    @Test
    public void constructeurTest() {
        DictionnaireNaif dico1 = new DictionnaireNaif(1);
        assertEquals(dico1.dictio.length, 40);
        DictionnaireNaif dico2 = new DictionnaireNaif(100);
        assertEquals(dico2.dictio.length, 100*40);
        DictionnaireNaif dico3 = new DictionnaireNaif();
        assertEquals(dico3.dictio.length, 10*40);
    }

    @Test
    public void ajouterMotTest() {
        int n = 3;
        DictionnaireNaif dico1 = new DictionnaireNaif(n);
        String[] mots1 = {"abc", "def", "ghi"};
        for (int i = 0; i < mots1.length; i++) {
            dico1.ajouterMot(mots1[i]);
        }
        char[] resultat1 = {'a','b','c','\0','d','e','f','\0','g','h','i','\0','\0'};
        assertArrayEquals(resultat1, Arrays.copyOfRange(dico1.dictio, 0, 13));
        n = 3;
        DictionnaireNaif dico2 = new DictionnaireNaif(n);
        String[] mots2 = {"abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz", "def", "ghi"};
        for (int j = 0; j < mots2.length; j++) {
            dico2.ajouterMot(mots2[j]);
        }
        char[] resultat2 = {'d','e','f','\0','g','h','i','\0','\0','\0','\0','\0','\0'};
        assertArrayEquals(resultat2, Arrays.copyOfRange(dico2.dictio, 0, 13));
        n = 2;
        DictionnaireNaif dico3 = new DictionnaireNaif(n);
        String[] mots3 = {"abc", "def", "ghi"};
        for (int k = 0; k < mots3.length; k++) {
            dico3.ajouterMot(mots3[k]);
        }
        char[] resultat3 = {'a','b','c','\0','d','e','f','\0','\0'};
        assertArrayEquals(resultat3, Arrays.copyOfRange(dico3.dictio, 0, 9));
    }

    @Test
    public void contientTest() {
        DictionnaireNaif dico1 = new DictionnaireNaif();
        String[] mots = {"abc","def","ghi"};
        for (int i = 0; i < mots.length; i++) {
            dico1.ajouterMot(mots[i]);
        }
        assertTrue(dico1.contient("abc"));
        assertTrue(dico1.contient("ghi"));
        assertFalse(dico1.contient("acb"));
        assertFalse(dico1.contient("a"));
        assertTrue(dico1.contient(""));
    }

    @Test
    public void nbMotsTest() {
        DictionnaireNaif dico1 = new DictionnaireNaif();
        String[] mots1 = {"abc","def","ghi"};
        for (int i = 0; i < mots1.length; i++) {
            dico1.ajouterMot(mots1[i]);
        }
        assertEquals(3, dico1.nbMots());
        DictionnaireNaif dico2 = new DictionnaireNaif();
        String[] mots2 = {"a","b","c","d","e","f","g"};
        for (int i = 0; i < mots2.length; i++) {
            dico2.ajouterMot(mots2[i]);
        }
        assertEquals(7, dico2.nbMots());
        DictionnaireNaif dico3 = new DictionnaireNaif();
        assertEquals(0, dico3.nbMots());
    }

    @Test
    public void viderTest() {
        DictionnaireNaif dico1 = new DictionnaireNaif();
        dico1.vider();
        assertArrayEquals(new char[10*40], dico1.dictio);
        DictionnaireNaif dico2 = new DictionnaireNaif(3);
        String[] mots2 = {"abc","def","ghi"};
        for (int i = 0; i < mots2.length; i++) {
            dico2.ajouterMot(mots2[i]);
        }
        dico2.vider();
        assertArrayEquals(new char[3*40], dico2.dictio);
        DictionnaireNaif dico3 = new DictionnaireNaif(3);
        String[] mots3 = {"abc","def","ghi"};
        for (int i = 0; i < mots3.length; i++) {
            dico3.ajouterMot(mots3[i]);
        }
        dico3.vider();
        assertArrayEquals(new char[3*40], dico3.dictio);
    }

    @Test
    public void indiceMotTest() {
        DictionnaireNaif dico1 = new DictionnaireNaif();
        String[] mots = {"abc","def","ghi"};
        for (int i = 0; i < mots.length; i++) {
            dico1.ajouterMot(mots[i]);
        }
        assertEquals(0, dico1.indiceMot("abc"));
        assertEquals(2, dico1.indiceMot("ghi"));
        assertEquals(-1, dico1.indiceMot("dfe"));
        assertEquals(-1, dico1.indiceMot(""));
    }

    @Test
    public void motIndiceTest() {
        DictionnaireNaif dico1 = new DictionnaireNaif();
        String[] mots = {"abc","def","ghi"};
        for (int i = 0; i < mots.length; i++) {
            dico1.ajouterMot(mots[i]);
        }
        assertEquals("abc", dico1.motIndice(0));
        assertEquals("ghi", dico1.motIndice(2));
        assertEquals("", dico1.motIndice(3));
        assertEquals("", dico1.motIndice(-1));
    }

    @Test
    public void contientPrefixTest() {
        DictionnaireNaif dico1 = new DictionnaireNaif();
        String[] mots1 = {"abc","def","ghi"};
        for (int i = 0; i < mots1.length; i++) {
            dico1.ajouterMot(mots1[i]);
        }
        assertTrue(dico1.contientPrefixe("de"));
        assertTrue(dico1.contientPrefixe("gh"));
        assertFalse(dico1.contientPrefixe("p"));
        DictionnaireNaif dico2 = new DictionnaireNaif();
        String[] mots2 = {"ballon","balle","poteau"};
        for (int i = 0; i < mots2.length; i++) {
            dico2.ajouterMot(mots2[i]);
        }
        assertTrue(dico2.contientPrefixe(""));
        assertTrue(dico2.contientPrefixe("po"));
        assertTrue(dico2.contientPrefixe("ballo"));
        assertFalse(dico2.contientPrefixe("balo"));
        DictionnaireNaif dico3 = new DictionnaireNaif();
        assertFalse(dico3.contientPrefixe(""));
    }

    @Test
    public void plusLongPrefixeDeTest() {
        DictionnaireNaif dico1 = new DictionnaireNaif();
        String[] mots1 = {"ar","arbr","arb"};
        for (int i = 0; i < mots1.length; i++) {
            dico1.ajouterMot(mots1[i]);
        }
        assertEquals("arbr", dico1.plusLongPrefixeDe("arbre"));
        assertEquals("arb", dico1.plusLongPrefixeDe("arbitre"));
        assertEquals("ar", dico1.plusLongPrefixeDe("arene"));
        assertEquals("", dico1.plusLongPrefixeDe("b"));
    }
*/
}
