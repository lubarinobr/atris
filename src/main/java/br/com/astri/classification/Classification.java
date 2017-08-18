package br.com.astri.classification;

import org.apache.commons.lang3.StringUtils;

import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.stemmers.LovinsStemmer;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class Classification implements IClassification {

	private Instances instances;
	
	private StringToWordVector filter;
	
	private FilteredClassifier classifier;
	
	private Instance test;
	
	private String file = "rdm.arff"; 
	
	public Classification() {
		loadFileToClassification();
	}
	
	@Override
	public void loadFileToClassification(){
		DataSource dataSource = new DataSource(Classification.class.getClassLoader().getResourceAsStream(file));
		try {
			this.instances = dataSource.getDataSet();
			this.instances.setClassIndex(instances.numAttributes() - 1);
			this.test = new DenseInstance(2);
			this.test.setDataset(this.instances);
			filterWorld();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void filterWorld() throws Exception {
		
		NaiveBayes nb = new NaiveBayes();
		this.filter = new StringToWordVector();
		this.filter.setInputFormat(this.instances);
		this.filter.setIDFTransform(true);
		
		LovinsStemmer lovinsStemmer = new LovinsStemmer();
		
		this.filter.setStemmer(lovinsStemmer);
		this.filter.setLowerCaseTokens(true);
		
		this.classifier = new FilteredClassifier();
		this.classifier.setFilter(this.filter);
		this.classifier.setClassifier(nb);
		this.classifier.buildClassifier(this.instances);
		
	}
	
	public double[] getProbability(String text) throws Exception {
		
		startLearn(text);
		
		if(this.classifier != null){
			
			return this.classifier.distributionForInstance(this.test);
			
		}else{
			return new double[0];
		}
		
	}
	
	private void startLearn(String text) throws Exception {
		
		if(StringUtils.isNoneEmpty(text)){
			this.test.setValue(0,text);
		}else {
			throw new Exception("Texto Vázio");
		}
	}

	@Override
	public void setFile(String file) {
		this.file = file;
	}

}
