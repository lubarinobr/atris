package br.com.astri.learning;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import com.sun.corba.se.spi.orbutil.fsm.Input;

import opennlp.tools.doccat.DoccatFactory;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.namefind.BioCodec;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

public class LearningCate {

	public static void main(String[] args) throws IOException {
		trainModel();
		classifyNewDocument("Me dar uma documento");
	}
	
	public static void trainModel() throws IOException {
		InputStreamFactory in = new MarkableFileInputStreamFactory(new File("/home/lubarino/Documents/nlp/document-cate.txt"));
		
		ObjectStream<String> lineStream = new PlainTextByLineStream(in, StandardCharsets.UTF_8);
		ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);
		
		TrainingParameters params = new TrainingParameters();
		params.put(TrainingParameters.ITERATIONS_PARAM, 70);
		params.put(TrainingParameters.CUTOFF_PARAM, 1);
		
		DoccatModel model = DocumentCategorizerME.train("pt-br", sampleStream,params,new DoccatFactory());
		
		OutputStream modelOut = new BufferedOutputStream(new FileOutputStream("/home/lubarino/Documents/nlp/document-cate.bin"));
		model.serialize(modelOut);
		
		modelOut.close();
		System.out.println("Finalizou");
	}
	
	public static void classifyNewDocument(String texto) {
		try(InputStream stream = new FileInputStream(new File("/home/lubarino/Documents/nlp/document-cate.bin"))) {
			
			DoccatModel model = new DoccatModel(stream);
			
			DocumentCategorizerME me = new DocumentCategorizerME(model);
			
			final WhitespaceTokenizer tokenizer = WhitespaceTokenizer.INSTANCE;
			final String[] text = tokenizer.tokenize(texto);
			
			double[] outcomes = me.categorize(text);
			
			String categoria = me.getBestCategory(outcomes);
			System.out.println(categoria);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
