package br.com.astri.model;

import java.io.Serializable;

public interface Context extends Serializable {

	public String getMessage();
	
	public void setFile(String file) throws Exception;
}
