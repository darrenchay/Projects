import edu.duke.*;
import java.io.*;
/**
 * Write a description of CeasarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CeasarCipherTwo {
    private String alphabets;
    private String shiftedAlpha1;
    private String shiftedAlpha2;
    private int key1;
    private int key2;
    
    public CeasarCipherTwo(int key, int keytwo){
        String Capitalalphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlpha = Capitalalphabets.toLowerCase();
        alphabets = Capitalalphabets + lowerAlpha;
        shiftedAlpha1 = Capitalalphabets.substring(key) + Capitalalphabets.substring(0,key) + lowerAlpha.substring(key) + lowerAlpha.substring(0,key);
        shiftedAlpha2 = Capitalalphabets.substring(keytwo) + Capitalalphabets.substring(0,keytwo) + lowerAlpha.substring(keytwo) + lowerAlpha.substring(0,keytwo);
        key1 = key;
        key2 = keytwo;
    }
    
    public String encrypt(String input){
        StringBuilder encrypt = new StringBuilder(input);
        for (int i=0; i < encrypt.length(); i++){
            char currCh = encrypt.charAt(i);
            int idx = alphabets.indexOf(currCh);
            if (idx != -1){
                if (((i+1)%2) == 0){
                    char newChar = shiftedAlpha2.charAt(idx);
                    encrypt.setCharAt(i,newChar);
                }
                if (((i+1)%2) != 0){
                    char newChar = shiftedAlpha1.charAt(idx);
                    encrypt.setCharAt(i,newChar);
                }
            }
        }
        return encrypt.toString();
    }
    
    public String decrypt(String input){
        CeasarCipherTwo cc = new CeasarCipherTwo(26-key1, 26-key2);
        String decrypt = cc.encrypt(input);
        return decrypt;
    }
    
   
}
