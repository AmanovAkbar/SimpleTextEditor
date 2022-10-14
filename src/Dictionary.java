import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
	private ArrayList<TrieNode> bohr;
    private ArrayList<String> pat;
    Dictionary(){
    	bohr = new ArrayList<>();
    	pat = new ArrayList<>();
    	bohr.add(new TrieNode(0, '$'));
    }
    public void loadFromFile(String fileName) throws IOException {
    	Path file = Paths.get(fileName);
		List<String> lines = Files.readAllLines(file);
		for(String s1 : lines) {
			//System.out.println(s1);
			addStringBohr(s1);
		}
    }
    
    public ArrayList<Word> compareToDictionary(String editedText, ArrayList<Word> wordsList) {//this method should throw exception if dictionary is empty
    	
    	
    	ArrayList<Word> foundWords = new ArrayList<>();
    	dictionarySearch(editedText, foundWords);
    	
    	
    	ArrayList<Word> notFound = new ArrayList<>();
    	for(int i = 0; i < wordsList.size(); ++i) {
    		if(!foundWords.contains(wordsList.get(i))) {
    			
    			
    			notFound.add(wordsList.get(i));
    		}
    	}
    	return notFound;
    }
    public ArrayList<Word> compareToDictionary(EditedString s) {
    	ArrayList<Word> wordsList = s.getWords();
    	String editedText = s.getText();
    	ArrayList<Word> foundWords = new ArrayList<>();
    	dictionarySearch(editedText, foundWords);
    	
    	ArrayList<Word> notFound = new ArrayList<>();
    	for(int i = 0; i < wordsList.size(); ++i) {
    		if(!foundWords.contains(wordsList.get(i))) {
    			notFound.add(wordsList.get(i));
    		}
    	}
    	return notFound;
    }

    private void addStringBohr(String s){
        int num = 0;
        
        char[] t = s.toCharArray();
        for(int i = 0; i < t.length; ++i) {
            if(bohr.get(num).next[t[i]]==-1) {
                bohr.add(new TrieNode(num, t[i]));
                bohr.get(num).next[t[i]] = bohr.size()-1;
            }
            num = bohr.get(num).next[t[i]];
        }
        bohr.get(num).flag = true;
        pat.add(s);
        bohr.get(num).patNum = pat.size()-1;
    }
    private int getSufFLink(int v) {
        if(bohr.get(v).sufFLink==-1) {
            int u = getSufLink( v);
            if(u==0) {
                bohr.get(v).sufFLink=0;
            }else {
                bohr.get(v).sufFLink = bohr.get(u).flag ? u : getSufFLink( u);
            }
        }
        return bohr.get(v).sufFLink;
    }
    private int getSufLink ( int v) {
        if(bohr.get(v).sufLink==-1) {
            if(v==0 || bohr.get(v).parent==0) {
                bohr.get(v).sufLink=0;
            }else {
                bohr.get(v).sufLink = getMove(getSufLink(bohr.get(v).parent),bohr.get(v).symbol);
            }
             
        }
        return bohr.get(v).sufLink;
    }
    private int getMove(int v, int ch) {
        if(bohr.get(v).go[ch]==-1) {
            if(bohr.get(v).next[ch]!=-1) {
                bohr.get(v).go[ch]=bohr.get(v).next[ch];
            }else {
                if(v==0) {
                    bohr.get(v).go[ch]=0;
                }else {
                    bohr.get(v).go[ch] = getMove(getSufLink(v), ch);
                }
            }
        }
        return bohr.get(v).go[ch];
    }
    private void dictionarySearch(String s, ArrayList<Word> foundWords) {
        int u = 0;
        
        for(int i = 0; i < s.length(); ++i) {
        	 
            u = getMove(u, s.charAt(i));
           
            check(u, i+1, foundWords);
             
        }
        
         
    }
    private  void check(int v, int i, ArrayList<Word> foundWords) {
        for(int u = v; u!=0; u = getSufFLink(u)) {
            if(bohr.get(u).flag) {
            	Word found = new Word((i-pat.get(bohr.get(u).patNum).length()), i,  pat.get(bohr.get(u).patNum));
            	foundWords.add(found);
                
            }
        }
         
    }
}
