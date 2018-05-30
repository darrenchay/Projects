package WordGram;


public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for(int k = 0; k< myWords.length;k++){
            ret += myWords[k] + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if(this.length() != other.length()){
            return false;
        }
        for(int k =0; k< myWords.length;k++){
            if(!this.wordAt(k).equals(other.wordAt(k))){
                return false;
            }
        }
        return true;
    }

    public WordGram shiftAdd(String word) { 
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        for(int i=1;i<myWords.length;i++){
            out.myWords[i-1] = myWords[i];
        }
        out.myWords[myWords.length-1] = word;        
        //System.out.println(out + " :outtest");
        //System.out.println(myWords.toString());
        return out;
    }
    
    
    public int hashCode(){
        String word = this.toString();
        int hash = word.hashCode();
        return hash;        
    }
}