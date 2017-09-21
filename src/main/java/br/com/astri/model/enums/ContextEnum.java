package br.com.astri.model.enums;

public enum ContextEnum {

	DOCUMENT("/home/lubarino/Documents/nlp/document.bin"),
	DOCUMENT_TYPE("/home/lubarino/Documents/nlp/document-type.bin");
	
	private String value;
	
	private ContextEnum(final String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
