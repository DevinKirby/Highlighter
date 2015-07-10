package highlightsWords;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import highlighter.HighlightSATWords;

import org.junit.Test;

public class HighlightSATWordsTest {

	@Test
	public void shouldReturnFalseForInvalidURL() {
		assertEquals(false,
				HighlightSATWords.checkURL("http://wwww.google.com/"));
	}

	@Test
	public void shouldReturnFalseForMalformedURL() {
		assertEquals(false, HighlightSATWords.checkURL("wwww.google.com/"));
	}

	@Test
	public void shouldReturnTrueForValidURL() {
		assertEquals(true, HighlightSATWords.checkURL("http://www.google.com/"));
	}

	@Test
	public void shouldReturnHighlightedSATWord() {
		assertEquals("<mark>plummet</mark> ",
				HighlightSATWords.compareURLWordsToTxt("plummet",
						HighlightSATWords.satFile));
	}

	@Test
	public void shouldReturnOriginalStyleForNonSATWord() {
		assertEquals("simple ", HighlightSATWords.compareURLWordsToTxt(
				"simple", HighlightSATWords.satFile));
	}

	@Test
	public void shouldThrowException() throws FileNotFoundException,
			IOException {
		HighlightSATWords.compareURLWordsToTxt("simple", "badFileName.txt");
	}

	@Test
	public void shouldThrowExceptionIfFileNameIsInvalid()
			throws FileNotFoundException {
		HighlightSATWords.writeStyledLineToHTML("Test String", "dummy");
	}

	@Test
	public void shouldReadFromUrlAndWriteToFile() throws IOException {
		String testUrl = "http://www.oracle.com";
		String fileName = "C:\\Users\\priya\\Desktop\\test.html";
		HighlightSATWords.readFromUrl(testUrl, fileName);
		int fileLength = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			if (br.readLine() != null) {
				fileLength++;
			}
			br.close();

		} catch (IOException ioe) {
			System.out.println("Error reading file");
		}
		assertTrue(fileLength > 0);
	}

	@Test(expected = IOException.class)
	public void shouldThrowExceptionForBadUrl() throws IOException {
		String fileName = "C:\\Users\\priya\\Desktop\\test.html";
		HighlightSATWords.readFromUrl("www.someurl.com", fileName);
	}

}

// @Test
// public void shouldWriteToFileIfFileNameIsValid()
// throws FileNotFoundException {
// String testMessage = "Testing file IO";
// String fileName = "C:\\Users\\priya\\Desktop\\test.txt";
// HighlightSATWords.writeStyledLineToHTML(testMessage, fileName);
// int fileLength = 0;
// try {
// BufferedReader br = new BufferedReader(new FileReader(fileName));
// if (br.readLine() != null) {
// fileLength++;
// }
// br.close();
//
// } catch (IOException ioe) {
// System.out.println("Error reading file");
// }
// assertTrue(fileLength > 0);
// }