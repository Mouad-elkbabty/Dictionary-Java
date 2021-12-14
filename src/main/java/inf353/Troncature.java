package inf353;

import org.tartarus.snowball.*;
import org.tartarus.snowball.ext.*;

public class Troncature {
    SnowballStemmer stemmer = new frenchStemmer();

    public String stem(String word) {
        stemmer.setCurrent(word);
        stemmer.stem();    
        return stemmer.getCurrent();
    }
}
