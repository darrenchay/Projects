import edu.duke.*;
/**
 * Write a description of Shakespear here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shakespear {
    public String [] commonWords(){
        FileResource fr = new FileResource("data/common.txt");
        String[] common = new String[20];
        int index = 0;
        for (String s : fr.words()){
            common[index] = s;
            index += 1;
        }
        return common;
    }
    
    public void txtShakes(){
        String [] texts = {"small.txt"};
        String[] common = commonWords();
        int[] counts = new int[common.length];
        for (int k=0; k < texts.length; k++){
            FileResource fr = new FileResource("data/" + texts[k]);
            for (String s : fr.words()){
                s = s.toLowerCase();
                for (int i = 0; i < common.length; i++){
                    if (common[i].equals(s)){
                         counts[i] += 1;
                    }
                    
                }
            }
            
        }
        for(int k=0; k < common.length; k++){
            System.out.println(common[k] + "\t" + counts[k]);
        }
    }
}
