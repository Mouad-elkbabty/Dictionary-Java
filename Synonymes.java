package inf353;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class Synonymes {

    int N;
    CelluleSynonyme[] T;

    public Synonymes() {
        this(1000);
    }

    public Synonymes(int N) {
        this.N = N;
        this.T = new CelluleSynonyme[N];
    }

    public Synonymes(String chemin) throws IOException {
        this();
        this.charger(chemin);
    }

    /**
     *
     */
    public void vider() {
        this.T = new CelluleSynonyme[this.N];
    }

    /**
     * 
     */
    public void ajouter(String expression, String synonyme) {
        int i = Math.abs(expression.hashCode() % this.N);
        this.T[i] = new CelluleSynonyme(expression, synonyme, this.T[i]);
    }

    /**
     * 
     */
    public int indiceExpression(String expression) {
        int indice = -1;
        int i = Math.abs(expression.hashCode() % this.N);
        CelluleSynonyme cc = this.T[i];
        while (cc != null && !cc.expression.equals(expression)) {
            cc = cc.suiv;
        }
        if (cc != null) {
            indice = i;
        }
        return indice;
    }

    /**
     * 
     */
    public CelluleSynonyme synonymesDe(String expression) {
        CelluleSynonyme tete = null;
        int indice = this.indiceExpression(expression);
        if (indice != -1) {
            CelluleSynonyme cc = this.T[indice];
            while (cc != null) {
                if (cc.expression.equals(expression)) {
                    tete = new CelluleSynonyme(cc.expression, cc.synonyme, tete);
                }
                cc = cc.suiv;
            }
        }
        return tete;
    }

    /**
     *
     */
    public int nbMots() {
        int nb = 0;
        int i = 0;
        while (i != this.T.length) {
            CelluleSynonyme cc = this.T[i];
            while (cc != null) {
                nb++;
                cc = cc.suiv;
            }
            i++;
        }
        return nb;
    }

    /**
     *
     */
    public String plusLongPrefixeDe(String expression) {
        boolean trouve = this.indiceExpression(expression) != -1;
        while (expression.length() > 0 && !trouve) {
            expression = expression.substring(0, expression.length() - 1);
            trouve = this.indiceExpression(expression) != -1;
        }
        return expression;
    }

    /**
     * 
     */
    public Indexation indexerAvecSynonymes(String chemin, int tailleMots, int tailleDocuments, int tailleMatrice) throws IOException {
        Indexation indexation = new Indexation(tailleMots, tailleDocuments, tailleMatrice);
        String phrase = "";
        LecteurDocumentNaif lecteur = new LecteurDocumentNaif(chemin);
        lecteur.demarrer();
        while (!lecteur.finDeSequence()) {
            phrase += lecteur.elementCourant() + " ";
            lecteur.avancer();
        }
        int i = 0;
        while (i < phrase.length()) {
            String nouvellePhrase = phrase.substring(i, phrase.length());
            String res = this.plusLongPrefixeDe(nouvellePhrase);
            if (res.equals("")) {
                String mot = "";
                char c;
                while (i != phrase.length() && (c = phrase.charAt(i)) != ' ') {
                    mot += c;
                    i++;
                }
                indexation.ajouterMot(mot);
            } else {
                CelluleSynonyme cc = this.synonymesDe(res);
                while (cc != null) {
                    String mot = "";
                    int j = 0;
                    char c;
                    while (j != cc.synonyme.length()) {
                        c = cc.synonyme.charAt(j);
                        if (c == ',' || c == '-' || c == ' ') {
                            if (!mot.equals("")) indexation.ajouterMot(mot);
                            mot = "";
                        } else {
                            mot += c;
                        }
                        j++;
                    }
                    if (!mot.equals("")) indexation.ajouterMot(mot);
                    cc = cc.suiv;
                }
                String mot = "";
                int k = 0;
                char c;
                while (k != res.length()) {
                    c = res.charAt(k);
                    if (c == ',' || c == '-' || c == ' ') {
                        if (!mot.equals("")) indexation.ajouterMot(mot);
                        mot = "";
                    } else {
                        mot += c;
                    }
                    k++;
                }
                if (!mot.equals("")) indexation.ajouterMot(mot);
                i += res.length();
            }
            i++;
        }
        return indexation;
    }

    /**
     *
     */
    public void charger(String chemin) throws IOException {
        // "src/main/resources/inf353/indexation/Synonymes.txt"
        // Initialisation du fichier et du Buffer
        File fichier = new File(chemin);
        if (!fichier.exists() || !fichier.isFile())
            throw new FileNotFoundException("Aucun fichier n'a ??t?? trouv?? au chemin " + chemin);
        BufferedReader buffer = new BufferedReader(new FileReader(fichier));
        
        // Remplissage du Dictionnaire de synonymes
        String ligne = buffer.readLine();
        while (ligne != null) {
            int i = 0;
            char c;
            String expression = "";
            while (i != ligne.length() && (c = ligne.charAt(i)) != '\t') {
                if (c == '_') expression += " ";
                else expression += c;
                i++;
            }
            i++;
            String synonyme = "";
            while (i != ligne.length()) {
                c = ligne.charAt(i);
                if (c == '_') synonyme += " ";
                else synonyme += c;
                i++;
            }
            this.ajouter(LecteurDocumentNaif.supprimeAccents(expression), LecteurDocumentNaif.supprimeAccents(synonyme));
            ligne = buffer.readLine();
        }
    }

}