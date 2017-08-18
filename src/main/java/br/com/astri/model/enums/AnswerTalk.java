package br.com.astri.model.enums;

public enum AnswerTalk {

	DOCUMENT_TYPE("Que tipo de documento você deseja ? Completo, Portlet ou middleware ?"),
	NO_UNDERSTAND("Não entendo ainda esse comando"),
	PROBLEM("Algo de errado aconteceu");
	
	private String type;
	
	private AnswerTalk(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
