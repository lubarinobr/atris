package br.com.astri.model.enums;

public enum DocumentEnum {

	RDM("rdm.arff");
	
	private String file;
	
	private DocumentEnum(final String file) {
		this.file = file;
	}
	
	public String getFile() {
		return file;
	}
	
}
