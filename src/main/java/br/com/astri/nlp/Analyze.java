package br.com.astri.nlp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import br.com.astri.model.Talk;
import br.com.astri.model.enums.ContextEnum;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.Span;

public class Analyze implements IAnalyze {

	@Override
	public String getContext(Talk talk) {
		
		final String[] sentences = Util.tokenizer(talk.getMessage());
		final String value = getValue(sentences);
		
		return value;
		
	}
	
	private String getValue(String[] sentences ){
		String categoria = null;
		try(InputStream stream = new FileInputStream(new File("/home/lubarino/Documents/nlp/document-cate.bin"))) {
			
			final DoccatModel model = new DoccatModel(stream);
			
			final DocumentCategorizerME me = new DocumentCategorizerME(model);
			
			final double[] outcomes = me.categorize(sentences);
			
			categoria = me.getBestCategory(outcomes);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return categoria;
	}
	
	
}
