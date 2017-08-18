package br.com.astri.analyze;

import java.io.InputStream;
import java.util.Properties;

import br.com.astri.classification.Classification;

public class AnalyzeText {

	private Properties properties = new Properties();
	private InputStream input = null;
	private Classification classification = new Classification();
	
	public String analyzeToRDM(String message) {
		
		double probability = 0.0;
		
		try {
			probability = classification.getProbability(message);
		} catch (Exception e) {
			return "Algo de errado aconteceu";
		}
		
		
		if(probability > 50.0) {
			
			return getTextRDMPortlet();
			
		
		}else {
			
			return "Não entendo ainda esse comando";
		}
		
	}
	
	private String getTextRDMPortlet() {
		
		loadProperties("portlet.properties");
		return properties.getProperty("text");
	}
	
	private void loadProperties(String prop) {
		
		try {
			
			input = AnalyzeText.class.getClassLoader().getResourceAsStream(prop);
			
			if(input == null)
				throw new Exception("Não foi possível encontrar esse arquivo");
			
			properties.load(input);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
