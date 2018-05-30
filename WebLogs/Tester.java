package WebLogs;


/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.io.*;
public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        String date = (le.getAccessTime()).toString();
        System.out.println(date);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer(String name) {
        LogAnalyzer la = new LogAnalyzer();    
        la.readFile(name);
        
        la.printAll();
    }
    
    public void testUniqueIP(String name){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(name);
        int count = la.countUniqueIPs();
        System.out.println("Unique IPs = " + count);
        //System.out.println(countUniqueIPs());
        //la.printAllHigherThanNum(400);
        ArrayList<String> list = la.UniqueIpVisitsOnDay("Sep 24");
        System.out.println(list.size());
        int num = la.countUniqueIPsInRange(200,299);
        System.out.println(num);
    }
    
    public void testCountVisitsPerIP(String name){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(name);
        HashMap<String, Integer> counts =  la.countVisitsPerIP();
        System.out.println(counts);
    }
    
    public void testmostNumberVisitsByIP(String name){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(name);
        HashMap<String, Integer> counts =  la.countVisitsPerIP();
        int num = la.mostNumberVisitsByIP(counts);
        System.out.println(num);
    }
    
    public void testiPsMostVisits(String name){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(name);
        HashMap<String, Integer> counts =  la.countVisitsPerIP();
        ArrayList<String> list =  la.iPsMostVisits(counts);
        System.out.println(list);
    }
    
    public void testiPsForDays(String name){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(name);
        HashMap<String, ArrayList<String>> map =  la.iPsForDays();
        for (String s: map.keySet()){ 
             System.out.println(s + "\t" + map.get(s) );
        }
    }
    
    public void testdayWithMostIPVisits(String name){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(name);
        HashMap<String,ArrayList<String>> map = la.iPsForDays();
        String most = la.dayWithMostIPVisits(map);
        System.out.println(most);
    }
    
    public void testiPsWithMostVisitsOnDay(String name){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(name);
        HashMap<String,ArrayList<String>> map = la.iPsForDays();
        System.out.println(map);
        ArrayList<String> list = la.iPsWithMostVisitsOnDay (map,"Sep 30");
        System.out.println(list);
    }
}
