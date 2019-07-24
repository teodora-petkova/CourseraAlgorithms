import junit.framework.TestCase;

public class OutcastTest extends TestCase {
    public void test() {
        WordNet wordnet = new WordNet(".\\W6.WordNet\\WordNetTests\\TestData\\synsets.txt", ".\\W6.WordNet\\WordNetTests\\TestData\\hypernyms.txt");
        Outcast out = new Outcast(wordnet);
        
        assertEquals("", "Turing", out.outcast(new String[] {"Turing", "von_Neumann"}));
        
        assertEquals("", "playboy", out.outcast(new String[] {"Banti's_disease", "hyperadrenalism", "German_measles", "gargoylism", "Q_fever", "amebiosis", "anthrax", "playboy"}));
        
        assertEquals("", "cabbage", out.outcast(new String[] {"apple", "orange", "banana", "grape", "strawberry", "cabbage", "mango", "watermelon"}));
    }
}