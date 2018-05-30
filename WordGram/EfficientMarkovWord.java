package WordGram;

import java.util.*;
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> map;
    
    public EfficientMarkovWord(int order){
         myRandom = new Random();
         myOrder = order;
         map = new HashMap<WordGram, ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
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
    }
    
    private void buildMap(){
        for (int k = 0; k+myOrder-1< myText.length; k++){
            WordGram wg = new WordGram(myText,k, myOrder);
            //System.out.println("wordGram: "+wg.toString());
            if (!map.containsKey(wg)){
                ArrayList<String> list = new ArrayList<String>();
                String follow = "";
                if(k+myOrder+1<= myText.length){
                    follow = myText[k+myOrder];                    
                }
                //System.out.println(follow +":next if does not contain key");
                list.add(follow);
                map.put(wg, list);
            }
            else{
                ArrayList<String> list = map.get(wg);
                String follow = "";
                if(k+myOrder+1<= myText.length){
                    follow = myText[k+myOrder];
                    list.add(follow);
                }                
                //System.out.println(follow +":next if contains key");
            }            
        }
    }
    
    public void printMap(){
        if (map.size()<15){
            System.out.println(map);
        }
        System.out.println("number of keys: "+ map.size());
        int maxSize = 0;
        for (WordGram s : map.keySet()) {
            int size = map.get(s).size();
            if (size> maxSize){
                maxSize = size;
            }
        }        
        System.out.println("Biggest value is " + maxSize);
        for (WordGram s: map.keySet()){
            int size = map.get(s).size();
            if (size == maxSize){
                System.out.println("The key \""+ s.toString() + "\" with " + maxSize + " values has the maximum size value.");
            }
        }        
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        return map.get(kGram);        
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
            if (follows == null || follows.size() == 0) {
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
    public String toString(){
        return "EfficientMarkovWord of order " + myOrder;
    }
    
}
