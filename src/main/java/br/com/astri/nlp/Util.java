package br.com.astri.nlp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;

import br.com.astri.model.enums.ContextEnum;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.Span;

public class Util {

	public static String[] tokenizer(final String text) {
		final WhitespaceTokenizer tokenizer = WhitespaceTokenizer.INSTANCE;

		return tokenizer.tokenize(text);
	}

	public static Collection<Span> getSpan(String[] text, ContextEnum documentEnum) {
		Collection<Span> spans = null;
		try(InputStream stream = new FileInputStream(documentEnum.getValue())) {
			final TokenNameFinderModel model = new TokenNameFinderModel(stream);

			NameFinderME finderME = new NameFinderME(model);

			spans = Arrays.asList(finderME.find(text));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return spans;
	}
	
	public static String readFile(final String f) throws Exception {
		final File file = new File(ClassLoader.getSystemResource(f).toURI());
	    final FileReader fr = new FileReader(file);
	    final BufferedReader br = new BufferedReader(fr);
	    final StringBuilder builder = new StringBuilder();
	    
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
