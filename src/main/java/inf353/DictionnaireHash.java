package inf353;

public class DictionnaireHash implements Dictionnaire {

    /**
     * Crée un DictionnaireHash vide
     */
    public DictionnaireHash() {

    }

    /**
     * Vide le dictionnaire
     */
    @Override
    public void vider() {
        return;        
    }

    /**
     * Ajoute un mot au dictionnaire
     * @param m Le mot à ajouter
     */
    @Override
    public void ajouterMot(String m) {
        return;
    }

    /**
     * Retourne l'indice du mot dans le DictionnaireHash ou -1 s'il n'est pas trouvé
     * @param m Le mot à tester
     * @return L'indice du mot testé
     */
    @Override
    public int indiceMot(String m) {
        return 0;
    }

    /**
     * Retourne le mot contenu à l'indice i dans le DictionnaireHash ou null s'il n'existe pas
     * @param i L'indice à tester
     * @return Le mot de l'indice testé
     */
    @Override
    public String motIndice(int i) {
        return null;
    }

    /**
     * Renvoie vrai si le mot est contenu dans le DictionnaireHash
     * @param m Le mot à tester
     * @return 
     */
    @Override
    public boolean contient(String m) {
        return false;
    }

    /**
     * Retourne le nombre de mots dans le DictionnaireHash
     * @return
     */
    @Override
    public int nbMots() {
        return 0;
    }

    /**
     * Retourne vrai si le préfixe est contenu dans l'un des mots du DictionnaireHash
     * @param p Le préfixe à tester
     * @return
     */
    @Override
    public boolean contientPrefixe(String p) {
        return false;
    }

    /**
     * Retourne le préfixe le plus long du mot contenu dans le DictionnaireHash
     * @param mot Le mot à tester
     * @return
     */
    @Override
    public String plusLongPrefixeDe(String mot) {
        return null;
    }
    
}
