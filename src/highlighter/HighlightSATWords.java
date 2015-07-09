package highlighter;

import java.net.*;
import java.io.*;

public class HighlightSATWords {
	public static boolean checkURL(String testURL) {
		boolean isValid = true;
		try {
			URL url = new URL(testURL);
			URLConnection conn = url.openConnection();
			conn.connect();
		} 
//		catch (MalformedURLException e) {
//			isValid = false;
//			System.out.println("The URL is not in a valid form.");}
		catch (IOException e) {
			isValid = false;
			System.out.println("The connection couldn't be established.");
		}
		return isValid;
	}

	public static void readFromUrl(String testURL) throws IOException {
		if (checkURL(testURL)) {
			URL url = new URL(testURL);
			URLConnection conn = url.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			while ((in.readLine()) != null) {
				String[] words = in.readLine().split("\\s+");
				String newString = "";
				for (String word : words) {
					newString += compareURLWordstoTxt(word);
				}
				writeStyledLineToHTML(newString);
			}

		}
	}

	private static void writeStyledLineToHTML(String newString) {
		// TODO Auto-generated method stub

	}

	private static String compareURLWordstoTxt(String word) {
		// TODO Auto-generated method stub
		return null;
	}

}
