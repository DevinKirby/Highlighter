package highlighter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;

import java.net.*;
import java.io.*;

public class HighlightSATWords {
	public static String satFile = "C:/Users/devin_000/git/Highlighter/src/SATwords.txt";
	public static String htmlFile = "C:/Users/devin_000/Desktop/highlight.html";

	public static boolean checkURL(String testURL) throws Exception {
		boolean isValid = true;
		try {
			// URL url = new URL(testURL);
			// URLConnection conn = url.openConnection();
			// conn.connect();
			Jsoup.connect(testURL).get();
		} catch (MalformedURLException e) {
			isValid = false;
			System.out.println("The URL is not in a valid form.");
		} catch (IllegalArgumentException e) {
			isValid = false;
			System.out.println("The connection couldn't be established.");
		} catch (UnknownHostException e) {
			isValid = false;
			System.out.println("The connection couldn't be established.");
		}
		return isValid;
	}

	public static void readFromUrl(String testURL, String htmlFile)
			throws Exception {
		if (checkURL(testURL)) {
			// URL url = new URL(testURL);
			// URLConnection conn = url.openConnection();
			Document doc = Jsoup.connect(testURL).get();
			String inputLine = doc.html();

			// // BufferedReader in = new BufferedReader(new InputStreamReader(
			// // conn.getInputStream()));
			// String inputLine;
			// while ((inputLine = in.readLine()) != null) {
			String[] inputArray = inputLine.split("\\s+");
			String newString = "";
			for (String word : inputArray) {
				newString += compareURLWordsToTxt(word, satFile);
			}
			writeStyledLineToHTML(newString, htmlFile);
		}

		else {
			throw new IOException("Url is not valid.");
		}
	}

	public static String compareURLWordsToTxt(String word, String file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = reader.readLine()) != null) {
				if (word.equalsIgnoreCase(line.trim())) {
					reader.close();
					return "<mark>" + word + "</mark>" + " ";
				} else if (word.toLowerCase().contains(line.trim().toLowerCase())) {
					reader.close();
					String newWord = "<mark>" + line.trim()
							+ "</mark> ";
					return word.replaceAll(line.trim(), newWord);
				}
			}
			reader.close();
			return word + " ";

		} catch (IOException ioe) {
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
