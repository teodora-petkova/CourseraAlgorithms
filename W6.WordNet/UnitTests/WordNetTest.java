import java.util.List;
import java.util.ArrayList;
import junit.framework.TestCase;

public class WordNetTest extends TestCase {
    public void test1() {
        WordNet wn = new WordNet(".\\W6.WordNet\\WordNetTests\\TestData\\synsets.txt", ".\\W6.WordNet\\WordNetTests\\TestData\\hypernyms.txt");
        List<String> nouns = new ArrayList<String>();
        for (String n : wn.nouns()) 
        {
            //System.out.println(n);
            nouns.add(n); 
        }
        //assertEquals("", 119188, nouns.size());
        assertEquals("", 17, wn.distance("Romanticism", "Swietinia_mahogani"));
        assertEquals("", "entity", wn.sap("Romanticism", "Swietinia_mahogani"));
        
        //assertEquals("", 11, wn.distance("quadrangle", "mountain_devil"));
        //assertEquals("", null, wn.sap("quadrangle", "mountain_devil"));
        
        assertEquals("", true, wn.isNoun("chlorophyll_a"));
        assertEquals("", 19, wn.distance("Gobiesox_strumosus", "capital_of_Paraguay"));
        assertEquals("", "object physical_object", wn.sap("Gobiesox_strumosus", "capital_of_Paraguay"));        
        //assertEquals("", 10, wn.distance("Eddy", "cutlery"));
        //assertEquals("", 15, wn.distance("sixth", "Galeopsis_tetrahit"));
        assertEquals("", 8, wn.distance("Diogenes", "Claude_Bernard"));
        assertEquals("", "person individual someone somebody mortal soul", wn.sap("Diogenes", "Claude_Bernard"));
        
    }
}