import java.util.List;
import java.util.ArrayList;
import junit.framework.TestCase;

public class WordNetTest extends TestCase {
    public void test_synsets() {
        WordNet wn = new WordNet(".\\W6.WordNet\\WordNetTests\\TestData\\synsets.txt", ".\\W6.WordNet\\WordNetTests\\TestData\\hypernyms.txt");
        List<String> nouns = new ArrayList<String>();
        for (String n : wn.nouns()) 
        {
            nouns.add(n); 
        }
        
         assertEquals("", 119188, nouns.size());
        
         assertEquals("", true, wn.isNoun("chlorophyll_a"));
         assertEquals("", true, wn.isNoun("chlorophyll_b"));
         assertEquals("", true, wn.isNoun("chlorophyll_c"));
         assertEquals("", true, wn.isNoun("chlorofucin"));         
        
         assertEquals("", "decade decennary decennium", wn.sap("1750s", "1770s"));
         assertEquals("", 2, wn.distance("1750s", "1770s"));
         
         assertEquals("", 17, wn.distance("Romanticism", "Swietinia_mahogani"));
         assertEquals("", "entity", wn.sap("Romanticism", "Swietinia_mahogani"));
        
         assertEquals("", 11, wn.distance("quadrangle", "mountain_devil"));
         assertEquals("", "whole unit", wn.sap("quadrangle", "mountain_devil"));
        
         assertEquals("", 10, wn.distance("Eddy", "cutlery"));
         assertEquals("", "whole unit", wn.sap("Eddy", "cutlery"));
         
         assertEquals("", 8, wn.distance("Diogenes", "Claude_Bernard"));
         assertEquals("", "person individual someone somebody mortal soul", wn.sap("Diogenes", "Claude_Bernard"));
         
         assertEquals("", 15, wn.distance("sixth", "Galeopsis_tetrahit"));
         assertEquals("", "entity", wn.sap("sixth", "Galeopsis_tetrahit"));   
        
         assertEquals("", 19, wn.distance("Gobiesox_strumosus", "capital_of_Paraguay"));
         assertEquals("", "object physical_object", wn.sap("Gobiesox_strumosus", "capital_of_Paraguay"));
    }
}