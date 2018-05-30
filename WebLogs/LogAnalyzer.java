package WebLogs;


/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();        
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for (String w : fr.lines()){
             WebLogParser p = new WebLogParser();
             LogEntry le = p.parseEntry(w);
             records.add(le);
             
         }
        
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         int IPno = 0;
         ArrayList<String> Ips = new ArrayList<String>();
         for (LogEntry le: records){
            String ip = le.getIpAddress();
            if (! Ips.contains(ip)){
                Ips.add(ip);
            }
         }
         IPno = Ips.size();
         return IPno;
     }
     
     public ArrayList<String> UniqueIpVisitsOnDay(String someday){
        ArrayList<String> IPs = new ArrayList<String>();
        for (LogEntry le : records){
           Date d = le.getAccessTime();
           String ip = le.getIpAddress();
           String date = d.toString();
           if (date.contains(someday)){
               if (! IPs.contains(ip)){
                   IPs.add(ip);
                }
            }
        }
        return IPs;
        }
        
        
     public ArrayList<String> IpVisitsOnDay(String someday){
        ArrayList<String> IPs = new ArrayList<String>();
        for (LogEntry le : records){
           Date d = le.getAccessTime();
           String ip = le.getIpAddress();
           String date = d.toString();
           if (date.contains(someday)){               
                   IPs.add(ip);              
            }
        }
        return IPs;
        }
     public void printAllHigherThanNum(int num){
        ArrayList<LogEntry> Ips = new ArrayList<LogEntry>();
        for (LogEntry le: records){
            int status = le.getStatusCode();
            if ( status > num){
                Ips.add(le);
            }
        }
        for (int k=0; k<Ips.size(); k++){ 
             System.out.println(Ips.get(k));
        }
     }
     
     public int countUniqueIPsInRange(int low, int high){
        int count = 0;
        HashMap <String, Integer> map = new HashMap<String, Integer>();
        for (LogEntry le: records){
            int status = le.getStatusCode();
            String ip = le.getIpAddress();
            if ( status >= low && status<= high && ! map.containsKey(ip)){
                    map.put(ip, status);  
            }
        }
        //System.out.println(map);
        count = map.size();
        return count;
        }
        
     public HashMap<String, Integer> countVisitsPerIP(){
        HashMap <String, Integer> map = new HashMap<String, Integer>();
        for (LogEntry le: records){
            String ip = le.getIpAddress();
            if (! map.containsKey(ip)){
                    map.put(ip, 1);  
            }
            else {
                map.put(ip, map.get(ip) +1);
            }
        }
        return map;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> map){
        int max = 0;
        for (String s : map.keySet()){
            int curr = map.get(s);
            if (curr> max){
                max = curr;
            }
        }
        return max;
     }
     
     public ArrayList<String>  iPsMostVisits(HashMap<String, Integer> map){
        ArrayList<String> list = new ArrayList<String>();
        int max = mostNumberVisitsByIP(map);
        for (String s : map.keySet()){
            if (map.get(s) == max){
                list.add(s);
            }
        }
        return list;
     }
     
     private String getDate(String time){
         String date = "";
        int index1 = time.indexOf("");
        date = time.substring(4, 10);
        return date;
        }
     
     public HashMap<String,ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
        for (LogEntry le : records){
           Date d = le.getAccessTime();
           String temp = d.toString();
           //System.out.println(temp);
           String date = getDate(temp);
           ArrayList<String> list =IpVisitsOnDay(date);    
           if (!map.containsKey(date)){
               map.put(date, list);
           }
        }
        return map;
     }
     
     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> map){
        String  most = "";
        int max = 0;
        for (String s: map.keySet()){
            ArrayList<String>list = map.get(s);
            int size = list.size();
            if (size> max){
                max = size;
                most = s;
            }
        }
        return most;
     }
     
     public ArrayList<String>  iPsWithMostVisitsOnDay (HashMap<String,ArrayList<String>> map, String date){
         ArrayList<String> list = new ArrayList<String>();
         ArrayList<String> dates = map.get(date);
         System.out.println("dates: " + dates);
         HashMap<String, Integer>max =  new HashMap<String, Integer>();
         for (String s: dates){
            if (! max.containsKey(s)){
                    max.put(s, 1);  
            }
            else {
                max.put(s, max.get(s) +1);
            }
            }
         System.out.println("max: "+ max);
         list = iPsMostVisits(max);
         return list;
     }
}

