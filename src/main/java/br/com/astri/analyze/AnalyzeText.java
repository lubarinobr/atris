package br.com.astri.analyze;

import java.io.IOException;

import br.com.astri.model.enums.AnswerTalk;

public class AnalyzeText extends AnalyzeAbstract {

	@Override
	public String analyze(String message) {
		double probability[];
		try {
			
			this.classification.setFile("rdm.arff");
			this.classification.loadFileToClassification();
			probability = this.classification.getProbability(message);
			
			if((probability[0] * 100) > 50.0) {
				return AnswerTalk.DOCUMENT_TYPE.getType();
			}else {
				return AnswerTalk.NO_UNDERSTAND.getType();
			}

		} catch (Exception e) {
			return AnswerTalk.PROBLEM.getType();
		}
		
	}
	
	private String getTextRDMPortlet(String typeRDM) throws Exception {
		
		return readFile(typeRDM);
	}
	
	public String analyzeRDMType(String text) throws Exception {
		this.classification.setFile("tipoRDM.arff");
		this.classification.loadFileToClassification();
		double[] prob = this.classification.getProbability(text);
		return getTypeTextFromProbability(prob);
	}
	
	private String getTypeTextFromProbability(double[] prob) throws Exception {
		prob[0] = prob[0] * 100;
		prob[1] = prob[1] * 100;
		prob[2] = prob[2] * 100;
		
		String file = "";
		
		if(prob[0] > 50) {
			file = "portlet.txt";
		
		}else if(prob[1] > 50) {
			file = "middleware.txt";
		
		}else if(prob[2] > 50) {
			file = "todos.properties";
		
		}else {
			return "Arquivo não encontrado";
		}
		
		return getTextRDMPortlet(file);
	}

}
