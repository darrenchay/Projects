package EarthquakeSorter;


/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.io.*;
public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> list, int indx){
        int min = indx;
        for(int k = min+1; k<list.size();k++){
            if(list.get(k).getDepth()>list.get(min).getDepth()){
                min = k;
            }
        }
        return min;
    }
    
    public void sortByDepth(ArrayList<QuakeEntry> list){
        for (int i = 0; i<50;i++){
            int max = getLargestDepth(list,i);
            QuakeEntry qi = list.get(i);
            QuakeEntry qax = list.get(max);
            list.set(i,qax);
            list.set(max,qi);
            System.out.println("Printing quakes after pass " +i);
            for (QuakeEntry qe: list) { 
                System.out.println(qe);
            } 
        }
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> list, int num){
        for(int k = 0; k<list.size()-num-1;k++){
            if(list.get(k).getMagnitude()>list.get(k+1).getMagnitude()){
                QuakeEntry qi = list.get(k);
                QuakeEntry qmin = list.get(k+1);
                list.set(k,qmin);
                list.set(k+1,qi);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> list){
        for (int i=0;i<list.size()-1;i++){
            System.out.println("Printing quakes after pass " +i);
            onePassBubbleSort(list,i);
            for (QuakeEntry qe: list) { 
                System.out.println(qe);
            } 
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> list){
        boolean ans = true;
        for (int k= 0;k<list.size()-1;k++){
            if( list.get(k).getMagnitude()>list.get(k+1).getMagnitude()){
                return false;
            }
        }
        return ans;
    }
    
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> list){
        for (int i=0;i<list.size()-1;i++){
            if(checkInSortedOrder(list) == false){
                System.out.println("Printing quakes after pass " +(i+1));
                onePassBubbleSort(list,i);
                //for (QuakeEntry qe: list) { 
                //    System.out.println(qe);
                //} 
            }
            else{
                System.out.println("Number of times quakes were bubble sorted = " + i);
                break;
            }
        }
        
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        for (int i=0; i< in.size()-1; i++) {
            if(checkInSortedOrder(in) == false){
                int minIdx = getSmallestMagnitude(in,i);
                QuakeEntry qi = in.get(i);
                QuakeEntry qmin = in.get(minIdx);
                in.set(i,qmin);
                in.set(minIdx,qi);
                System.out.println("Printing quakes after pass " +(i+1));
                //for (QuakeEntry qe: in) { 
                //    System.out.println(qe);
                //} 
            }
            else{
                System.out.println("Number of times quakes were bubble sorted = " + i);
                break;
            }
        }
    }
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "EarthquakeSorter/data/nov20quakedata.atom";
        String source = "EarthquakeSorter/data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");   
        //System.out.println(list);
        //sortByMagnitude(list);
        //sortByDepth(list); 
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        System.out.println("Printing sorted quakes" );
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
