package br.com.astri;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.astri.analyze.AnalyzeText;

public class AnalyzeTextTest {

	private AnalyzeText analyze;
	
	private String text;
	
	@Before
	public void beforeStart(){
		this.analyze = new AnalyzeText();
	}
	
	@Test
	public void testTextIsNotRDM() {
		this.text = analyze.analyzeToRDM("uma coisa de nova");
		assertNotNull(text);
		assertFalse(text.trim().isEmpty());
		assertEquals("Não entendo ainda esse comando", text);
	}
	
	@Test
	public void testTextIsRDM() {
		this.text = this.analyze.analyzeToRDM("quero uma rdm");
		assertNotNull(text);
		assertFalse(this.text.trim().isEmpty());
		
	}

}
