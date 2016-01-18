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
import java.util.Random;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

/**
 * @author cwinsor
 *
 */
public class App02_weka_neural_network {


	public App02_weka_neural_network() {
		}

	/**
	 * run
	 */
	public void run() {
		
		 NNConditionalEstimator newEst = new NNConditionalEstimator();

		   long seed = 5;
		// Create 50 random points and add them
		   Random r = new Random(seed );
		   for(int i = 0; i < 50; i++) {
		     int x = Math.abs(r.nextInt() % 100);
		     int y = Math.abs(r.nextInt() % 100);
		     System.out.println("# " + x + "  " + y);
		     newEst.addValue(x, y, 1);
		   }

		   // Pick a random conditional value
		   int cond = Math.abs(r.nextInt() % 100);
		   System.out.println("## Conditional = " + cond);

		   // Print the probabilities conditional on that value
		   Estimator result = newEst.getEstimator(cond);
		   for(int i = 0; i <= 100; i+= 5) {
		     System.out.println(" " + i + "  " + result.getProbability(i));
		   }
		 
		   
		   
		//  BayesNet bayesNet = new BayesNet();
		//   bayesNet.
		
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		App02_weka_neural_network theApp = new App02_weka_neural_network();
		theApp.run();
			System.out.printf("\n---done---");

	}


}
