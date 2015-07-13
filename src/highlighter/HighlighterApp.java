package highlighter;
import java.util.Scanner;
//import java.io.IOException;

public class HighlighterApp {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the URL: ");
		String url = sc.nextLine();
		long startTime = System.nanoTime();
		HighlightSATWords.readFromUrl(url, HighlightSATWords.htmlFile);
		//code
		long endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " nanoseconds");
		System.out.println("Took "+((endTime - startTime)/1000000000.0)+ " seconds");
		sc.close();
	}

}
