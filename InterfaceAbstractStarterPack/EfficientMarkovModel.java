package InterfaceAbstractStarterPack;

import java.util.*;
import edu.duke.*;
import java.io.*;
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EfficientMarkovModel extends AbstractMarkovModel {
    private int N;
    private HashMap<String, ArrayList<String>> map; 
    public EfficientMarkovModel(int num) {
        map = new HashMap<String, ArrayList<String>>();
        myRandom = new Random();
        N = num;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
        buildMap();
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
            ArrayList<String> list = getNewFollows(key);
            //System.out.println(key + "\t" + list);
            
            if(!map.containsKey(key)){
                break;
            }
            if(list.size() == 0){
                break;
            }
            int rand = myRandom.nextInt(list.size());
            String next = list.get(rand);
            sb.append(next);
            key = key.substring(1)+next;
            //System.out.println(key + "-next key" );
        }        
        return sb.toString();
    }
    
    public String toString(){
        return "EfficientMarkovModel of order " + N;
    }
    
    public void buildMap(){
        for(int pos = 0 ; pos + N <= myText.length();pos ++){
            String key = myText.substring(pos, pos+N);
            //System.out.print(key + "\t");
            if (! map.containsKey(key)){
                ArrayList<String> list = new ArrayList<String>();
                if ( pos+N+1<=myText.length()){
                    String follow = myText.substring(pos+N,pos+1+N);
                    //System.out.println(follow);
                    list.add(follow);
                }
                map.put(key, list);
            }
            else{
                ArrayList<String> list = map.get(key);
                if ( pos+N+1<=myText.length()){
                    String follow = myText.substring(pos+N,pos+1+N);
                    //System.out.println(follow);
                    list.add(follow);
                }            
            }
            //System.out.println(myText.length()+"-textlength, pos-" + pos);
        }
    }
    
    public void printMap(){
        //System.out.println(map);
        System.out.println("number of keys: "+ map.size());
        int maxSize = 0;
        for (String s : map.keySet()) {
            int size = map.get(s).size();
            if (size> maxSize){
                maxSize = size;
            }
        }        
        System.out.println("Biggest value is " + maxSize);
        for (String s: map.keySet()){
            int size = map.get(s).size();
            if (size == maxSize){
                System.out.println("The key "+ s + " with " + maxSize + " values has the maximum size value.");
            }
        }        
    }
    
    public ArrayList<String> getNewFollows(String key){
        return map.get(key);
    }
}
