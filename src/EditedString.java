import java.util.ArrayList;
import java.util.TreeMap;

public final class EditedString {
	private static EditedString INSTANCE;
	private String text = "";
	private ContentType currentType;
	private RedoList redo;
	private UndoList undo;
	private TreeMap<String, ContentType> ct = new TreeMap<>();
	private TreeMap<String, Macros> macrosMap;
	private EditedString(){
		
		ct.put("txt", new ContentText());
		redo = new RedoList();
		undo = new UndoList();
		macrosMap = new TreeMap<>();
		currentType = ct.get("txt");
	}
	public void addContentType(ContentType type) {
		
		String key = type.getTypeName();
		if(!ct.containsKey(key)) {
			ct.put(key, type);
		}else {
			System.out.println("This type already exists...");
		}
		
	}
	public void deleteWord(Word word) {
		text = text.substring(0, word.getBeginIndex()) + text.substring(word.getEndIndex(), text.length());
	}
	public String getCurrentTypeName() {
		return currentType.getTypeName();
	}
	public static EditedString getInstance() {
		if(INSTANCE==null) {
			INSTANCE = new EditedString();
		}
		return INSTANCE;
		
	}
	public void defineMacros(String name, int q) {
		ArrayList<Operation> mac = undo.getMacros(q);
		
		Macros macros = new Macros(name, mac);
		macrosMap.put(name, macros);
	}
	public void doMacros(String name) {
		Macros macros = macrosMap.get(name);
		ArrayList<Operation> mac = macros.getSequence();
		for(int i = 0; i < mac.size(); ++i) {
			doOperation(mac.get(i));
			undo.addToTheList(mac.get(i).getReversedOperation());
		}
	}
	public void Display() {
		System.out.println(text);
	}
	public void Undo() {
		if(!undo.isEmpty()) {
			Operation o = undo.getLast();
			doOperation(o);
			redo.addToTheList(o.getReversedOperation());
		}else {
			System.out.println("Nothing left to undo...");
		}
	}
	public void Redo() {
		if(!redo.isEmpty()) {
			Operation o = redo.getLast();
			doOperation(o);
			undo.addToTheList(o.getReversedOperation());
		}else {
			System.out.println("Nothing left to redo...");
		}
	}
	public void showLastModifications(int q) {
		undo.showLast(q);
	}
	public void Insert(boolean isHead, String t) {
		Operation o = new Operation(isHead, true, t);
		doOperation(o);
		undo.addToTheList(o.getReversedOperation());
		redo.emptyTheList();
	}
	public void Delete(boolean isHead, int q) {
		if(q>text.length()) {
			q = text.length();
		}
		String t;
		if(isHead) {
			t = text.substring(0, q);
		}else {
			t = text.substring(text.length()-q, text.length());
		}
		Operation o = new Operation(isHead, false, t);
		doOperation(o);
		undo.addToTheList(o.getReversedOperation());
		redo.emptyTheList();
	}
	public void doOperation(Operation o) {
		if(o.getIsHead()) {
			if(o.isInsert()) {
				text = o.getText()+text;
			}else {
				text = text.substring(o.getText().length());
			}
		}else {
			if(o.isInsert()) {
				text+=o.getText();
			}else {
				text = text.substring(0, text.length()-o.getText().length());
			}
		}
	}
	public void setCurrentType(String typeName) {
		if(ct.containsKey(typeName)) {
			currentType = ct.get(typeName); 
		}else {
			System.out.println("No such content type...");
		}
	}
	public ArrayList<Word> getWords() {
		// TODO Auto-generated method stub
		ArrayList<Word> words = currentType.filterOutWords(text);
		return words;
	}

	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

	

}
