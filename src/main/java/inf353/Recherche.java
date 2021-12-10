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
        this.indexation = new Indexation(chemin);
        this.requete = new Indexation();
        this.evaluer();
    }

    /**
     * Évaluer l'entrée donnée
     */
    public void evaluer() throws IOException {
        this.requete.dictioMots = new DictionnaireHash(1);
        this.requete.dictioDocuments = new DictionnaireHash(1);
        this.requete.ajouterDocument(this.recherche.getPath());
        System.out.println(this.requete.dictioDocuments.motIndice(0));
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
        for (int i = 0; i < valeurs.length; i++) {
            for (int j = i + 1; j < valeurs.length; j++) {
                if (valeurs[i] < valeurs[j]) {
                    double v = valeurs[i];
                    valeurs[i] = valeurs[j];
                    valeurs[j] = v;
                    int p = positions[i];
                    positions[i] = positions[j];
                    positions[j] = p;
                }
            }
        }
        BufferedWriter buffer = new BufferedWriter(new FileWriter(recherche, true));
        buffer.newLine();
        buffer.newLine();
        System.out.println("Voici les résultats correspondant à votre requête :");
        for (int r = 0; r < 10 && r < positions.length; r++) {
            String resultat = (r+1) + ". " + indexation.dictioDocuments.motIndice(positions[r]) + " (score: " + valeurs[r] + ")";
            System.out.println(resultat);
            buffer.write(resultat);
            buffer.newLine();
        }
        System.out.println("Votre requête a été postée dans le fichier " + recherche.getName());
        buffer.flush();
        buffer.close();
    } 

    /**
     * Calcule le score des documents en fonction de l'Indexation et de la recherche
     */
    public double[] score() throws IOException {
        double[] scores = new double[indexation.dictioDocuments.nbMots()];
        int i = 0;
        while (i != indexation.dictioDocuments.nbMots()) { // on parcours tous les documents de notre indexation
            CelluleMatrice cc = requete.matriceOccurrences.T[0];
            String document = indexation.dictioDocuments.motIndice(i);
            while (cc != null) { // on parcours tous les mots de notre requete
                String mot = requete.dictioMots.motIndice(cc.ind);
                double pondLocaleDoc = facteurLogDoc(mot, document);
                if (pondLocaleDoc > 0) {
                    scores[i] += pondLocaleDoc * ponderationLocaleRequete(mot);
                }
                cc = cc.suiv;
            }
            scores[i] *= (ponderationGlobaleDocument("") * ponderationGlobaleRequete()) / (normalisationDocument() * normalisationRequete());
            i++;
        }
        return scores;
    }

    /**
     * Renvoie la valeur de la pondération du mot dans le document
     * Cette pondération est de niveau M (facteur fréquentiel normalisé)
     * @param mot le mot à chercher
     * @param document le document à chercher
     */
    public double ponderationLocaleDocument(String mot, String document) {
        int val = indexation.val(mot, document);
        double maxOccurrence = indexation.maxOccurrence(document);
        return val / maxOccurrence;
    }


    /**
     * Renvoie la valeur de la pondération du mot dans le document
     * Cette pondération est de niveau l (facteur Logarithmique)
     * @param mot le mot à chercher
     * @param document le document à chercher
     */
    public double facteurLogDoc(String mot, String document){
        double res =0;
        if(indexation.val(mot, document) != 0)
        {
            res = 1+ Math.log(indexation.val(mot, document));
        }
        return res;
    }

    
    /**
     * Renvoie la valeur de la pondération dans le corpus
     * Cette pondération est de niveau N (pas de pondération)
     */
    public double ponderationGlobaleDocument(String mot) {

        double idf = 0.;
        int nb = indexation.dictioMots.nbDocMot(mot);

        idf = Math.log(indexation.dictioDocuments.nbMots()/nb);
        return idf;
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
     * Cette pondération est de niveau M (facteur fréquentiel normalisé)
     * @param mot le mot à chercher
     */
    public double ponderationLocaleRequete(String mot) {
        int val = requete.val(mot, this.recherche.getName());
        double maxOccurrence = requete.maxOccurrence(this.recherche.getName());
        return val / maxOccurrence;
    }

    /**
     * Renvoie la valeur de la pondération dans le corpus
     * Cette pondération est de niveau N (pas de pondération)
     */
    public int ponderationGlobaleRequete() {
        return 0;

    }

    /**
     * Renvoie la normalisation de la requête
     * Cette normalisation est de niveau N (pas de normalisation)
     */
    public int normalisationRequete() {
        return 1;
    }

}
