import java.util.ArrayList;

public class UndoList extends OperationSequence {

	public UndoList() {
		super();
		
	}
	public ArrayList<Operation> getMacros(int q){
		ArrayList<Operation> macros = new ArrayList<>();
		for(int i = sequence.size()-q; i < sequence.size(); ++i) {
			macros.add(sequence.get(i).getReversedOperation());
		}
		return macros;
	}
	public void showLast(int q) {
		if(q>sequence.size()) {
			q = sequence.size();
			System.out.println("Only " + q + " modifications are available");
		}
		for(int i = sequence.size()-1; i >= sequence.size()-q; --i) {
			Operation o = sequence.get(i);
			String c = o.getText();
			String head;
			if(o.getIsHead()) {
				head = "head";
			}else {
				head = "tail";
			}
			String insert;
			if(!o.isInsert()) {
				insert = "inserted";
			}else {
				insert = "deleted";
			}
			System.out.println("Operation #" + i + ": " + " \"" + c + "\"" + " was " +insert + " from the " + head);
		}
	}
	public Operation getLast() {
		return sequence.remove(sequence.size()-1);
	}
	public void addToTheList(Operation p) {
		sequence.add(p);
	}

}
