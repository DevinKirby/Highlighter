package highlightsWords;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	public void shouldWriteToFileIfFileNameIsValid()
			throws FileNotFoundException {
		String testMessage = "Testing file IO";
		String fileName = "C:\\Users\\priya\\Desktop\\test.txt";
		HighlightSATWords.writeStyledLineToHTML(testMessage, fileName);
		int fileLength = 0;
		try {
			FileInputStream in = new FileInputStream(fileName);
			if (in.read() != -1) {
				fileLength++;
				in.close();
			}
		} catch (IOException ioe) {
			System.out.println("Error reading file");
		}
		assertTrue(fileLength > 0);
	}

	@Test
	public void shouldThrowExceptionIfFileNameIsInvalid()
			throws FileNotFoundException {
		HighlightSATWords.writeStyledLineToHTML("Test String", "dummy");
	}
	
//	@Test
//	public void shouldReadFromUrlAndWriteToFileIfFileNameIsValid() {
//		String testUrl = "Test URL";
//		String fileName = ;
//	}

}
