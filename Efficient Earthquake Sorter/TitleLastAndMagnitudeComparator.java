
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2){
        String t1 = q1.getInfo().substring(q1.getInfo().lastIndexOf(" "));
        String t2= q2.getInfo().substring(q2.getInfo().lastIndexOf(" "));
        int val = t1.compareTo(t2);
        if (val == 0){
            return Double.compare(q1.getMagnitude(),q2.getMagnitude());
        }
        return val;
    }

}
