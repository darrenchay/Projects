package WordGram;


/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov.toString()); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov(int order, int seed, int size) { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        //st = "This is a test yes this is a test and its just a test so this is just a test";
        //MarkovWordOne markovWord = new MarkovWordOne(); 
        //runModel(markovWord, st, 200); 
        MarkovWord mark = new MarkovWord(order);
        runModel(mark, st, size, seed);
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 
    
    public void compareMethods(int order, int seed){
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord mark = new MarkovWord(order);
        long t1 = System.nanoTime()/1000000000;
        runModel(mark, st, 100, seed);
        long t2 = System.nanoTime()/1000000000;
        System.out.println("Time taken to run MarkovWord is: "+ (t2-t1));
        EfficientMarkovWord goodmark = new EfficientMarkovWord(order);
        long t3 = System.nanoTime()/1000000000;
        runModel(goodmark, st, 100, seed);
        long t4 = System.nanoTime()/1000000000;
        System.out.println("Time taken to run EfficientMarkovWord is: "+ (t4-t3));        
    }
    
    public void testmap(int order, int seed){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        EfficientMarkovWord mark = new EfficientMarkovWord(order);
        //mark.setTraining("this is a test yes this is really a test yes a test this is wow");
        mark.setTraining(st);
        mark.setRandom(seed);        
        String s= mark.getRandomText(100);
        printOut(s);      
        mark.printMap();
    }
}
