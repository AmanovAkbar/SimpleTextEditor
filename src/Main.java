import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	static EditedString editedString;
	static Dictionary d;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		editedString = EditedString.getInstance();
		editedString.addContentType(new ContentXml());
		String folder = "./files";
		Path fold = Paths.get(folder);
		if(!Files.exists(fold)) {
			Files.createDirectory(fold);
		}
		System.out.println("Start inputting commands");
		Scanner scan = new Scanner(System.in);
		String command = scan.nextLine();
		while(!command.contentEquals("close")) {
			char c = command.charAt(0);
			switch(c) {
			case 's':
				s(command);
				break;
			case 'A':
				A(command);
				break;
			case 'a':
				a(command);
				break;
			case 'D':
				D(command);
				break;
			case 'd':
				d(command);
				break;
			case 'l':
				l(command);
				break;
			case 'u':
				u();
				break;
			case 'r':
				r();
				break;
			case 'm':
				m(command);
				break;
			case 'c':
				c(command);
				break;
			case '$':
				ma(command);
			
			}
			command = scan.nextLine();
		}
		System.out.println("Enter a file name: ");
		String name = scan.nextLine();
		if(name.equals("")) {
			
			name = randomString(7);
		}
		scan.close();
		Charset charset = Charset.forName("UTF-8");
		String fileName = "./files/" + name+"."+ editedString.getCurrentTypeName();
		Path file = Paths.get(fileName) ;
		
		try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
		    writer.write(editedString.getText().toCharArray(), 0, editedString.getText().toCharArray().length);
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
	}

	private static String randomString(int l) {
		int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = l;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	    	
	        int randomLimitedInt = leftLimit + (int)(random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();
	    return generatedString;
	}

	private static void ma(String command) {
		if(command.length()>1) {
			String name = command.substring(1);
			editedString.doMacros(name);
		}else {
			System.out.println("wrong command");
		}
		editedString.Display();
		
	}

	private static void c(String command) {
		if(command.equals("content txt")) {
			editedString.setCurrentType("txt");
		}else if(command.equals("coontent xml")) { //add more else if when adding more content types
			editedString.setCurrentType("xml");
		}else {
			System.out.println("wrong command");
		}
		
	}

	private static void m(String command) {
		if(command.length()>2) {
			String[]cL = command.split(" ");
			int q = Integer.parseInt(cL[1]);
			String name = cL[2];
			
			editedString.defineMacros(name, q);
		}else {
			System.out.println("wrong command");
		}
	}

	private static void r() {
		editedString.Redo();
		editedString.Display();
		
	}

	private static void u() {
		editedString.Undo();
		editedString.Display();
	}

	private static void l(String command) throws IOException {
		if(command.length()>2) {
			if(command.charAt(1)==' ') {
				String number = command.substring(2);
				int q = Integer.parseInt(number);
				editedString.showLastModifications(q);
			}else {
				String language = command.substring(5);
				if(language.equals("eng")) { 
					
					d = new Dictionary();
					d.loadFromFile("eng2.txt");
				}else if(language.equals("fra")) {  //Add more else if statements once adding more dictionaries.
					d = new Dictionary();
					d.loadFromFile("fra2.txt");
				}else {
					System.out.println("no such language available");
				}
			}
		}else {
			System.out.println("wrong command");
		}
		
	}

	private static void d(String command) {
		if(command.length()>2) {
			String number = command.substring(2);
			int q = Integer.parseInt(number);
			editedString.Delete(true, q);
			editedString.Display();
		}else {
			System.out.println("wrong command");
		}
		
		
	}

	private static void D(String command) {
		if(command.length()>2) {

			String number = command.substring(2);
			
			int q = Integer.parseInt(number);
			editedString.Delete(false, q);
			editedString.Display();
		}else {
			System.out.println("wrong command");
		}
		
	
		
	}

	private static void a(String command) {
		if(command.length()>3) {
			String toInsert = command.substring(3, command.length()-1);
			editedString.Insert(true, toInsert);
			editedString.Display();
		}else {
			System.out.println("wrong command");
		}
		
	}

	private static void A(String command) {
		if(command.length()>3) {
			String toInsert = command.substring(3, command.length()-1);
			
			editedString.Insert(false, toInsert);
			editedString.Display();
		}else {
			System.out.println("wrong command");
		}
		
	}

	private static void s(String command){
		if(command.equals("spell")) {
			if(d==null) {
				System.out.println("First Use lang command to specify text language!!!");
				System.out.println("For Example: lang eng, lang fra .. etc.");
			}else {
				ArrayList<Word> nF = d.compareToDictionary(editedString);
				for(int i = 0; i < nF.size(); ++i) {
					System.out.println(nF.get(i).getContent());
				}
			}
			
		}else if(command.equals("spell-a")){
			if(d==null) {
				System.out.println("First Use lang command to specify text language!!!");
				System.out.println("For Example: lang eng, lang fra .. etc.");
			}else {
				ArrayList<Word> nF = d.compareToDictionary(editedString);
				String ttd = new String(editedString.getText());//textToDisplay
				int offset=0;
				for(int i = 0; i < nF.size(); ++i) {
					Word cur = nF.get(i);
					String t = ttd.substring(0, cur.getBeginIndex()+offset) + "*[" + ttd.substring(cur.getBeginIndex()+offset, cur.getEndIndex()+offset) + "]" 
							+ ttd.substring(cur.getEndIndex()+offset, ttd.length());
					ttd = t;
					offset+=3;
				}
				System.out.println(ttd);
			}
		}else if(command.equals("spell-m")) {//can not be undone, because I chose a bad design for Operation class.
			if(d==null) {
				System.out.println("First Use lang command to specify text language!!!");
				System.out.println("For Example: lang eng, lang fra .. etc.");
			}else {
				ArrayList<Word> nF = d.compareToDictionary(editedString);
				for(int i = 0; i < nF.size();) {
					editedString.deleteWord(nF.get(i));
					nF = d.compareToDictionary(editedString);
				}
				editedString.Display();
			}
		}else if(command.equals("s")) {
			editedString.Display();
		}else {
			System.out.println("Wrong command format!");
		}
	}

}
