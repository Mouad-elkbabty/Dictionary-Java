package inf353;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Recherche {

    File recherche;
    Indexation indexation;
    Indexation requete;

    /**
     * Crée une Recherche
     * @param requete La requête faite
     * @param chemin Le chemin vers l'Indexation à utiliser
     */
    public Recherche(String[] entree, String chemin) throws IOException {
        File dossier = new File("./src/main/resources/inf353/");
        if (!dossier.isDirectory()) {
            dossier.mkdir();
        }
        int numero = 0;
        if (dossier.list() != null) {
            numero = dossier.list().length;
        }
        this.recherche = new File("./src/main/resources/inf353/requete-" + numero);
        BufferedWriter buffer = new BufferedWriter(new FileWriter(recherche.getPath(), false));
        String ligne = "";
        int i = 0;
        while (i < entree.length){
            ligne += entree[i] + " ";
            i++;
        }
        buffer.write(ligne);
        buffer.flush();
        buffer.close();
        System.out.println("Chargement de l'indexation en cours...");
        this.indexation = new Indexation(chemin);
        System.out.println("Chargement termine !");
        this.requete = new Indexation(1, 1, 1);
        this.requete.ajouterDocument(this.recherche.getPath());
    }
    /**
     * Trie les résultats du meilleur au pire score et affiche les resultat
     */
    public void presentation() throws IOException {
        System.out.println("Calcul du score en cours...");
        double[] valeurs = score(); 
        System.out.println("Calcul terminé !");
        int[] positions = new int[valeurs.length];
        for (int p = 0; p < positions.length; p++) {
            positions[p] = p;
        }
        int longueur = valeurs.length;
        int i = 0;
        BufferedWriter buffer = new BufferedWriter(new FileWriter(recherche, true));
        buffer.newLine();
        buffer.newLine();
        System.out.println("Voici les résultats correspondant à votre requête :");
        while (i < longueur && i != 10) {
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
            String resultat = (i+1) + ". " + indexation.dictioDocuments.motIndice(positions[position]) + " (score: " + valeurs[position] + ")";
            System.out.println(resultat);
            buffer.write(resultat);
            buffer.newLine();
            // on retire 1 à la longueur pour mettre la dernière valeur à sa place
            longueur -= 1;
            valeurs[position] = valeurs[longueur];
            positions[position] = positions[longueur];
            i++;
        }
        System.out.println("Votre requête a été postée dans le fichier " + recherche.getName());
        buffer.flush();
        buffer.close();
    } 
    /**
     * Calcule le score des documents en fonction de l'Indexation et de la recherche
     */
    public double[] score() throws IOException {
        // un element pour un document
        double[] scores = new double[indexation.dictioDocuments.nbMots()];
        // on veut parcourir tous les mots de notre requete
        CelluleDictio cc = this.requete.dictioMots.T[0];
        // tant qu'on a pas traite tous les mots
        while (cc != null) {
            System.out.println("Calcul du score avec le mot " + cc.elt);
            // i = index du document
            int i = 0;
            double ponderationLocaleRequete = this.ponderationLocaleRequete(cc.elt);
            // tant qu'on a pas traite tous les scores
            while (i != scores.length) {
                String document = this.indexation.dictioDocuments.motIndice(i);
                double ponderationLocaleDocument = this.ponderationLocaleDocument(cc.elt, document);
                double ponderationGlobaleDocument = ponderationGlobaleDocument(cc.elt);
                scores[i] += ponderationLocaleDocument * ponderationLocaleRequete * ponderationGlobaleDocument * ponderationGlobaleRequete() / (normalisationDocument() * normalisationRequete());
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
    public double ponderationLocaleDocument(String mot, String document) {
        double res = 0;
        int val = this.indexation.val(mot, document);
        if(val > 0) {
            res = 1 + Math.log(val);
        }
        return res;
    }
    /**
     * Renvoie la valeur de la pondération dans le corpus
     * Cette pondération est de niveau N (pas de pondération)
     */
    public double ponderationGlobaleDocument(String mot) {
        double res = 0;
        int df = this.indexation.dictioMots.nbDocMot(mot);
        res = 1 + Math.log(this.indexation.dictioDocuments.nbMots() / df);
        return res;
    }


    public double ponderationGlobaleDocument1(String mot) {
        double res = 0;
        int df = this.indexation.dictioMots.nbDocMot(mot);
        res = 1 + Math.log(this.indexation.dictioDocuments.nbMots() - df / df);
        return res;
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
        int val = this.requete.val(mot, this.recherche.getName());
        if(val > 0) {
            res = 1 + Math.log(val);
        }
        return res;
    }

    /**
     * Renvoie la valeur de la pondération dans le corpus
     * Cette pondération est de niveau N (pas de pondération)
     */
    public int ponderationGlobaleRequete() {
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
        BufferedWriter buffer = new BufferedWriter(new FileWriter(recherche, false));
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
        System.out.println("Votre requête a été postée dans le fichier " + recherche.getName());
        buffer.flush();
        buffer.close();
    } 
    
    

}
