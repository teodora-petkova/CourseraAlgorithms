public class Outcast {
   private final WordNet wordnet;
   // constructor takes a WordNet object
   public Outcast(WordNet wordnet)        
   {
       this.wordnet = wordnet;
   }
   
   // given an array of WordNet nouns, return an outcast
   public String outcast(String[] nouns)   
   {
       int maxSum = Integer.MIN_VALUE;
       String nounWithMaxSum = "";
       
       for (int i = 0; i < nouns.length; i++) 
       {
           int currentSum = 0;
           for (int j = 0; j < nouns.length; j++)
           {               
               currentSum += wordnet.distance(nouns[i], nouns[j]);
           }
           if (maxSum < currentSum)
           {
               maxSum = currentSum;
               nounWithMaxSum = nouns[i];
           }
       }
       return nounWithMaxSum;
   }   
}