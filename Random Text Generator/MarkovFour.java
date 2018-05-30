
/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.io.*;
public class MarkovFour {
    private String myText;
    private Random myRandom;
    
    public MarkovFour() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-4);
        String key = myText.substring(index,index+4);
        sb.append(key);
        for(int k=0; k < numChars -4; k++){
            ArrayList<String> list = getFollows(key);
            if(list.size() == 0){
                break;
            }
            int rand = myRandom.nextInt(list.size());
            String next = list.get(rand);
            sb.append(next);
            key = key.substring(1)+next;
        }
        
        return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key){
        ArrayList<String> list = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length()-1){
            int index = myText.indexOf(key,pos);
            if (index == -1){
                break;
            }
            if(index + key.length() > myText.length()-1){
                break;
            }
            String follow = myText.substring(index+key.length(),index+key.length()+1);
            list.add(follow);
            pos = index + 1;
        }
        return list;
    }
}
