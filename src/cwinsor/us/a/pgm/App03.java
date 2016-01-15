/**
 * 
 */
package cwinsor.us.a.pgm;

import weka.classifiers.bayes.BayesNet;
import weka.core.Instances;
import weka.estimators.Estimator;
import weka.estimators.NNConditionalEstimator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

/**
 * @author cwinsor
 *
 */
public class App03 {


	public App03() {
	}

	/**
	 * run
	 */
	public void run() {

		//	List<String> values = new ArrayList <String>();
		//	List<String> supplierNames = Arrays.asList("sup1", "sup2", "sup3");

		//values.add("Present");
		//values.add("NotPresent");
		int i = 5;


		//RandomVariable cancer = new RandomVariable();


		VariableDef CANCER_DEF = new VariableDef("CANCER_DEF", Arrays.asList("present", "notPresent"));
		RandomVariable cancer = new RandomVariable("cancer", CANCER_DEF);
		DistributionBc f_cancer = new DistributionBc("f_cancer", Arrays.asList(cancer));
		f_cancer.setValues(Arrays.asList(
				cancer.event("notPresent"), 0.11,
				cancer.event("present"), 0.89));



	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.printf("\n---start---");
		App03 theApp = new App03();
		theApp.run();
		System.out.printf("\n---done---");

	}


}
