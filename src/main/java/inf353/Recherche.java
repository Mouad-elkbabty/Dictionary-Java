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
    }

    /**
     * Trie les résultats du meilleur au pire score et affiche les resultat
     */
    public void presentation() throws IOException {
        int[] valeurs = score(); 
        int[] positions = new int[valeurs.length];
        for (int p = 0; p < positions.length; p++) {
            positions[p] = p;
        }
        for (int i = 0; i < valeurs.length; i++) {
            for (int j = i + 1; j < valeurs.length; j++) {
                if (valeurs[i] < valeurs[j]) {
                    int v = valeurs[i];
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
    public int[] score() throws IOException {
        int[] scores = new int[indexation.dictioDocuments.nbMots()];
        int i = 0;
        while (i != indexation.dictioDocuments.nbMots()) { // on recherche parmis tous les documents de notre indexation
            CelluleMatrice cc = requete.matriceOccurences.T[0];
            String document = indexation.dictioDocuments.motIndice(i);
            while (cc != null) { // on recherche parmis tous les mots de notre requête
                String mot = requete.dictioMots.motIndice(cc.ind);
                int valeur = indexation.val(mot, document);
                if (valeur != 0) {
                    scores[i] = scores[i] + valeur * cc.elt;
                }
                cc = cc.suiv;
            }
            i++;
        }
        return scores;
    }

}
