import java.util.*;
import edu.duke.*;
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LargestQuakes {
    public void findLargestQuakes(int value){
        EarthQuakeParser parse = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parse.read(source);
        System.out.println("Number of entries read= " + list.size());
        /*for (int k = 0 ; k<list.size(); k++){
            System.out.println(list.get(k));   
        }
         */
        /*int largest = indexOfLargest(list);
        QuakeEntry qe = list.get(largest);
        System.out.println(list.get(largest));
        System.out.println(qe.getMagnitude());
        System.out.println(largest);
        */
        ArrayList<QuakeEntry> large = getLargest(list,value);
        for (int k = 0; k<large.size(); k++){
            System.out.println(large.get(k));
        }
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> quakeData){
        int largest = 0;
        double max = 0;
        for (int k = 0; k<quakeData.size();k++){
            QuakeEntry qe = quakeData.get(k);
            double mag = qe.getMagnitude();
            if (mag> max){
                largest = k;
                max = mag;
            }
        }
        return largest;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        for (int k = 0; k<howMany; k++){ 
            QuakeEntry qe = copy.get(indexOfLargest(copy));
            answer.add(qe);
            copy.remove(qe);
        }
        return answer;
    }
}
