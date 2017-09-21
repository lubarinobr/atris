package br.com.astri.model;

public class Document implements Context {

	private static final long serialVersionUID = 6207152202944371187L;

	@Override
	public String getMessage() {
		return "Qual tipo de documento você necessita ? <br> <ul><li>RDM</li></ul>";
	}

	@Override
	public void setFile(String file) throws Exception {
		throw new Exception("No implemented");
	}

}
