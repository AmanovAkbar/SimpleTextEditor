//isHead ==true -> operation to the head
//isHead == false -> operation to the tail
//isInsert == true -> insert
//isInsert == false -> delete
public class Operation {
	
	private boolean isInsert;
	private boolean isHead;
	private String text;
	Operation(boolean head, boolean insert, String t){
		isHead = head;
		isInsert = insert;
		text = t;
	}
	
	public boolean getIsHead() {
		return isHead;
	}
	public boolean isInsert() {
		return isInsert;
	}
	public String getText() {
		return text;
	}
	Operation getReversedOperation() {
		return new Operation(isHead, !isInsert, text);
	}
}
