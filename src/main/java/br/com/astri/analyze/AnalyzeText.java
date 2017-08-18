package br.com.astri.analyze;

import br.com.astri.model.enums.AnswerTalk;

public class AnalyzeText extends AnalyzeAbstract {

	@Override
	public String analyze(String message) {
		
		double probability = 0.0;
		
		try {
			probability = classification.getProbability(message);
		} catch (Exception e) {
			return AnswerTalk.PROBLEM.getType();
		}
		
		
		if(probability > 50.0) {
			
			return getTextRDMPortlet();
			
		
		}else {
			
			return AnswerTalk.NO_UNDERSTAND.getType();
		}
		
	}
	
	private String getTextRDMPortlet() {
		
		loadProperties("portlet.properties");
		return properties.getProperty("text");
	}
	

}
