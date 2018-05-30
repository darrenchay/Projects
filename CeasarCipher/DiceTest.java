package CeasarCipher;

import java.util.Random;
/**
 * Write a description of DiceTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DiceTest {
    public void rollingdice(int rolls){
        Random rand = new Random();
        int[] count = new int[13];
        for ( int k=0; k < rolls; k++){
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            System.out.println("roll is " + d1 + "+" + d2 + "=" + (d1+d2));
            count[d1+d2] += 1;
                        
        }
        for (int k=2; k<13; k++){
            System.out.println (k + "=" + count[k]);
        }
    }
}