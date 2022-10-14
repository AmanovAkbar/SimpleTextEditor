
public class RedoList extends OperationSequence {

	public RedoList() {
		super();
	}
	public Operation getLast() {
		return sequence.remove(sequence.size()-1);
	}
	public void addToTheList(Operation p) {
		sequence.add(p);
	}
	public void emptyTheList() {
		sequence.clear();
	}

}
