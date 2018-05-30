package EarthquakeFilter;


/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter{
    private double min;
    private double max;
    private String name;
    public DepthFilter(double minval , double maxval, String Name){
        min = minval;
        max = maxval;
        name = Name;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return qe.getDepth() >= min && qe.getDepth() <= max;
    }
    public String getName(){
            return name;
        }
}
