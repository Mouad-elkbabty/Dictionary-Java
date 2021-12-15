package inf353;

import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class MainEval {

    public static void main(String[] args) throws IOException{
        if (args.length == 0 || (!args[0].equals("y") && !args[0].equals("n"))) {
            System.out.println("Veuillez entrer 'y' pour une presentation en un fichier et 'n' pour une presentation fichier par fichier");
        } else {
            Recherche recherche = new Recherche("./src/main/resources/inf353/indexation/");
            int doc = 91;
            while (doc <= 140) {
                recherche.requete(doc, 250);
                doc++;
            }
            if (args[0].equals("y")) {
                File sortie = new File("./src/main/resources/inf353/requetes/requete");
                if (!sortie.exists() || sortie.isDirectory()) sortie.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(sortie, false));
                doc = 91;
                while (doc <= 140) {
                    File fichier = new File("./src/main/resources/inf353/requetes/" + doc);
                    if (fichier.exists() && !fichier.isDirectory()) {
                        BufferedReader reader = new BufferedReader(new FileReader(fichier));
                        String res = "";
                        String ligne = reader.readLine();
                        while (ligne != null) {
                            res += ligne + "\n";
                            ligne = reader.readLine();
                        }
                        writer.write(res);
                        reader.close();
                        fichier.delete();
                    } else {
                        System.out.println("Fichier " + fichier.getName() + " manquant.");
                    }
                    doc++;
                }
                writer.flush();
                writer.close();
            }
        }

    }

}