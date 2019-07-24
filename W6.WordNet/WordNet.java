import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner; 
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private final Map<String, List<Integer>> synsets = new HashMap<String, List<Integer>>();
    private final Map<Integer, String> synsetStrings =
        new HashMap<Integer, String>();
    
    private final Digraph dag;
    
    private class Tuple<T1, T2> 
    {
        public final T1 item1;
        public final T2 item2;
 
        public Tuple(T1 item1, T2 item2)
        {
            this.item1 = item1;
            this.item2 = item2;
        }
    }
    
    public WordNet(String synsetsFileName, String hypernymsFileName)
    {
        if (synsetsFileName == null || hypernymsFileName == null) {
            throw new IllegalArgumentException("the argument is null!");
        }
           
        readSynsets(synsetsFileName);
        Tuple<Integer, List<Tuple<Integer, Integer>>> hypernyms = 
            readHypernyms(hypernymsFileName);
        
        this.dag = new Digraph(hypernyms.item1 + 1);
        for (Tuple<Integer, Integer> h : hypernyms.item2)
        {
            dag.addEdge(h.item1, h.item2);
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns()
    {
        return synsets.keySet();
    }
    
    // is the word a WordNet noun?
    public boolean isNoun(String word)
    {
        if (word == null) {
            throw new IllegalArgumentException("the argument is null!");
        }
        return synsets.containsKey(word);
    }
    
    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB)
    {
        checkIsNoun(nounA);
        checkIsNoun(nounB);
        Iterable<Integer> idsA = synsets.get(nounA);
        Iterable<Integer> idsB = synsets.get(nounB);
        
        
        SAP sap = new SAP(this.dag);
        return sap.length(idsA, idsB);
    }
    
    // a synset (second field of synsets.txt) that is 
    // the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB)
    {
        checkIsNoun(nounA);
        checkIsNoun(nounB);
        Iterable<Integer> idsA = synsets.get(nounA);
        Iterable<Integer> idsB = synsets.get(nounB);
        SAP sap = new SAP(this.dag);
        int idSAP = sap.ancestor(idsA, idsB);
        return synsetStrings.get(idSAP);       
    }
    
    private void readSynsets(String synsetsFileName)
    {
        File fileSynset = new File(synsetsFileName);
        try
        { 
            Scanner inputSynset = new Scanner(fileSynset);
            
            while (inputSynset.hasNextLine()) {
                String nextLine = inputSynset.nextLine();
                List<String> line = Arrays.asList(nextLine.split(","));
                if (line.size() >= 2)
                {
                    int id = Integer.parseInt(line.get(0));
                    String synsetString = line.get(1);
                    List<String> synsetlist = Arrays.asList(synsetString.split(" "));
                    for (String synset : synsetlist)
                    {
                        if (!synsets.containsKey(synset))
                        {
                            synsets.put(synset, new ArrayList<Integer>(Arrays.asList(id)));
                        }
                        else    
                        {
                            List<Integer> newIds = synsets.get(synset);
                            newIds.add(id);
                            synsets.put(synset, newIds);
                        }
                    }
                    synsetStrings.put(id, synsetString);
                }
            }
            inputSynset.close();
        } catch (FileNotFoundException e) { 
            System.out.println(e);
        } 
    }
    
    private Tuple<Integer, List<Tuple<Integer, Integer>>> readHypernyms(
                                                  String hypernymsFileName)
    {
        List<Tuple<Integer, Integer>> hypernyms = 
            new ArrayList<Tuple<Integer, Integer>>();  
        int numberVertices = 0;
        File fileHypernym = new File(hypernymsFileName);
        try
        { 
            Scanner inputHypernym = new Scanner(fileHypernym);
            
            while (inputHypernym.hasNextLine()) {
                String nextLine = inputHypernym.nextLine();
                List<String> line = Arrays.asList(nextLine.split(","));
                if (line.size() >= 1)
                {
                    int h1 = Integer.parseInt(line.get(0));
                    if (numberVertices < h1) numberVertices = h1;
                    for (String connection : line.subList(1, line.size()))
                    {
                        int h2 = Integer.parseInt(connection);                        
                        if (numberVertices < h2) numberVertices = h2;
                        Tuple<Integer, Integer> h =
                            new Tuple<Integer, Integer>(h1, h2);
                        hypernyms.add(h);
                    }
                }
            }
            inputHypernym.close();
        } catch (FileNotFoundException e) { 
            System.out.println(e);
        } 
        return new Tuple<Integer, List<Tuple<Integer, Integer>>>(numberVertices,
                                                                 hypernyms);
    }
     
    private void checkIsNoun(String noun)
    {
        if (!isNoun(noun)) {
            throw new IllegalArgumentException("the argument '" + noun + "' is not a noun!");
        }
    }
}