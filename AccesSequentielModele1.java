package inf353;

/**
 * Interface générique d'un accès séquenciel de modèle 1 d'élemnts de type T
 */
public interface AccesSequentielModele1<T> {

    /**
     * Initialisation du parcours.
     */
    public void demarrer()throws java.io.IOException;

    /**
     * Passage à l'élément suivant
     */
    public void avancer() throws java.io.IOException;

    /**
     * vrai ssi la séquence est épuisée
     * @return
     */
    public boolean finDeSequence() throws java.io.IOException;

    /**
     * renvoie l'élément courant
     * @return
     */
    public T elementCourant() throws java.io.IOException;

}
