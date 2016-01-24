import java.util.Scanner;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
//test

public class TextBuddy {
	private String fileName_;
	private Scanner sc_;
	public final String MODE_ADD = "add";
	public final String MODE_DELETE = "delete";
	public final String MODE_DISPLAY = "display";
	public final String MODE_EXIT = "exit";
	public final String MODE_CLEAR = "clear";
	public final String INVALID_COMMAND_ERROR = "Invalid command!";
	private LinkedList<String> data_;
	
	public static void main (String [] args){
		String fileName = args[0];
		printWelcomeMessage(fileName);
		Scanner sc = new Scanner(System.in);
		TextBuddy textBuddy = new TextBuddy(fileName, sc);
		while(true){
			System.out.print("command: ");
			textBuddy.readSingleCommand();
		}
	}
	
	public static void printWelcomeMessage(String fileName){
		System.out.println("Welcome to TextBuddy. " + fileName + " is ready for use");
	}
	
	public TextBuddy(String fileName, Scanner sc){
		fileName_ = fileName;
		sc_ = sc;
		data_ = new LinkedList<String>();
	}
	
	public void readSingleCommand(){
		String nextCommand = sc_.nextLine();
		String[] commandArr = nextCommand.split(" ", 2);
		if(commandArr[0].equalsIgnoreCase(MODE_ADD)){
			commandAdd(commandArr[1]);
		} else if(commandArr[0].equalsIgnoreCase(MODE_DELETE)){
			commandDelete(Integer.parseInt(commandArr[1]) - 1);
		} else if(commandArr[0].equalsIgnoreCase(MODE_DISPLAY)){
			commandDisplay();
		} else if(commandArr[0].equalsIgnoreCase(MODE_CLEAR)){
			commandClear();
		} else if(commandArr[0].equalsIgnoreCase(MODE_EXIT)){
			commandExit();
		} else {
			System.out.println(INVALID_COMMAND_ERROR);
		}
	}
	

	
	private void commandAdd(String toAdd){
		data_.add(toAdd);
		System.out.println("added to " + fileName_ + 
				": \"" + toAdd + "\"");
	}
	
	private void commandDelete(int index){
		String deleted = data_.remove(index);
		System.out.println("deleted from " + fileName_ + 
				": \"" + deleted + "\"");
	}
	
	private void commandDisplay(){
		if(data_.size() == 0){
			System.out.println(fileName_ + " is empty");
		}
		for(int i = 1; i <= data_.size(); i++){
			System.out.println(i + ". "+ data_.get( i - 1 ));
		}
	}
	
	private void commandExit(){
		File saveFile = new File(fileName_);
		try{
			writeToFile(saveFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} 
		System.exit(1);
	}
	
	private void commandClear(){
		data_ = new LinkedList<String>();
		System.out.println("all content deleted from " + fileName_);
	}
	
	private void writeToFile(File file) throws FileNotFoundException, SecurityException{
		PrintWriter printWriter = new PrintWriter(file);
		for(int i = 1; i <= data_.size(); i++){
			printWriter.write(data_.get(i - 1));
		}
		printWriter.close();
	}
	
}
















