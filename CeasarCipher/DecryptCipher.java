import java.io.*;
import edu.duke.*;
/**
 * Write a description of decrypt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DecryptCipher {
    public String decypt(String encrypted){
        CeasarCipher cc = new CeasarCipher();
        int freq[] = countletters(encrypted);
        int maxIdx = maxIndex(freq);
        int decryptKey = maxIdx - 4;
        if (maxIdx<4){
            decryptKey = 26 - ( 4- maxIdx );
        }
        String decrypted = cc.encrypt(encrypted, 26 - decryptKey);
        //System.out.println(decrypted);
        return decrypted;
       
    }

    public int[] countletters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int count[] = new int[26];
        for (int k = 0; k< message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int index = alph.indexOf(ch);
            if (index != -1){
                count[index] += 1;
            }
        }
        for (int k=1; k<count.length; k++){
            System.out.println(count[k] +" words with length " + k);
        }
        return count;
    }
    
    public void testcount(){
        FileResource fr = new FileResource("lotsOfWords.txt");
        String message = fr.asString();
        int count[] = countletters(message);
        for (int k=0; k<count.length; k++){
            System.out.println(count[k] +" " + k);
        }
    }
    
    public int maxIndex(int freq[]){
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
    
    
    public String halfofString (String message, int Start){
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
        //System.out.println(newhalf);
        //System.out.println(newhalf2);
        String wordhalves = newhalf + "!@#$%" + newhalf2;
        //System.out.println(wordhalves);
        return wordhalves;
    }
    
    public int getKey(String message){
        int count[] = countletters(message);
        int largest = maxIndex(count);
        int decryptKey = largest - 4;
        if (largest<4){
            decryptKey = 26 - ( 4- largest);
        }
        return decryptKey;
    }
    
    public void decryptTwoKeys(String encrypted ){
        String seperated = halfofString(encrypted, 0);
        int indexone = seperated.indexOf("!@#$%");
        String partone = seperated.substring(0,indexone);
        String parttwo = seperated.substring(indexone+5);
        //System.out.println(partone);
        //System.out.println(parttwo);
        int keyone = getKey(partone);
        int keytwo = getKey(parttwo);
        System.out.println("key1 is: " + keyone);
        System.out.println("key2 is: " + keytwo);
        CeasarCipher cc = new CeasarCipher();
        String decrypted = cc.encryptTwoKeys(encrypted, 26- keyone, 26- keytwo);
        System.out.println(decrypted);
    }
    
    public void keytester(){
        FileResource fr = new FileResource("mysteryTwoKeysQuiz.txt");
        String message = fr.asString();
        decryptTwoKeys(message);
    }
    public void testMxIndex(){
        FileResource fr = new FileResource("lotsOfWords.txt");
        String message = fr.asString();
        int count[] = countletters(message);
        int maxindex = maxIndex(count);
        System.out.print ("maxindex "+maxindex);
    }
}
