import edu.duke.*;
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    public void countWordLengths(FileResource resource, int[]counts){ 
        for (String word : resource.words()){
            int length = findLength(word);
            for (int k = 0; k < counts.length; k++){
                if (k == length){
                    counts[k] += 1;
                }
            }
            
        }
    }
    
    public int findLength(String word){
        StringBuilder s = new StringBuilder(word);
        int length = s.length();
        if ( Character.isLetter(s.charAt(length -1) )== true && Character.isLetter(s.charAt(0)) == true){
            return length;
        }
        if (( Character.isLetter(s.charAt(length -1) )== true && Character.isLetter(s.charAt(0)) == false) || ( Character.isLetter(s.charAt(length-1) )== false && Character.isLetter(s.charAt(0)) == true)) {
           length = length - 1;
        }
        if ( Character.isLetter(s.charAt(length -1 ) )== false && Character.isLetter(s.charAt(0)) == false){
            length -=  2;
        }
        return length;
    }
    
    public int indexofMax(int[]values){
        int longest = 0;
        int value = 0;
        for (int k = 0; k<values.length; k++){
            int currVal = values[k];
            if (currVal > longest){
                longest  = currVal;
                value=k;
            }
        }
        return value;
    }
    
    public void testCountWordLengths(){
        FileResource fr = new FileResource("errors (1).txt");
        int counts[] = new int[31]; 
        countWordLengths(fr, counts);
        int longest = indexofMax(counts);
        for (int k = 0; k<counts.length; k++){
            System.out.println(counts[k] + " words of length " + k);
        }
        System.out.println(longest);
        
    }
}
    
