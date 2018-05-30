package EarthquakeFilter;


/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{
    private Location lo;
    private double max;
    private String name;
    public DistanceFilter(double maxval , Location loc ,String Name){
        lo = loc;
        max = maxval;
        name = Name;
    }
    
    public boolean satisfies(QuakeEntry qe){
        Location locn = qe.getLocation();
        return locn.distanceTo(lo) <= max;
    }
    public String getName(){
            return name;
        }
}
