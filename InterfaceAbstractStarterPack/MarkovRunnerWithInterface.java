package InterfaceAbstractStarterPack;


/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);

        String model = markov.toString();
        System.out.println("running with " + model);
        for(int k=0; k < 3; k++){
            markov.setRandom(seed); 
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov(int seed) {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size,seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(7);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

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
    
    
    public void compareModels(){        
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        EfficientMarkovModel mark = new EfficientMarkovModel(5);
        runModel(mark,st, 1000, 615);
        long t1 = System.nanoTime();
        System.out.println(t1/1000000000 + " seconds for the model "+ mark.toString() );
        MarkovModel model = new MarkovModel(5);
        runModel(model, st, 1000, 615);        
        long t2 =System.nanoTime();
        System.out.println((t2-t1)/1000000000 +" seconds for model" + model.toString());
    }
    
    public void testmap(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        EfficientMarkovModel mark = new EfficientMarkovModel(5);
        //mark.setTraining("yes-this-is-a-thin-pretty-pink-thistle");
        mark.setTraining(st);
        mark.setRandom(531);        
        String s= mark.getRandomText(1000);
        printOut(s);      
        mark.printMap();
    }
}
