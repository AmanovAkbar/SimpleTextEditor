import java.util.ArrayList;

public class ContentText implements ContentType {
	
	private String type;
	public ContentText() {
		type = "txt";
	}

	@Override
	public String getTypeName() {
		
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
					//System.out.println(""+sc[i]);
					word += sc[i];
					i++;
					if(i>=sc.length) {
						break;
					}
				}
				Word w = new Word(pos, i, word);
				result.add(w);
			}
			
		}
		return result;
	}

}
