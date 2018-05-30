import edu.duke.*;
import java.io.*;
/**
 * Write a description of Ceasar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ceasar {
    private String alphabets;
    private String shiftedalpha;
    private int mainKey;
    public Ceasar(int key){
        String Capitalalphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlpha = Capitalalphabets.toLowerCase();
        alphabets = Capitalalphabets + lowerAlpha;
        shiftedalpha = Capitalalphabets.substring(key) + Capitalalphabets.substring(0,key) + lowerAlpha.substring(key) + lowerAlpha.substring(0,key);
        mainKey = key;
    }
    
    public String encrypt (String input){
        StringBuilder encrypt = new StringBuilder(input);
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
    
    public String decrypt(String encrypted){
        Ceasar cc = new Ceasar(26-mainKey);
        String decrypted = cc.encrypt(encrypted);
        return decrypted;
    }
    
}
