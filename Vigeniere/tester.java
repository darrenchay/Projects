package Vigeniere;
import edu.duke.*;
import java.util.*;
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tester {
    public void testCeasarCracker(){
        FileResource fr = new FileResource();
        String message  = fr.asString();
        CaesarCracker cc = new CaesarCracker('a');
        String decrypted = cc.decrypt(message);
        System.out.println(decrypted);
    }
    
    public void testSliceString(){
        VigenereBreaker vb = new VigenereBreaker();
        String slice = vb.sliceString("abcdefghijklm", 4,5);
        System.out.println(slice);
    }
    public void testTryKeyLength(){
        FileResource fr = new FileResource();
        String message  = fr.asString();
        VigenereBreaker vb = new VigenereBreaker();
        int[] key = vb.tryKeyLength(message, 4, 'e');
        for (int k = 0; k< key.length; k++){
            System.out.println(key[k]);
        }
    }
    
    public void tests(){
        FileResource fr = new FileResource();
        String message  = fr.asString();
        VigenereBreaker vb = new VigenereBreaker();
        int[] key = vb.tryKeyLength(message, 38, 'e');
        FileResource dictionary = new FileResource("dictionaries/English");
        HashSet<String> dict = vb.readDictionary(dictionary);
        VigenereCipher vc = new VigenereCipher(key);
        String currdecrypted = vc.decrypt(message);
        int count = vb.countWords(currdecrypted,dict);
        System.out.println("no:"+count);
    }
    
    public void testbreakVigenere(){
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }
    
    public void testMostCommon(){
        FileResource dictionary = new FileResource();
        VigenereBreaker vb = new VigenereBreaker();
        HashSet<String> dict = vb.readDictionary(dictionary);
        char c = vb.mostCommonCharIn(dict);
        System.out.println(c);
    }
}
