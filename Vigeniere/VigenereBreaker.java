package Vigeniere;

import java.util.*;
import edu.duke.*;
import java.io.*;
public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slice = new StringBuilder();
        for (int k = whichSlice ; k< message.length(); k += totalSlices){
            char c = message.charAt(k);
            slice.append(c);
        }
        return slice.toString() ;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        ArrayList<String> Slices = new ArrayList<String>();
        for (int k = 0; k<klength; k++){
            String slice = sliceString(encrypted, k, klength);
            Slices.add(slice);
        }
        //for (int k = 0; k<Slices.size(); k++){            
        //    System.out.println(Slices.get(2));
        // }
        for (int k = 0; k<Slices.size(); k++){
            String slice = Slices.get(k);
            int keys = cc.getKey(slice);
            key[k] = keys;
        }
        //System.out.print(key[4]);
        return key;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();
        for (File f : dr.selectedFiles()){
            FileResource dictionary = new FileResource(f);
            HashSet<String> dict = readDictionary(dictionary);
            String name = f.getName();
            map.put(name, dict);
        }
        String decrypted = breakForAllLangs(message, map);
        System.out.println(decrypted.substring(0,decrypted.length()));
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> words = new HashSet<String>();
        for (String line : fr.lines()){
            String word = line.toLowerCase();
            words.add(word);
        }
        return words;
    }
    
    public int countWords(String message, HashSet<String> dict){
        int count = 0;
        for (String w : message.split("\\W+")){
            String words = w.toLowerCase();
            if (dict.contains(words)){
                count += 1;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dict){
        int max =0;
        int keylength = 0;
        String decrypted = "";
        int[] thekey = new int[10000];
        char mostCommon = mostCommonCharIn(dict);
        for (int k = 0; k<100 ; k++){
            int[] currkey = tryKeyLength(encrypted, k+1, mostCommon);
            VigenereCipher vc = new VigenereCipher(currkey);
            String currdecrypted = vc.decrypt(encrypted);
            int count = countWords(currdecrypted, dict); 
            //System.out.println(count);
            if(count > max){
                max = count;
                keylength = k+1;
                decrypted = currdecrypted;
                thekey = currkey;
            }
        }
        System.out.println("max for that language:"+max);
        System.out.println("keylength that lang:"+ keylength);
        for(int i = 0; i <keylength; i++){
                System.out.print(thekey[i] +"," );
            }
        return decrypted;
    }
    
    public char mostCommonCharIn(HashSet<String> dict){
        HashMap<String, Integer> chars = new HashMap<String, Integer>();
        char mostCommon = ' ';
        for(String s: dict){
            for (int k = 0; k< s.length(); k++){
                String w = s.substring(k,k+1);
                if (! chars.containsKey(w)){
                    chars.put(w,1);
                }
                else{
                    chars.put(w, chars.get(w) +1);
                }
            }
        }
        int max = 0;
        String common = "";
        for (String s: chars.keySet()){
            int curr = chars.get(s);
            if (curr> max){
                max = curr;
                common = s;
            }
        }
        //System.out.println(common);
        StringBuilder sb = new StringBuilder(common);
        char c = sb.charAt(0);
        mostCommon = Character.toLowerCase(c);
        return mostCommon;
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>>languages){
        int max = 0;
        String decrypt = "";
        String lang = "";
        for (String s : languages.keySet()){
            HashSet<String> language = languages.get(s);
            String decrypted = breakForLanguage(encrypted, language);
            int count = countWords(decrypted, language);
            if (count> max){
                max = count;
                lang = s;
                decrypt = decrypted;
            }        
        }
        System.out.println("max for all lang:"+max);
        System.out.println("language max = "+lang); 
        return decrypt;
        }
}
