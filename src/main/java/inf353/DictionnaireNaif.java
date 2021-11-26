package inf353;

import java.lang.Error;

public class DictionnaireNaif implements Dictionnaire {

    public char[] dictio;

    /**
     * Créer un dictionnaire qui peut contenir 10 mots
     */
    public DictionnaireNaif() {
        this(10);
    }

    /**
     * Créer un dictionnaire qui peut contenir n mots
     */
    public DictionnaireNaif(int n) {
        if (n <= 0) throw new Error("Veuillez entrer un nombre plus grand que 0 pour construire votre dictionnaire.");
        this.dictio = new char[40*n];
    }

        /**
     * é.i.: le dictionnaire contient D0 (un ensemble de N mots). é.f.: si m
     * appartient à D0; le dictionnaire est inchangé sinon, le dictionnaire contient
     * D0 U {m}
     *
     * @param m
     */
    public void ajouterMot(String m) {
        if (estValide(m)) {
            int i = 0;
            while (this.dictio[i] != '\0') {
                if (this.dictio[i+1] == '\0') i++;
                i++;
            }
            for (int j = 0; j < m.length(); j++) {
                this.dictio[i+j] = m.charAt(j);
            }
            this.dictio[i+m.length()] = '\0';
        }
    }

        /**
     * renvoie vrai ssi m est dans le dictionnaire.
     * 
     * @param m
     * @return 
     */
    public boolean contient(String m) {
        boolean estPresent = false;
        if (this.dictio[0] == '\0') estPresent = m == "";
        else {
            int i = 0;
            String resultat = "";
            while (!estPresent && i < this.dictio.length) {
                if (this.dictio[i] == '\0') {
                    if (resultat.equals(m)) estPresent = true;
                    resultat = "";
                } else {
                    resultat += this.dictio[i];
                }
                i++;
            }
        }
        return estPresent;
    }

        /**
     * renvoie le nombre de mots de m.
     * 
     * @return
     */
    public int nbMots() {
        int nb = 0;
        for (int i = 1; i < this.dictio.length; i++) {
            if (this.dictio[i-1] != '\0' && this.dictio[i] == '\0') {
                nb++;
            }
        }
        return nb;
    }

    /**
     * é.i: qcq é.f.: le dictionnaire est vide.
     */
    public void vider() {
        for (int i = 0; i < this.dictio.length; i++) {
            this.dictio[i] = '\0';
        }
    }

    /**
     * renvoie l'entier associé à m;
     * 
     * @param m
     * @return l'indice ou -1 s'il n'est pas trouvé
     */
    public int indiceMot(String m) {
        int i = 0;
        if (m == "" || !contient(m)) {
            i = -1;
        } else {
            int j = 0;
            String resultat = "";
            while (!resultat.equals(m)) {
                if (this.dictio[j] == '\0') {
                    resultat = "";
                    i++;
                } else {
                    resultat += this.dictio[j];
                }
                j++;
            }
        }
        return i;
    }

    /**
     * renvoie le mot associé à l'entier n;
     * 
     * @param n l'indice du mot à renvoyer
     * @return le mot ou ""
     */
    public String motIndice(int n) {
        String resultat = "";
        if (n > -1 && n < this.dictio.length/40) {
            int i = 0;
            int j = 0;
            while (i <= n && j < this.dictio.length) {
                if (this.dictio[j] == '\0') {
                    i++;
                } else {
                    if (i == n) {
                        resultat += this.dictio[j];
                    }
                }
                j++;
            }
        }
        return resultat;
    }

    /**
     * vrai ssi il existe m dans D0 tel que il exist u tq m = p.u
     *
     * (vari si un mot de D0 commence par p)
     * 
     * @param p le préfixe recherché
     * @return
     */
    public boolean contientPrefixe(String p) {
        Boolean estPresent = false;
        if (nbMots() != 0) {
            if (p == "") {
                estPresent = true;
            } else {
                if (this.dictio[0] != '\0') {
                    int i = 0;
                    int j = 0;
                    while (i < this.dictio.length && !estPresent) {
                        if (this.dictio[i] == p.charAt(j)) {
                            i++;
                            j++;
                            if (p.length() == j) {
                                estPresent = true;
                            }
                        }
                        else {
                            j = 0;
                            while (this.dictio[i] != '\0') {
                                i++;
                            }
                            i++;
                        }
                    }
                }
            }
        }
        return estPresent;
    }

    /**
     * renvoie la chaîne de caractères s telle que s est dans D0 et mot commence par
     * s et il n'existe pas de chaîne s' ds D plus longue que s tq mot commence par
     * s.
     *
     * @param mot
     * @return s sinon ""
     */
    public String plusLongPrefixeDe(String mot) {
        String plusLong = "";
        int i = 0;
        while (i < this.dictio.length) {
            int j = 0;
            String resultat = "";
            while (this.dictio[i] != '\0' && j < mot.length() && mot.charAt(j) == this.dictio[i]) {
                resultat += this.dictio[i];
                j++;
                i++;
            }
            if (this.dictio[i] == '\0' && plusLong.length() < resultat.length()) {
                plusLong = resultat;
            }
            while (this.dictio[i] != '\0') {
                i++;
            }
            i++;
        }
        return plusLong;
    }

    /**
     * Renvoie vrai si le mot est valide
     * @param mot le mot à tester
     * @return
     */
    public boolean estValide(String mot) {
        return (mot != "" && mot.length() < 40 && !contient(mot) && nbMots() < this.dictio.length/40);
    }

}