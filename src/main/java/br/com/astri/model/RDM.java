package br.com.astri.model;

import br.com.astri.nlp.Util;

public class RDM implements Context {

	private static final long serialVersionUID = 7083407082626288711L;
	
	private String file;
	
	@Override
	public String getMessage() {
		try {
			return Util.readFile(this.file);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void setFile(String file) {
		this.file = file;
	}

	
}
