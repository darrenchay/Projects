import edu.duke.*;
import java.io.*;
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch){
        char letter = Character.toLowerCase(ch);
        char vowels = 'a'+'e'+'i'+'o'+'u';
        if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u'){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void testisVowel(char ch ){
        boolean vowel = isVowel(ch);
        if (vowel == true){
            System.out.println(ch + " is a vowel");
        }
        else{
            System.out.println(ch + " is not a vowel");
        }
    }
    
    public String replaceVowels(String phrase, char ch){
        StringBuilder line = new StringBuilder(phrase);
        for (int i = 0; i < line.length(); i++){
            char letter = line.charAt(i);
            if (isVowel(letter) == true){
                line.setCharAt(i,ch);
            }
            
        }
        return line.toString();
    }
    
    public void testReplaceVowels(String phrase, char ch){
        String line = replaceVowels(phrase, ch);
        System.out.println(line);
        System.out.println(phrase);
    }
    
    public String emphasize(String phrase, char ch){
        String lowerphrase = phrase.toLowerCase();
        StringBuilder line = new StringBuilder(lowerphrase);
        StringBuilder lines = new StringBuilder(phrase);
        for (int i = 0; i < line.length(); i++){
            char letter = line.charAt(i);
            if (letter == ch && ((i+1)%2) == 0){
                lines.setCharAt (i,'+');
            }
            if(letter == ch && ((i+1)%2) !=0){
                lines.setCharAt (i,'*');
            }
        }
        return lines.toString();
    }
    
    public void testEmphasize(String phrase, char ch){
        String st = emphasize(phrase, ch);
        System.out.println(phrase);
        System.out.println(st);
    }
}
