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
		} catch (MalformedURLException e) {
			isValid = false;
			System.out.println("The URL is not in a valid form.");
		} catch (IOException e) {
			isValid = false;
			System.out.println("The connection couldn't be established.");
		}
		return isValid;
	}
}
