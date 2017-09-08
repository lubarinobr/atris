package br.com.astri.analyze;

import java.io.IOException;
import java.util.regex.Pattern;

import br.com.astri.model.Talk;
import br.com.astri.model.enums.AnswerTalk;

public class AnalyzeText extends AnalyzeAbstract {

	private String message;
	
	@Override
	public String analyze() {
		double probability[];
		try {
			
			this.classification.setFile("rdm.arff");
			this.classification.loadFileToClassification();
			probability = this.classification.getProbability(this.message);
			
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
	
	public String executeStep(Talk talk, int step) throws Exception {
		
		this.message = talk.getMessage();
		
		switch(step) {
			case 0:
				return analyze();
			case 1:
				return "Okay, preciso saber o número da RDM";
			case 2:
				getValidRDM();
				return "Agora preciso saber o caminho do svn";
				
			default:
				return "Hmmm, não entendi";
		}
		
	}
	
	private void getValidRDM() throws Exception {
		final Pattern pattern = Pattern.compile("[RDM]+[1-9]+");
		if(!pattern.matcher(this.message.toUpperCase()).matches()) {
			throw new Exception("RDM inválida");
		}
	}
	
	public String analyzeRDMType() throws Exception {
		this.classification.setFile("tipoRDM.arff");
		this.classification.loadFileToClassification();
		double[] prob = this.classification.getProbability(this.message);
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
		
		}else {
			return "Ops, não entendi qual tipo você deseja ?";
		}
		
		return getTextRDMPortlet(file);
	}

}
