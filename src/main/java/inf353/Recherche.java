package inf353;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Recherche {

    Indexation indexation;
    Indexation recherche;
    File sortie;

    /**
     * Crée une Recherche
     * @param chemin Le chemin vers l'Indexation à utiliser
     */
    public Recherche(String chemin) throws IOException {
        // Creation du dossier requetes
        File dossier = new File("./src/main/resources/inf353/requetes/");
        if (!dossier.isDirectory()) {
            dossier.mkdir();
        }

        // Chargement de l'Indexation
        System.out.println("Chargement de l'indexation en cours...");
        this.indexation = new Indexation(chemin);
        System.out.println("Chargement termine !");
    }

    public void requete(String nomFic, String requete, int nbResultats) throws IOException {
        // Chargement du fichier
        String chemin = "./src/main/resources/inf353/requetes/" + nomFic;
        this.sortie = new File(chemin);
        if (this.sortie.isDirectory())
            throw new IOException("Le chemin \"" + chemin + "\" est un dossier.");
        File dossier = new File(this.sortie.getParent());
        if (dossier != null && !dossier.isDirectory()) dossier.mkdir();
        this.sortie.createNewFile();
        
        // Initialisation du Buffer
        BufferedWriter buffer = new BufferedWriter(new FileWriter(this.sortie.getPath(), false));
        // Ecriture de la requete dans un fichier pour utiliser le Lecteur
        buffer.write(requete);
        buffer.flush();
        buffer.close();
        // Attribution de la nouvelle recherche
        this.recherche = new Indexation(1, 1, 1);
        this.recherche.ajouterDocument(this.sortie.getPath());
        // Presentation du resultat
        this.presentation(nbResultats);
    }

    public void requete(int numeroRequete, int nbResultats) throws IOException {
        // Verification de l'entier donne
        if (numeroRequete < 91 || numeroRequete > 140) throw new Error("Veuillez entrer un nombre entre 91 et 140 (inclus)");
        // Verification du chemin pour voir si tout est en ordre
        String num = "";
        if (numeroRequete < 100) num = "0";
        String chemin = System.getProperty("user.home") + "/ubuntu/requete/inf353-tests/C" + num + numeroRequete;
        this.sortie = new File(chemin);
        if (!this.sortie.exists()) throw new IOException("Le chemin \"" + chemin + "\" n'existe pas.");
        if (this.sortie.isDirectory()) throw new IOException("Le chemin \"" + chemin + "\" est un dossier.");

        // Creation du Buffer
        BufferedReader buffer = new BufferedReader(new FileReader(this.sortie));

        // Recuperation de la requete stockee
        String res = "";
        String ligne = buffer.readLine();
        while (ligne != null) {
            res += ligne + "\n";
            ligne = buffer.readLine();
        }
        // Fermeture du Buffer
        buffer.close();

        // Utilisation de notre autre methode requete()
        this.requete(numeroRequete + "", res, nbResultats);
    }

    /**
     * Trie les résultats du meilleur au pire score et affiche les resultat
     */
    public void presentation(int nbResultats) throws IOException {
        System.out.println("Calcul du score en cours...");
        double[] valeurs = score(); 
        System.out.println("Calcul terminé !");
        int[] positions = new int[valeurs.length];
        for (int p = 0; p < positions.length; p++) {
            positions[p] = p;
        }
        int longueur = valeurs.length;
        int i = 0;
        BufferedWriter buffer = new BufferedWriter(new FileWriter(this.sortie, false));
        System.out.println("Ecriture des resultats en cours...");
        while (i < longueur && i != nbResultats) {
            // on cherche la position du max de resultats
            int position = 0;
            int j = 1;
            // parcours de tous les éléments jusqu'à la longueur
            while (j < longueur) {
                if (valeurs[j] > valeurs[position]) {
                    position = j;
                }
                j++;
            }
            // position contient la position de la plus grande valeur trouvée
            String resultat = this.sortie.getName() + '\t' + "Q0" + '\t' + indexation.dictioDocuments.motIndice(positions[position]) + '\t' + (i+1) + '\t' + valeurs[position] + '\t' + "lnn-lnn";
            buffer.write(resultat);
            buffer.newLine();
            // on retire 1 à la longueur pour mettre la dernière valeur à sa place
            longueur -= 1;
            valeurs[position] = valeurs[longueur];
            positions[position] = positions[longueur];
            i++;
        }
        buffer.flush();
        buffer.close();
        System.out.println("Ecriture terminee dans le fichier " + this.sortie.getName());
    } 

    /**
     * Calcule le score des documents en fonction de l'Indexation et de la recherche
     */
    public double[] score() throws IOException {
        // un element pour un document
        double[] scores = new double[indexation.dictioDocuments.nbMots()];
        // on veut parcourir tous les mots de notre requete
        CelluleDictio cc = this.recherche.dictioMots.T[0];
        // tant qu'on a pas traite tous les mots
        while (cc != null) {
            System.out.println("Calcul du score avec le mot " + cc.elt);
            // i = index du document
            int i = 0;
            double ponderationLocaleRequete = this.ponderationLocaleRequete(cc.elt);
            double ponderationGlobaleRequete = this.ponderationGlobaleRequete(cc.elt);
            // tant qu'on a pas traite tous les scores
            while (i != scores.length) {
                String document = this.indexation.dictioDocuments.motIndice(i);
                double ponderationLocaleDocument = this.ponderationLocaleDocumentl(cc.elt, document);
                double ponderationGlobaleDocument = ponderationGlobaleDocumentT(cc.elt);
                scores[i] += ponderationLocaleDocument * ponderationLocaleRequete * ponderationGlobaleDocument * ponderationGlobaleRequete / (normalisationDocument() * normalisationRequete());
                i++;
            }
            cc = cc.suiv;
        }
        return scores;
    }

    /**
     * Renvoie la valeur de la pondération du mot dans le document
     * Cette pondération est de niveau l (facteur logarithmique)
     * @param mot le mot à chercher
     * @param document le document à chercher
     */
    public double ponderationLocaleDocumentl(String mot, String document) {
        double res = 0;
        int val = this.indexation.val(mot, document);
        if(val > 0) {
            res = 1 + Math.log(val);
        }
        return res;
    }

     /**
     * Renvoie la valeur de la pondération du mot dans le document
     * Cette pondération est de niveau L (facteur logarithmique normalisé)
     * formule: [ 1 + log(freq(t,d)) ] / [ 1 + log(avg(freq(t,d))) ]  avec freq(t,d) != 0
     * @param mot le mot à chercher
     * @param document le document à chercher
     */
    public double ponderationLocaleDocumentL(String mot, String document) {
        double res = 0;
        int val = this.indexation.val(mot, document);
        double avg = this.indexation.dictioMots.nbOccMot(mot)/this.indexation.dictioDocuments.nbMots();
        if(val > 0) {
            res = (1 + Math.log(val))/(1+Math.log(avg));
        }
        return res;
    }
    /**
     * Renvoie la valeur de la pondération dans le corpus
     * Cette pondération est de niveau t (1 + log (N/df) )
     */
    
    public double ponderationGlobaleDocumentT(String mot) {
        double res = 0;
        int df = this.indexation.dictioMots.nbDocMot(mot);
        if(df != 0){
            res = 1 + Math.log(this.indexation.dictioDocuments.nbMots() / df);
            return res;
        }
        else{
            return 0.;
        }
    }


       /**
     * Renvoie la valeur de la pondération dans le corpus
     * Cette pondération Globale est de niveau P (1+log (N-df/df)  ) 
     */

    public double ponderationGlobaleDocumentP(String mot) {
        double res = 0;
        int df = this.indexation.dictioMots.nbDocMot(mot);
        if(df != 0){
            res = 1 + Math.log(this.indexation.dictioDocuments.nbMots() - df / df);
            return res;
        }
        else{
            return 0.;
        }
    }

    /**
     * Renvoie la normalisation du document
     * Cette normalisation est de niveau N (pas de normalisation)
     */
    public int normalisationDocument() {
        return 1;
    }

    /**
     * Renvoie la valeur de la pondération locale du mot dans la requête
     * Cette pondération est de niveau l (facteur logarithmique)
     * @param mot le mot à chercher
     */
    public double ponderationLocaleRequete(String mot) {
        double res = 0;
        int val = this.recherche.val(mot, this.sortie.getName());
        if(val > 0) {
            res = 1 + Math.log(val);
        }
        return res;
    }

    /**
     * Renvoie la valeur de la pondération dans le corpus
     * Cette pondération est de niveau N (pas de pondération)
     */
    public double ponderationGlobaleRequete(String mot) {
        return 1;
    }

    /**
     * Renvoie la normalisation de la requête
     * Cette normalisation est de niveau N (pas de normalisation)
     */
    public int normalisationRequete() {
        return 1;
    }

    public void presentationFichiers(int nbResultats) throws IOException {
        System.out.println("Calcul du score en cours...");
        double[] valeurs = score(); 
        System.out.println("Calcul terminé !");
        int[] positions = new int[valeurs.length];
        for (int p = 0; p < positions.length; p++) {
            positions[p] = p;
        }
        int longueur = valeurs.length;
        int i = 0;
        BufferedWriter buffer = new BufferedWriter(new FileWriter(this.sortie, false));
        while (i != longueur && i != nbResultats) {
            // on cherche la position du max de resultats
            int position = 0;
            int j = 1;
            // parcours de tous les éléments jusqu'à la longueur
            while (j < longueur) {
                if (valeurs[j] > valeurs[position]) {
                    position = j;
                }
                j++;
            }
            // position contient la position de la plus grande valeur trouvée
            String resultat = "91" + '\t' + "Q0" + '\t' + indexation.dictioDocuments.motIndice(positions[position]) + '\t' + (i+1) + '\t' + valeurs[position] + '\t' + "91-lnn-lnn";
            buffer.write(resultat);
            buffer.newLine();
            // on retire 1 à la longueur pour mettre la dernière valeur à sa place
            longueur -= 1;
            valeurs[position] = valeurs[longueur];
            positions[position] = positions[longueur];
            i++;
        }
        System.out.println("Votre requête a été postée dans le fichier " + sortie.getPath());
        buffer.flush();
        buffer.close();
    } 
    
    

}
