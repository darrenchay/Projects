
/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class TitleAndDepthComparator implements Comparator <QuakeEntry> {
    public int compare (QuakeEntry q1, QuakeEntry q2){
        String t1 = q1.getInfo();
        String t2 = q2.getInfo();
        //System.out.println(t1);
        double d1= q1.getDepth();
        double d2 = q2.getDepth();
        int val  = t1.compareTo(t2);
        if (val == 0){
            return Double.compare(d1,d2);
        }
        return val;
        
    }

}
