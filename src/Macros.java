import java.util.ArrayList;
import java.util.List;

public class Macros extends OperationSequence {
	String name;
	public Macros(String n, List<Operation>list) {
		super();
		name = n;
		sequence.addAll(list);
	}
	public ArrayList<Operation> getSequence() {
		return sequence;
	}

}
