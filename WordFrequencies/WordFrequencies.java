package WordFrequencies;

import edu.duke.*;
import java.util.*;
import java.io.*;
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String s : fr.words()){
            String word = s.toLowerCase();
            int index = myWords.indexOf(word);
            if (index == -1){
                myWords.add(word);
                myFreqs.add(1);
            }
            else{
                int value = myFreqs.get(index);
                myFreqs.set(index, value+1);
            }
        }
    }
    
    public int findIndexOfMax(){
        int max =0;
        int maxIndex = 0;
        for (int k=0; k<myFreqs.size(); k++){
            int currVal = myFreqs.get(k);
            if(currVal > max){
                max = currVal;
                maxIndex = k;
            }
        }
        return maxIndex;
    }
    
    
    public void tester(){
        findUnique();
        System.out.println("# of unique words is: " + myFreqs.size());
        //for (int k=0; k<myWords.size(); k++){
        //    System.out.println(myFreqs.get(k) +"\t" + myWords.get(k));
        //}
        int index = findIndexOfMax();
        System.out.println("The word \"" +myWords.get(index) + "\" occuring " + myFreqs.get(index)+ " times is the word which occurs the most often ");
    }
}
