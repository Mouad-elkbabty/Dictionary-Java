package inf353;

import java.io.FileReader;
import java.io.IOException;

public class LecteurDocumentNaif implements AccesSequentielModele1<String> {
    
        
	FileReader fileReader;
    String texte;
    String mot;
    int contenu;
	char separateurs[] = {',', ';', '?', '.', '!', ':', ' ', '\t', '\n', '{', '}', '(', ')', '"', '&', '-', '_', '\'', '/','\r'};
	int i;


    public LecteurDocumentNaif(String file){
        
		try {
            i = 0;
			fileReader = new FileReader(file);
			contenu = fileReader.read();		
		
		}catch (IOException e) {
			e.printStackTrace();
		}

    }

    public void demarrer(){
        try {
				
			if(estSeparateur((char) contenu)){
				contenu = fileReader.read();
			}

		}catch (IOException e) {
			e.printStackTrace();
		}
    }    
	
    public void avancer(){
        try {

        	if(estSeparateur((char) contenu)){
            
				contenu = fileReader.read();
			}
				
			mot = "";
			while(!estSeparateur((char) contenu)){
				mot = mot + contenu;
				contenu = fileReader.read();
			}       	 
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
    }

    public boolean estSeparateur(char c){
    	int j = 0;
    	while(j != separateurs.length && separateurs[j] != c){
    		j++;
    	}
    	return j != separateurs.length;
    }

    public String elementCourant(){
        return mot;
    }

    public boolean finDeSequence(){
        return mot==null||mot.equals("");
    }


}
