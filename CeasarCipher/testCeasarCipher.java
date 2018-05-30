import edu.duke.*;
import java.io.*;
/**
 * Write a description of testCeasarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class testCeasarCipher {
    public String breakCeasarCipher(String input){
        int freq[] = countletters(input);
        int maxIndex = maxIndex(freq);
        int key = maxIndex - 4;
        if (maxIndex < 4){
            key = 26-  (4-maxIndex);
        }
        Ceasar cc = new Ceasar(key);
        String decrypted = cc.decrypt(input);
        return decrypted;
    }
    
    private int[] countletters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int count[] = new int[26];
        for (int k = 0; k< message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int index = alph.indexOf(ch);
            if (index != -1){
                count[index] += 1;
            }
        }
        return count;
    }
    
    private int maxIndex(int freq[]){
        int largest = 0;
        int count = 0;
        for (int i = 0; i<freq.length; i++){
            int current = freq[i];
            if (current > count){
                largest = i;
                count =current;
            }
        }
        return largest;
    }
    
    public void simpleTests(){
        FileResource fr = new FileResource("small.txt");
        String msg = fr.asString();
        Ceasar cc = new Ceasar(17);
        String encrypted = cc.encrypt(msg);
        String decrypt = cc.decrypt(encrypted);
        System.out.println(encrypted);
        System.out.println(decrypt);
        String message = breakCeasarCipher(encrypted);
        System.out.println("using Breaking: " + message);
    }    
}
