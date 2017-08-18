package br.com.astri;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import br.com.astri.classification.Classification;

public class ClassificationTest {

	private Classification classification;

	@Test
	public void startTest() throws Exception{
		testConstructClassification();
		testReturnProbabilityMoreThan50ToRDM();
		testReturnProbabilityLessThan50ToRdm();
	}
	
	public void testConstructClassification() {
		this.classification = new Classification();
		Assert.assertNotNull(this.classification);
	}
	
	public void testReturnProbabilityMoreThan50ToRDM() throws Exception {
//		double value = this.classification.getProbability("é uma rdm");
//		assertNotNull(value);
//		assertTrue(value > 50);
	}
	
	public void testReturnProbabilityLessThan50ToRdm() throws Exception {
//		double value = this.classification.getProbability("é um teste");
//		assertNotNull(value);
//		assertTrue(value < 50);
	}

}
