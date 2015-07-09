package highlighter;

import java.net.*;
import java.io.*;

public class HighlightSATWords {
	public static String satFile = "C:\\Users\\priya\\git\\Highlighter\\src\\SATwords.txt";
	public static String htmlFile = "C:\\Users\\priya\\Desktop\\sample.html";

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

	public static void readFromUrl(String testURL, String htmlFile)
			throws IOException {
		if (checkURL(testURL)) {
			URL url = new URL(testURL);
			URLConnection conn = url.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			while ((in.readLine()) != null) {
				String[] words = in.readLine().split("\\s+");
				String newString = "";
				for (String word : words) {
					newString += compareURLWordsToTxt(word, satFile);
				}
				writeStyledLineToHTML(newString, htmlFile);
			}

		}
	}

	public static String compareURLWordsToTxt(String word, String file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line ="";
			while ((line=reader.readLine()) != null) {
				if (word.equalsIgnoreCase(line.trim())) {
					reader.close();
					return "<mark>" + word + "</mark>" + " ";
				}
			}
			reader.close();
			return word + " ";
		}
		// catch (FileNotFoundException e) {
		// System.out.println("File not found.");
		// }
		catch (IOException ioe) {
			System.out.println("Could not read from file.");
		}
		return null;

	}

	public static void writeStyledLineToHTML(String newString, String FileName)
			throws FileNotFoundException {
		PrintWriter fout = null;
		fout = new PrintWriter(new BufferedOutputStream(new FileOutputStream(
				FileName, true)));
		fout.write(newString);
		fout.close();
	}

}
