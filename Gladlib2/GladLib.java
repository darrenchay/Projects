package Gladlib2;
import edu.duke.*;
import java.util.*;


/**
 * Write a description of GladLib3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GladLib {
    private HashMap<String, ArrayList<String>> map;
    private ArrayList<String> usedList;
    private Random myRandom;
    private ArrayList<String> usedCategories;
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLib(){
        map = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        usedList = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
        myRandom = new Random();
    }
    
    public GladLib(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] labels={"adjective","noun","color","country","name","animal","timeframe","verb","fruit"};
        for (String s: labels){
            ArrayList<String> list = readIt(source+ "/" +s+".txt");
            map.put(s,list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        String words = source.get(index);
        return words;
    }
    
    private void addWords(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        String words = source.get(index);
        if (! usedList.contains(words)){
            usedList.add(source.get(index));
        }
    }
    
    private String getSubstitute(String label) {
        if (! usedCategories.contains(label)){
            usedCategories.add(label);
        }
        if (map.containsKey(label)) {
            return randomFrom(map.get(label));
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String sub = "";
        while (true){
            if(usedList.indexOf(sub) == -1){
                usedList.add(sub);
                break;
            }
            else{
                String newsub = getSubstitute(w.substring(first+1,last));
                sub = newsub;
            }
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        return prefix+sub+suffix;      
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap(){
        int words =0;
        for (String s: map.keySet()){
            ArrayList<String> list = map.get(s);
            words = words + map.get(s).size();
        }
        return words;
    }
    
    private int totalWordsConsidered(){
        int total = 0;
         for (String s: map.keySet()){
            ArrayList<String> list = map.get(s);
            if (usedCategories.contains(s)){
                total = total + list.size();
            }
        }
        return total;
    }
    
    public void makeStory(){
        usedList.clear();
        usedCategories.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println(usedList.size()+ " words replaced");
        int num = totalWordsInMap();
        System.out.println(num +" possible words choices");
        int total = totalWordsConsidered();
        System.out.println(usedCategories);
        System.out.println(total +" possible words choices from used categories");
        for (String s: map.keySet()){
            ArrayList<String> list = map.get(s);
            System.out.println (s + "\t "+list.size());
        }
    }
    

}
