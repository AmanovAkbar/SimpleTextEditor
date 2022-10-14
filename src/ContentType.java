import java.util.ArrayList;

public interface ContentType {
	public String getTypeName();
	public ArrayList<Word> filterOutWords(String s);
	
}
