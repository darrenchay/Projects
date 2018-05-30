import edu.duke.*;
import java.io.*;
/**
 * Write a description of testCeasarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class testCeasarCipherTwo {
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
    
     private String halfofString (String message, int Start){
        StringBuilder words = new StringBuilder(message);
        String newhalf= "";
        String newhalf2="";
        for (int k= Start; k<words.length();k++){
            String letter = message.substring(k,k+1);
            if ( k%2 == 0){
                newhalf = newhalf + letter;
            }
            else {
                newhalf2= newhalf2 + letter;
            }
        }
        String wordhalves = newhalf + "!@#$%" + newhalf2;
        return wordhalves;
    }
    
    public void simpleTests(){
        FileResource fr = new FileResource("WordsLotsOfEs.txt");
        String msg = fr.asString();
        CeasarCipherTwo cc = new CeasarCipherTwo(17,3);
        String encrypted = cc.encrypt(msg);
        String decrypted = cc.decrypt(encrypted);
        String decrypt = breakCeasarCipher(encrypted);
        System.out.println(encrypted);
        System.out.println(decrypted);
        System.out.println(decrypt);
    }
    
    private int getKey (String input){
        int freq[] = countletters(input);
        int index = maxIndex(freq);
        int decryptKey = index - 4;
        if (index<4){
            decryptKey = 26 - ( 4- index);
        }
        return decryptKey;
    }
    
    public String breakCeasarCipher(String input){
        String temp = halfofString (input, 0);
        int index1 = temp.indexOf("!@#$%") ;
        String part1 = temp.substring(0, index1);
        String part2 = temp.substring (index1+5);
        int key = getKey(part1);
        int keytwo = getKey(part2);
        System.out.println("key1 is: " + key);
        System.out.println("key2 is: " + keytwo);
        CeasarCipherTwo cc = new CeasarCipherTwo(key,keytwo);
        String decrypt = cc.decrypt(input);
        return decrypt;
    }
    
}
