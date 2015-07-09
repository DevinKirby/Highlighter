package highlightsWords;

import static org.junit.Assert.*;
import highlighter.HighlightSATWords;

import org.junit.Test;

public class HighlightSATWordsTest {

	@Test
	public void shouldReturnFalseForInvalidURL() {
		assertEquals(false, HighlightSATWords.checkURL("https://wwww.google.com/"));
	}
	
	@Test
	public void shouldReturnTrueForValidURL() {
		assertEquals(true, HighlightSATWords.checkURL("https://www.google.com/"));
	}

}
