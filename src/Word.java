import java.util.Objects;

public class Word {
	private int beginIndex;
	private int endIndex;
	private String content;
	public Word(int i, int e, String c) {
		beginIndex = i;
		endIndex = e;
		content = c;
	}
	public int getBeginIndex() {
		return beginIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public String getContent() {
		return content;
	}
	public void printWordMeta() {
		System.out.println("("+getBeginIndex() + ")" + getContent() + "(" +getEndIndex() + ") ");
	}
	@Override
	public int hashCode() {
		return Objects.hash(beginIndex, endIndex, content);
	}
	@Override
	public boolean equals(Object obj) {
		if(obj==null) {
			return false;
		}
		if(obj==this) {
			return true;
		}
		if (!(obj instanceof Word)) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		Word other = (Word) obj;
		if(beginIndex==other.beginIndex && endIndex==other.endIndex && content.contentEquals(other.getContent())) {
			return true;	
		}else {
			return false;
		}
		
	}
	
}
