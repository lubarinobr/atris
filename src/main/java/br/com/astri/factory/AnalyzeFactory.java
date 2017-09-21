package br.com.astri.factory;

import br.com.astri.model.Context;
import br.com.astri.model.Document;
import br.com.astri.model.RDM;

public class AnalyzeFactory {

	public static Context getContext(String context) {
		if(context.equalsIgnoreCase("0")) {
			return new Document();
		
		}else if(context.equalsIgnoreCase("1")) {
			RDM rdm = new RDM();
			rdm.setFile("portlet.txt");
			return rdm;
		}else {
			return null;
		}
	}
}
