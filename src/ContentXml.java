import java.util.ArrayList;

public class ContentXml implements ContentType {
	private String type;
	ContentXml(){
		type = "xml";
	}
	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public ArrayList<Word> filterOutWords(String s) {
		ArrayList<Word> result = new ArrayList<>();
		
		char[] sc = s.toCharArray();
		for(int i = 0; i < sc.length; ++i) {
			if(Character.isAlphabetic(sc[i])) {
				
				int pos = i;
				String word = "";
				while(Character.isAlphabetic(sc[i])) {
					
					word += sc[i];
					i++;
					if(i>=sc.length) {
						break;
					}
				}
				Word w = new Word(pos, i, word);
				result.add(w);
			}else if(sc[i]=='<' || sc[i]=='/') {
				
				while(sc[i]!='>') {
					i++;
					if(i>=sc.length) {
						break;
					}
				}
			}
			
		}
		return result;
	}
	

}
