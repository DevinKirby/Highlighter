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
		// catch (MalformedURLException e) {
		// isValid = false;
		// System.out.println("The URL is not in a valid form.");}
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
					newString += compareURLWordsToTxt(word);
				}
				writeStyledLineToHTML(newString);
			}

		}
	}

	public static String compareURLWordsToTxt(String word) {
		String file = "C:\\Users\\Conor\\git\\Highlighter\\src\\SATwords.txt";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while ((reader.readLine()) != null) {
				if (word.equalsIgnoreCase(reader.readLine().trim())) {
					reader.close();
					return "<mark>" + word + "</mark>"+" ";
				}
			}
			reader.close();
			return word+" ";
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (IOException ioe) {
			System.out.println("Could not read from file.");
		}
		return null;
		
	}

	private static void writeStyledLineToHTML(String newString) {
		PrintWriter fout = null; 
		try {
			fout = new PrintWriter(new BufferedOutputStream(new FileOutputStream("C:\\Users\\priya\\Desktop\\sample.html",true)));
			fout.write(newString);
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		fout.close();
	}

}
