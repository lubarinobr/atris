package br.com.astri.analyze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Stream;

import br.com.astri.classification.Classification;
import br.com.astri.classification.IClassification;

public abstract class AnalyzeAbstract {

	protected Properties properties = new Properties();
	protected InputStream input = null;
	protected IClassification classification = new Classification();
	private String message = "";

	public abstract String analyze();

	protected void loadProperties(String prop) {

		try {

			input = AnalyzeAbstract.class.getClassLoader().getResourceAsStream(prop);

			if(input == null)
				throw new Exception("Não foi possível encontrar esse arquivo");

			properties.load(input);

		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	protected String readFile(String f) throws Exception {
		File file = new File(ClassLoader.getSystemResource(f).toURI());
	    FileReader fr = new FileReader(file);
	    BufferedReader br = new BufferedReader(fr);
	    StringBuilder builder = new StringBuilder();
	    String line;
	    while((line = br.readLine()) != null){
	    	builder.append(line);
	    	builder.append(System.getProperty("line.separator"));
	    }
	    br.close();
	    fr.close();
	
	    return builder.toString();
	}
	
}
