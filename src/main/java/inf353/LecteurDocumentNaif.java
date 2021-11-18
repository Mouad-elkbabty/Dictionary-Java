package inf353;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LecteurDocumentNaif implements AccesSequentielModele1<String> {
    
	File fichier;
	FileReader fileReader;
	int caractereLu;
    String mot;
	static char[] separateurs = {',', ';', '?', '.', '!', ':', ' ', '\t', '\n', '{', '}', '(', ')', '"', '&', '-', '_', '\'', '/','\r'};

	/**
	 * Construit un LecteurDocumentNaif
	 * @param nomDuFichier le chemin vers le fichier
	 * @throws IOException
	 */
    public LecteurDocumentNaif(String nomDuFichier) throws IOException {
		this.fichier = new File(nomDuFichier);
        if (!this.fichier.exists() || !this.fichier.isFile()) {
            throw new FileNotFoundException("Aucun fichier du nom de " + nomDuFichier + " n'a été trouvé.");
        }
    }

	/**
	 * Démarrer le LecteurDocumentNaif, se place sur le premier mot
	 */
    public void demarrer() throws IOException {
		this.fileReader = new FileReader(this.fichier.getPath());
		this.caractereLu = this.fileReader.read();
		this.avancer();
    }    
	
	/**
	 * Avance vers le prochain mot
	 */
    public void avancer() throws IOException {
		while (estSeparateur((char) this.caractereLu)) {
			this.caractereLu = this.fileReader.read();
		}
		mot = "";
		while (this.caractereLu != -1 && !estSeparateur((char) this.caractereLu)) {
			this.mot += Character.toLowerCase((char) this.caractereLu);
			this.caractereLu = this.fileReader.read();
		}
    }

	/**
	 * Renvoie vrai si c appartient à la liste des seperateurs
	 * @param c le caractère à tester
	 */
    public static boolean estSeparateur(char c) {
    	int j = 0;
    	while (j != separateurs.length && separateurs[j] != c) {
    		j++;
    	}
    	return j != separateurs.length;
    }

	/**
	 * Renvoie le mot actuellement lu
	 */
    public String elementCourant() {
        return this.mot;
    }

	/**
	 * Renvoie vrai si la fin du document est atteinte
	 */
    public boolean finDeSequence(){
        return this.mot != null && this.mot.equals("");
    }

}
