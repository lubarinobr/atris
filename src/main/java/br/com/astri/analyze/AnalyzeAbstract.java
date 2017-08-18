package br.com.astri.analyze;

import java.io.InputStream;
import java.util.Properties;

import br.com.astri.classification.Classification;
import br.com.astri.classification.IClassification;

public abstract class AnalyzeAbstract {

	protected Properties properties = new Properties();
	protected InputStream input = null;
	protected IClassification classification = new Classification();

	public abstract String analyze(String text);

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

}
