package br.com.astri.analyze;

import java.io.InputStream;
import java.util.Properties;

public class AnalyzeText {

	private Properties properties = new Properties();
	private InputStream input = null;
	
	public String analyzeToRDM(String message) {
		
		if(message.contains("RDM")) {
			
			if(message.contains("portlet"))
				return getTextRDMPortlet();
			
			return "Qual tipo de texto você deseja ? ";
		
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
