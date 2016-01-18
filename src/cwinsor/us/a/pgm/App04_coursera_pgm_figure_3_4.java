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
 * @author cwinsor
 *
 */
public class App04_coursera_pgm_figure_3_4 {


	public App04_coursera_pgm_figure_3_4() {
	}

	/**
	 * run
	 */
	public void run() {

		/* add a mapping function to the construction of a random variable - future */
		/* a random variable instance, and a distribution instance are essentially the same thing */
		/* the assignment of 'values' to a random variable or a distribution is replaced by the mapping function */
		/* a joint distribution is a specific case of a random variable - in the case of a
		 * joint distribution the overall sum is 1
		 * a conditional distribution is a specific case of a random variable - in the case of
		 * a conditional distribution the sum over the condition-ing variables = 1.0
		 * There is also the third case of a random variable - an 'uncontrolled or unconstrained' type - where it
		 * does not necessarily sum to anything.
		 * 
		 * Question: Definition vs instance:  Does the definition necessarily establish which of the sub-classes ?
		 * Answer: I would think absolutely yes - the sub-class is established at the time of definition.
		 */

		// define random variables and events
		RandomVariableDefinition RVDEF_DIFFICULTY = new RandomVariableDefinition("RVDEF_DIFFICULTY", Arrays.asList("d0", "d1"));
		RandomVariableDefinition RVDEF_INTELLIGENCE = new RandomVariableDefinition("RVDEF_INTELLIGENCE", Arrays.asList("i0", "i1"));
		RandomVariableDefinition RVDEF_GRADE = new RandomVariableDefinition("RVDEF_GRADE", Arrays.asList("g1", "g2", "g3"));
		RandomVariableDefinition RVDEF_LETTER = new RandomVariableDefinition("RVDEF_LETTER", Arrays.asList("l0", "l1"));
		RandomVariableDefinition RVDEF_SAT = new RandomVariableDefinition("RVDEF_SAT", Arrays.asList("s0", "s1"));

		// instance random variables
		/* false - random variable is defined as a function on some distribution ???
		 * Assuming that is true - the constructor should take in a mapping function as one
		 * of the parameters.   The other parameter would be (a list of variable-defs??)
		 * The degenerate case is to enumerate (case switch) the events and return the pre-defined value.
		 * A number of good examples exist, but would return an Object (type TBD).
		 */
		//		RandomVariableInstance difficulty = new RandomVariableInstance("difficulty", RV_DEF_DIFFICULTY);
		// here we want to set the callback function that defines the behavior
		//	difficulty.set(difficulty.eventRef("d0"), 0.6);
		//	difficulty.set(difficulty.eventRef("d0"), 0.4);
		//		RandomVariableInstance intelligence = new RandomVariableInstance("intelligence", RV_DEF_INTELLIGENCE);
		//		RandomVariableInstance grade = new RandomVariableInstance("grade", RV_DEF_GRADE);


	//	DistributionDefinition DIST_DEF_DIFFICULTY = new DistributionDefinition("DIST_DEF_DIFFICULTY", RV_DEF_DIFFICULTY, Arrays.asList());
	//	DistributionDefinition DIST_DEF_GRADE = new DistributionDefinition("DIST_DEF_GRADE", RV_DEF_GRADE, Arrays.asList(RV_DEF_DIFFICULTY, RV_DEF_INTELLIGENCE));
	//	DistributionDefinition DIST_DEF_INTELLIGENCE = new DistributionDefinition("DIST_DEF_INTELLIGENCE", RV_DEF_GRADE, Arrays.asList(RV_DEF_DIFFICULTY, RV_DEF_INTELLIGENCE));
	//	DistributionDefinition DIST_DEF_LETTER = new DistributionDefinition("DIST_DEF_LETTER", RV_DEF_LETTER, Arrays.asList(RV_DEF_GRADE));
	//	DistributionDefinition DIST_DEF_SAT = new DistributionDefinition("DIST_DEF_SAT", RV_DEF_SAT, Arrays.asList(RV_DEF_INTELLIGENCE));



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
		App04_coursera_pgm_figure_3_4 theApp = new App04_coursera_pgm_figure_3_4();
		theApp.run();
		System.out.println("---done---");

	}
}
