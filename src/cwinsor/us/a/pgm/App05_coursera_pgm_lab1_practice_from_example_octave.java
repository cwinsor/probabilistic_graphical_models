/**
 * 
 */
package cwinsor.us.a.pgm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This file contains a java-based implementation of the Octave-based exercise
 * which comes as part of Lab 1 of the Coursera Stanford Graphical Probabilistic Models.
 */
public class App05_coursera_pgm_lab1_practice_from_example_octave {


	public App05_coursera_pgm_lab1_practice_from_example_octave() {
	}

	/**
	 * run
	 */
	public void run() {

		// % FACTORS.INPUT(1) contains P(X_1)
		//	FACTORS.INPUT(1) = struct('var', [1], 'card', [2], 'val', [0.11, 0.89]);
		//
		// the intent is to create a "factor" X1 which is strictly a prior with two values P(X1=x1_0)=0.11 and P(X1=x1_1)=0.89
		RandomVariableDefinition RVDEF_X1 = new RandomVariableDefinition("RVDEF_X1", Arrays.asList("x1_0", "x1_1"));
		LocalProbabilityModel X1 = new LocalProbabilityModel("X1");
		X1.setTargetAndDependencies(RVDEF_X1, null);
		X1.setValue("x0", 0.11);
		X1.setValue("x1", 0.89);
		System.out.println(X1.toString());


		// % FACTORS.INPUT(2) contains P(X_2 | X_1)
		// FACTORS.INPUT(2) = struct('var', [2, 1], 'card', [2, 2], 'val', [0.59, 0.41, 0.22, 0.78]);
		//
		// the intent is to create a "factor" X2 with cardinality of 2, which is conditional on X1 with cardinality of 2
		RandomVariableDefinition RVDEF_X2 = new RandomVariableDefinition("RVDEF_X2", Arrays.asList("x2_0", "x2_1"));
		LocalProbabilityModel X2 = new LocalProbabilityModel("X2");
		X2.setTargetAndDependencies(RVDEF_X2, Arrays.asList(X1));
		X2.setValue("x2_0", Arrays.asList(X1.event("x1_0")), 0.59);
		X2.setValue("x2_1", Arrays.asList(X1.event("x1_0")), 0.41);
		X2.setValue("x2_0", Arrays.asList(X1.event("x1_1")), 0.22);
		X2.setValue("x2_1", Arrays.asList(X1.event("x1_1")), 0.78);


		// % FACTORS.INPUT(3) contains P(X_3 | X_2)
		// FACTORS.INPUT(3) = struct('var', [3, 2], 'card', [2, 2], 'val', [0.39, 0.61, 0.06, 0.94]);
		//
		// The intent is to create a "factor" X3 with cardinality of 2, which is conditional on X2 with cardinality of 2
		RandomVariableDefinition RVDEF_X3 = new RandomVariableDefinition("RVDEF_X3", Arrays.asList("x3_0", "x3_1"));
		LocalProbabilityModel X3 = new LocalProbabilityModel("X3");
		X3.setTargetAndDependencies(RVDEF_X3, Arrays.asList(X2));
		X3.setValue("x3_0", Arrays.asList(X2.event("x2_0")), 0.39);
		X3.setValue("x3_1", Arrays.asList(X2.event("x2_0")), 0.61);
		X3.setValue("x3_0", Arrays.asList(X2.event("x2_1")), 0.06);
		X3.setValue("x3_1", Arrays.asList(X2.event("x2_1")), 0.94);

		// % Factor Product
		// % FACTORS.PRODUCT = FactorProduct(FACTORS.INPUT(1), FACTORS.INPUT(2));
		// % The factor defined here is correct to 4 decimal places.
		// FACTORS.PRODUCT = struct('var', [1, 2], 'card', [2, 2], 'val', [0.0649, 0.1958, 0.0451, 0.6942]);
		//
		// The intent is to compute the "factor product" of X_1 and X_2.
		// Note that X_2 includes the "conditioning" variable X_1
		// so in effect we are performing...  P(X1) * P(X2|X1) -----> P(X1,X2)
		// or more specifically for example...   P(X1=x1_0) * P(X2=x2_0 | (X1=x1_0)) ---->  P(X1=x1_0, X2=x2_0)
		// These have common X_1 so presumably this would be X_1 which is a 1x2 (dot) X_2 which is a 2x2  resulting in a 






		// define random variables and events
		RandomVariableDefinition RVDEF_DIFFICULTY = new RandomVariableDefinition("RVDEF_DIFFICULTY", Arrays.asList("d0", "d1"));
		RandomVariableDefinition RVDEF_INTELLIGENCE = new RandomVariableDefinition("RVDEF_INTELLIGENCE", Arrays.asList("i0", "i1"));
		RandomVariableDefinition RVDEF_GRADE = new RandomVariableDefinition("RVDEF_GRADE", Arrays.asList("g1", "g2", "g3"));
		RandomVariableDefinition RVDEF_LETTER = new RandomVariableDefinition("RVDEF_LETTER", Arrays.asList("l0", "l1"));
		RandomVariableDefinition RVDEF_SAT = new RandomVariableDefinition("RVDEF_SAT", Arrays.asList("s0", "s1"));



		LocalProbabilityModel difficulty = new LocalProbabilityModel("difficulty");
		difficulty.setTargetAndDependencies(RVDEF_DIFFICULTY, null);
		difficulty.setValue("d0", 0.6);
		difficulty.setValue("d1", 0.4);

		LocalProbabilityModel intelligence = new LocalProbabilityModel("intelligence");
		intelligence.setTargetAndDependencies(RVDEF_INTELLIGENCE, null);
		intelligence.setValue("i0", 0.7);
		intelligence.setValue("i1", 0.3);

		LocalProbabilityModel grade = new LocalProbabilityModel("grade");
		grade.setTargetAndDependencies(RVDEF_GRADE, Arrays.asList(difficulty, intelligence));
		grade.setValue("g1", Arrays.asList(intelligence.event("i0"),difficulty.event("d0")), 0.3);
		grade.setValue("g2", Arrays.asList(intelligence.event("i0"),difficulty.event("d0")), 0.4);
		grade.setValue("g3", Arrays.asList(intelligence.event("i0"),difficulty.event("d0")), 0.3);
		grade.setValue("g1", Arrays.asList(intelligence.event("i0"),difficulty.event("d1")), 0.05);
		grade.setValue("g2", Arrays.asList(intelligence.event("i0"),difficulty.event("d1")), 0.25);
		grade.setValue("g3", Arrays.asList(intelligence.event("i0"),difficulty.event("d1")), 0.7);
		grade.setValue("g1", Arrays.asList(intelligence.event("i1"),difficulty.event("d0")), 0.9);
		grade.setValue("g2", Arrays.asList(intelligence.event("i1"),difficulty.event("d0")), 0.08);
		grade.setValue("g3", Arrays.asList(intelligence.event("i1"),difficulty.event("d0")), 0.02);
		grade.setValue("g1", Arrays.asList(intelligence.event("i1"),difficulty.event("d1")), 0.5);
		grade.setValue("g2", Arrays.asList(intelligence.event("i1"),difficulty.event("d1")), 0.3);
		grade.setValue("g3", Arrays.asList(intelligence.event("i1"),difficulty.event("d1")), 0.2);

		LocalProbabilityModel letter = new LocalProbabilityModel("letter");
		letter.setTargetAndDependencies(RVDEF_LETTER, Arrays.asList(grade));
		letter.setValue("l0",  Arrays.asList(grade.event("g1")), 0.1);
		letter.setValue("l1",  Arrays.asList(grade.event("g1")), 0.9);
		letter.setValue("l0",  Arrays.asList(grade.event("g2")), 0.4);
		letter.setValue("l1",  Arrays.asList(grade.event("g2")), 0.6);
		letter.setValue("l0",  Arrays.asList(grade.event("g3")), 0.99);
		letter.setValue("l1",  Arrays.asList(grade.event("g3")), 0.01);

		LocalProbabilityModel sat = new LocalProbabilityModel("sat");
		sat.setTargetAndDependencies(RVDEF_SAT, Arrays.asList(intelligence));
		sat.setValue("s0",  Arrays.asList(intelligence.event("i0")), 0.95);
		sat.setValue("s1",  Arrays.asList(intelligence.event("i0")), 0.05);
		sat.setValue("s0",  Arrays.asList(intelligence.event("i0")), 0.2);
		sat.setValue("s1",  Arrays.asList(intelligence.event("i0")), 0.8);

	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("---start---");
		App05_coursera_pgm_lab1_practice_from_example_octave theApp = new App05_coursera_pgm_lab1_practice_from_example_octave();
		theApp.run();
		System.out.println("---done---");

	}
}
