package highlightsWords;

import static org.junit.Assert.*;
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
		assertEquals("<mark>plummet</mark>",
				HighlightSATWords.compareURLWordsToTxt("plummet"));
	}

	@Test
	public void shouldReturnOriginalStyleForNonSATWord() {
		assertEquals("simple", HighlightSATWords.compareURLWordsToTxt("simple"));
	}

}
