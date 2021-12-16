package inf353;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class DictionnaireHashTest {

    // impossible d'utiliser ajouterMot sans vérifier indiceMot
    public void indiceMotTest() {
        DictionnaireHash dictio = new DictionnaireHash();
        dictio.T["test".hashCode()%dictio.N] = new CelluleDictio("test", 0,0,0, null);
        dictio.nb += 1;
        // après ajout du mot,
        assertEquals(0, dictio.indiceMot("test"));
        assertEquals(-1, dictio.indiceMot("pas test"));
    }

    // impossible d'utiliser ajouterMot sans vérifier contient
    // test de Contient pour être sûr qu'il renvoie False si le mot n'y ai pas , et True s'il contient le mot
    @Test
    public void contientTest() {
        DictionnaireHash dictio = new DictionnaireHash();
        dictio.T["test".hashCode()%dictio.N] = new CelluleDictio("test",0,0,0, null);
        dictio.nb += 1;
        // le dictionnaire contient bien le mot test et inversement pour pas test
        assertTrue(dictio.contient("test")); 
        assertFalse(dictio.contient("pas test"));
    }

    @Test
    public void ajouterMotTest() {
        DictionnaireHash dictio = new DictionnaireHash();
        dictio.ajouterMot("test");
        // après un ajout, la case correspondant au mot test n'est pas null et il y a 1 mot
        assertNotEquals(null, dictio.T["test".hashCode()%dictio.N]);
        assertEquals(1, dictio.nb);
        dictio.ajouterMot("un autre test");
        dictio.ajouterMot("puis encore un");
        // après 2 autres ajouts, il y en a 3
        assertEquals(3, dictio.nb);
    }

    @Test
    public void nbMotsTest() {
        DictionnaireHash dictio = new DictionnaireHash();
        // à l'initialisation, il n'y a aucun mot
        assertEquals(0, dictio.nbMots());
        dictio.ajouterMot("test1");
        // après un ajout, il y a 1 mot
        assertEquals(1, dictio.nbMots());
        for (int i = 2; i <= 10; i++) {
            dictio.ajouterMot("test" + i);
        }
        // après 9 autres ajouts, il y en a 10
        assertEquals(10, dictio.nbMots());
    }

    // impossible d'utiliser le constructeur avec chemin sans charger
    //@Test
    public void chargerTest() throws IOException {
        DictionnaireHash dictio = new DictionnaireHash();
        dictio.charger("./src/test/resources/inf353/test-dictionnaire1.txt");
        // il y a 8 mots dans le fichier chargé
        assertEquals(8, dictio.nbMots());
        // on regarde s'ils sont dans le bon ordre
        assertEquals(0, dictio.indiceMot("manger"));
        assertEquals(7, dictio.indiceMot("ENCOURAGER"));
        assertEquals(2, dictio.indiceMot("balancer"));
        // ce mot se trouve à la deuxième ligne : il n'est pas compté
        assertEquals(-1, dictio.indiceMot("TEST"));
    }

    //@Test
    public void constructeursTest() throws IOException {
        // dictionnaire par défaut avec un tableau de longueur 2000 et sans mots
        DictionnaireHash dictio1 = new DictionnaireHash();
        assertEquals(4000, dictio1.T.length);
        assertEquals(0, dictio1.nb);
        // dictionnaire avec un tableau de longueur 10 et sans mots
        DictionnaireHash dictio2 = new DictionnaireHash(10);
        assertEquals(10, dictio2.T.length);
        assertEquals(0, dictio2.nb);
        // dictionnaire avec un tableau de longueur 2000
        DictionnaireHash dictio3 = new DictionnaireHash("./src/test/resources/inf353/test-dictionnaire1.txt");
        assertEquals(4000, dictio1.T.length);
        assertEquals(8, dictio3.nb);
    }

    @Test
    //Test de MotIndice, voir si il rend bien le mot voulu et le int voulu ( indice )
    public void motIndiceTest() {
        DictionnaireHash dictio = new DictionnaireHash();
        // on ajoute les mots test0, test1, test2 jusqu'à test9
        for (int i = 0; i < 10; i++) {
            dictio.ajouterMot("test" + i);
        }
        // on vérifie que chaque indice correspond bien au mot test + indice
        for (int i = 0; i < 10; i++) {
            assertEquals("test" + i, dictio.motIndice(i));
        }
    }

    @Test
    public void viderTest() {
        DictionnaireHash dictio = new DictionnaireHash();
        // on ajoute les mots test0, test1, test2 jusqu'à test9
        for (int i = 0; i < 10; i++) {
            dictio.ajouterMot("test" + i);
        }
        dictio.vider();
        // le dictionnaire ne contient plus aucun mot
        assertEquals(0, dictio.nbMots());
        assertEquals(-1, dictio.indiceMot("test0"));
    }

    @Test
    //Vérification Du préfixe vide ainsi que des préfixes au hasard
    public void contientPrefixeTest() {
        DictionnaireHash dictio = new DictionnaireHash();
        String[] mots = { "salut", "je", "fais", "des", "tests" };
        // on ajoute les mots du tableau mots
        for (int i = 0; i < mots.length; i++) {
            dictio.ajouterMot(mots[i]);
        }
        // on vérifie le préfixe vide
        assertTrue(dictio.contientPrefixe(""));
        // on vérifie des préfixes au hasard
        assertTrue(dictio.contientPrefixe("sal"));
        assertTrue(dictio.contientPrefixe("salut"));
        assertTrue(dictio.contientPrefixe("tes"));
        assertFalse(dictio.contientPrefixe("jee"));
        assertFalse(dictio.contientPrefixe("b"));
    }

    @Test
    //Vérification  de  plusLongPrefixeDeTest() par des préfixes au hasard
    public void plusLongPrefixeDeTest() {
        DictionnaireHash dictio = new DictionnaireHash();
        String[] prefixes = { "prefi", "pre", "pres", "pr", "pp" };
        // on ajoute les préfixes du tableau prefixes
        for (int i = 0; i < prefixes.length; i++) {
            dictio.ajouterMot(prefixes[i]);
        }
        // on vérifie des préfixes au hasard
        assertEquals("prefi", dictio.plusLongPrefixeDe("prefix"));
        assertEquals("pre", dictio.plusLongPrefixeDe("pretendre"));
        assertEquals("pr", dictio.plusLongPrefixeDe("prier"));
        assertEquals("", dictio.plusLongPrefixeDe("plaie"));
        assertEquals("pres", dictio.plusLongPrefixeDe("pres"));
    }

    //@Test
    public void sauverTest() throws IOException {
        DictionnaireHash dictio = new DictionnaireHash();
        // on ajoute les mots test0, test1, test2 jusqu'à test9
        for (int i = 0; i < 10; i++) {
            dictio.ajouterMot("test" + i);
        }
        // on le sauvegarde puis on le charge
        dictio.sauver("./src/test/resources/inf353/output-dictionnaire1.txt");
        DictionnaireHash resultat = new DictionnaireHash("./src/test/resources/inf353/output-dictionnaire1.txt");
        // on vérifie le nombre de mots ainsi que le contenu du dictionnaire
        assertEquals(10, resultat.nbMots());
        for (int i = 0; i < 10; i++) {
            assertEquals(i, resultat.indiceMot("test" + i));
        }
    }

}
