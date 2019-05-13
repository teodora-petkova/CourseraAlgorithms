import junit.framework.TestCase;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;

public class SAPTest extends TestCase {

    private SAP getSapFromFile(String path)
    {
        In in = new In(path);
        Digraph G = new Digraph(in);
        return new SAP(G);
    }
    
    // check length() and ancestor() on fixed digraphs (test1 - test7)
    public void test1() {
        SAP sap = getSapFromFile(".\\W6.WordNet\\WordNetTests\\TestData\\digraph1.txt");
        assertEquals("", 0, sap.length(3, 3));
    }
    
    public void test2() {
        SAP sap = getSapFromFile(".\\W6.WordNet\\WordNetTests\\TestData\\digraph2.txt");
        assertEquals("", 2, sap.length(1, 3));
        assertEquals("", 2, sap.length(3, 1));
        assertEquals("", 4, sap.length(2, 0));
        assertEquals("", 0, sap.ancestor(2, 0));
    }
    
    public void test3() {
        SAP sap = getSapFromFile(".\\W6.WordNet\\WordNetTests\\TestData\\digraph3.txt");
        assertEquals("", 3, sap.length(7, 11));
        assertEquals("", 5, sap.length(14, 7));
    }
    
    public void test4() {
        SAP sap = getSapFromFile(".\\W6.WordNet\\WordNetTests\\TestData\\digraph4.txt");
        assertEquals("", 1, sap.length(0, 8));
        assertEquals("", 3, sap.length(1, 4));
    }
    
    public void test5() {
        SAP sap = getSapFromFile(".\\W6.WordNet\\WordNetTests\\TestData\\digraph5.txt");
        assertEquals("", 0, sap.length(10, 10));
        assertEquals("", 10, sap.ancestor(10, 10));
        assertEquals("", 17, sap.length(21, 15));
    }
    
    public void test6() {
        SAP sap = getSapFromFile(".\\W6.WordNet\\WordNetTests\\TestData\\digraph6.txt");
        assertEquals("", 3, sap.length(6, 4));
        assertEquals("", 4, sap.ancestor(6, 4));
        assertEquals("", 5, sap.length(5, 0));
    }
    
    public void test7() {
        SAP sap = getSapFromFile(".\\W6.WordNet\\WordNetTests\\TestData\\digraph9.txt");
        assertEquals("", 1, sap.length(4, 1));
        assertEquals("", 1, sap.length(3, 0));
    }
}