package WordGram;
import java.util.*;

/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    public MarkovWord(int order){
         myRandom = new Random();
         myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
           
    private int indexOf(String[]words, WordGram target, int start){
        ArrayList<WordGram> list = new ArrayList<WordGram>();
        int size = myOrder;
        for(int index = 0; index <= words.length - size; index += 1) {
            WordGram wg = new WordGram(words,index,size);
            list.add(wg);
        }
        for (int k = start; k<list.size();k++){
            if (list.get(k).equals(target)){
                return k;
            }
        }
        return -1;
    }
    
    public void testIndexOf( int start){
        String text = "this is just a test yes this is just a simple test";        
        String[] words = text.split("\\s+");
        WordGram word = new WordGram(words, 0, myOrder);       
        int idx = indexOf(words, word, start);
        System.out.println(word + "\t"+idx);
        HashMap<WordGram , String> wordabc= new HashMap<WordGram, String>();
        wordabc.put(word, "test");
        wordabc.get(word);
        System.out.print (        wordabc.get(word));
        for(WordGram wg: wordabc.keySet()){
            
        }
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int start = 0;
        while(start< myText.length-myOrder){            
            int pos = indexOf(myText, kGram, start);
            if(pos == -1){
                break;
            }
            if (pos+myOrder>= myText.length){
                break;
            }
            //System.out.println(pos);
            String next = myText[pos+myOrder];
            //System.out.println(next);
            follows.add(next);
            start = pos+1;
        }
        return follows;
    }      
    
    public String toString(){
        return "MarkovWord of order " + myOrder;
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram word = new WordGram(myText, index, myOrder); 
        //System.out.println(word +":word1");
        sb.append(word.toString());
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(word);
            //System.out.println(word +"\t"+ follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            //System.out.println(sb.toString().trim()+":newword");
            word = word.shiftAdd(next);
        }        
        return sb.toString().trim();
    }
}
