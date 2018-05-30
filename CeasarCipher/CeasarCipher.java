import edu.duke.*;
import java.io.*;
/**
 * Write a description of CeasarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CeasarCipher {
    public String encrypt (String input, int key){
        StringBuilder encrypt = new StringBuilder(input);
        String Capitalalphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlpha = Capitalalphabets.toLowerCase();
        String alphabets = Capitalalphabets + lowerAlpha;
        //System.out.println(alphabets);
        String shiftedalpha = Capitalalphabets.substring(key) + Capitalalphabets.substring(0,key) + lowerAlpha.substring(key) + lowerAlpha.substring(0,key);
        //System.out.println(shiftedalpha);
        for (int i=0; i < encrypt.length(); i++){
            char currCh = encrypt.charAt(i);
            int idx = alphabets.indexOf(currCh);
            if (idx != -1){
                char newChar = shiftedalpha.charAt(idx);
                encrypt.setCharAt(i,newChar);
            }
        }
        return encrypt.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypt = new StringBuilder(input);
        String Capitalalphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlpha = Capitalalphabets.toLowerCase();
        String alphabets = Capitalalphabets + lowerAlpha;
        //System.out.println(alphabets);
        String shiftedalpha1 = Capitalalphabets.substring(key1) + Capitalalphabets.substring(0,key1) + lowerAlpha.substring(key1) + lowerAlpha.substring(0,key1);
        //System.out.println(shiftedalpha1);
        String shiftedalpha2 = Capitalalphabets.substring(key2) + Capitalalphabets.substring(0,key2) + lowerAlpha.substring(key2) + lowerAlpha.substring(0,key2);        
        //System.out.println(shiftedalpha2);
        for (int i=0; i < encrypt.length(); i++){
            char currCh = encrypt.charAt(i);
            int idx = alphabets.indexOf(currCh);
            if (idx != -1){
                if (((i+1)%2) == 0){
                    char newChar = shiftedalpha2.charAt(idx);
                    encrypt.setCharAt(i,newChar);
                }
                if (((i+1)%2) != 0){
                    char newChar = shiftedalpha1.charAt(idx);
                    encrypt.setCharAt(i,newChar);
                }
            }
        }
        return encrypt.toString();
    }
    
    public void test2(String input, int key, int key2){
        String encrypted = encryptTwoKeys(input, key, key2);
        String decrypted = encryptTwoKeys(encrypted, 26-key, 26-key2);
        System.out.println(input);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }
    
    
    public void test(String input, int key){
        String encrypted = encrypt(input, key);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(input);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }
    public void testEncrypt(int key){
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
}
