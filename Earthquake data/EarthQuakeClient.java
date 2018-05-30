import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        for (int i = 0; i<copy.size();i++){
            QuakeEntry qe = copy.get(i);
            double mag = qe.getMagnitude();
            if (mag> magMin){
                answer.add(copy.get(i));
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (int i = 0; i<quakeData.size(); i++){
            QuakeEntry qe = quakeData.get(i);            
            Location lo = new Location(qe.getLocation());
            //System.out.println("location:"+lo);
            //System.out.println("distance:"+from.distanceTo(lo));
            if(from.distanceTo(lo)< distMax){
                answer.add(qe);
            }
        }
        // TODO

        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (int i = 0; i<quakeData.size(); i++){
            QuakeEntry qe = quakeData.get(i);     
            double depth = qe.getDepth();
            if(depth> minDepth && depth< maxDepth){
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public void quakesOfDepth(double min, double max){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> depths = filterByDepth(list, min, max );  
        System.out.println("Quakes with depths between "+min+" and "+max+" are:");
        for (int k = 0; k<depths.size(); k++){
            System.out.println(depths.get(k));
        }
        System.out.println("Found " + depths.size() + " quakes that match the criteria");
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (int i = 0; i<quakeData.size(); i++){
            QuakeEntry qe = quakeData.get(i);
            String info = qe.getInfo();
            if(where == "start" && info.startsWith(phrase)){
                answer.add(qe);
            }
            if(where == "end" && info.endsWith(phrase)){
                answer.add(qe);
            }
            if(where == "any" && info.contains(phrase)){
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public void quakesByPhrase(String where, String what){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> phrase = filterByPhrase(list, where, what);
        System.out.println("Quakes with phrase ending with " +what + " are:");
        for (int k = 0; k<phrase.size(); k++){
            System.out.println(phrase.get(k));
        }
        System.out.println("Found " + phrase.size() + " quakes that match the criteria");
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes(double mag) {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> mags = filterByMagnitude(list, mag);     
        System.out.println("Quakes with mag greater than "+mag+" are:");
        for (int k = 0; k<mags.size(); k++){
            System.out.println(mags.get(k));
        }
    }

    public void closeToMe(int amount){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        
        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry> dists = filterByDistanceFrom(list, amount, city);
        for (int k = 0; k<dists.size(); k++){
            QuakeEntry qe = dists.get(k);
            Location lo = new Location(qe.getLocation());
            System.out.println(lo.distanceTo(city) + " " + qe.getInfo());
        }
        System.out.println("Found " + dists.size() + " quakes that match the criteria");
        

        // TODO
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
