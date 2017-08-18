package br.com.astri.classification;

public interface IClassification {

	public double getProbability(String text) throws Exception;
	public void setFile(String file);
}
