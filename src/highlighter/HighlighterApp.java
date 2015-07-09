package highlighter;
import java.util.Scanner;
import java.io.IOException;

public class HighlighterApp {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the URL: ");
		String url = sc.nextLine();
		HighlightSATWords.readFromUrl(url, HighlightSATWords.htmlFile);
		//sc.close();
	}

}
