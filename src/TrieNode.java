
public class TrieNode{
    int next[] = new int[130];
    int go [] = new int[130];
     
    int sufLink;
    int sufFLink;
    int parent;
    boolean flag;
    int patNum;
    int symbol;
    TrieNode(int p, int s){
    	for(int i = 0; i < 130; ++i) {
    		next[i]=-1;
    		go[i]=-1;
    	}
        flag = false;
        sufLink = -1;
        parent = p;
        symbol = s;
        sufFLink = -1;
         
    }
    
    
}