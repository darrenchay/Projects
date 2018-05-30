package Gladlib2;
import edu.duke.*;
import java.util.*;
import java.io.*;

/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CodonCount {
    private HashMap <String, Integer> myMap;
    public CodonCount(){
        myMap = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna){
        myMap.clear();
        int index = 0;
        for (int k = start; k<dna.length(); k= k + 3){
            if (k+3 > dna.length()){
                break;
            }
            String codon = dna.substring(k, k+3); 
            if (myMap.containsKey(codon)){
                int value = myMap.get(codon);
                myMap.put(codon, value +1 );
            }
            else{
                myMap.put(codon,1);
            }
        }
        System.out.println(myMap);
    }
    
    private String getMostCommonCodon(){
        int largest = 0;
        String codon = "";
        for (String s : myMap.keySet()){
            int i = myMap.get(s);
            if (i>largest){
                largest = i;
                codon = s;
            }
        }
        //System.out.println(codon);
        return codon;
    }
    
    public void printCodon(int start, int end){
        for (String s : myMap.keySet()){
            if (myMap.get(s)>= start && myMap.get(s)<= end){
                System.out.println(s + "\t" + myMap.get(s));
            }
        }
    }
    
    public void test(int start, int end){
        FileResource fr = new FileResource();
        String temp = fr.asString();
        String dna = (temp.toUpperCase()).trim();
        for (int k = 0; k<3 ; k++){
            buildCodonMap(k,dna);
            String mostCommon = getMostCommonCodon();
            System.out.println("Reading frame starting with "+ k +" results in " + myMap.size() + " unique codon sets");
            System.out.println("where the most common codon was " + mostCommon + " with a count of " +myMap.get(mostCommon));
            System.out.println("Codons between " + start + " and " + end + " are" );
            printCodon(start, end);
        }
    }
}
