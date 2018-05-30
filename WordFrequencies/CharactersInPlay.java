package WordFrequencies;

import edu.duke.*;
import java.util.*;
import java.io.*;
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> freq;
    
    public CharactersInPlay(){
        names = new ArrayList<String>();
        freq = new ArrayList<Integer>();
    }
    
    public void update(String person){
            String persons = person.toLowerCase();
            int index = names.indexOf(persons);
            if (index == -1){
                names.add(persons);
                freq.add(1);
            }
            else{             
                    int value = freq.get(index);
                    freq.set(index,value+1);
             }
    }
    
    public void findAllCharacters(){
        names.clear();
        freq.clear();
        FileResource fr = new FileResource();
        for (String words : fr.lines()){
            int index = words.indexOf(".");
            if(index != -1){
                String name = words.substring(0,index);
                update(name);
            }
        }
        
    }
    
    public void tester(){
        findAllCharacters();
        for (int k=0; k< names.size(); k++){
            if (freq.get(k)>1){
                System.out.println(names.get(k) +" spoke "+ freq.get(k) + " times");
            }
        }
        charactersWithNumParts(10, 15);
    }
    
    public void  charactersWithNumParts(int num1, int num2){
        for (int k=0; k< names.size(); k++){
            int number = freq.get(k);
            if (number >= num1 && number<= num2){
                System.out.println(names.get(k) +" spoke "+ freq.get(k) + " times, which is between "+num1 +" and " + num2 );
            }
            
        }
    }
}
