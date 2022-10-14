import java.util.ArrayList;

public class OperationSequence {
	protected ArrayList<Operation>sequence;
	OperationSequence(){
		sequence = new ArrayList<Operation>();
	}
	public boolean isEmpty() {
		return sequence.isEmpty();
		
	}
	public int getSize() {
		return sequence.size();
	}
}
