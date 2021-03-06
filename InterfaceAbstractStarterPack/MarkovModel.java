package InterfaceAbstractStarterPack;


/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.io.*;
public class  MarkovModel extends AbstractMarkovModel {
    private int N;
    public MarkovModel(int num) {
        myRandom = new Random();
        N = num;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-N);
        String key = myText.substring(index,index+N);
        sb.append(key);
        for(int k=0; k < numChars -N; k++){
            ArrayList<String> list = getFollows(key);
            if(list.size() == 0){
                break;
            }
            int rand = myRandom.nextInt(list.size());
            String next = list.get(rand);
            sb.append(next);
            key = key.substring(1)+next;
        }
        
        return sb.toString();
    }
    
    public String toString(){
        return "MarkovModel of order " + N;
    }
}
