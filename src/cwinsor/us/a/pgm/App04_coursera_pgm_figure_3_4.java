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
		RandomVariableDefinition RV_DEF_DIFFICULTY = new RandomVariableDefinition("RV_DEF_DIFFICULTY", Arrays.asList("d0", "d1"));
		RandomVariableDefinition RV_DEF_INTELLIGENCE = new RandomVariableDefinition("RV_DEF_INTELLIGENCE", Arrays.asList("i0", "i1"));
		RandomVariableDefinition RV_DEF_GRADE = new RandomVariableDefinition("RV_DEF_GRADE", Arrays.asList("g1", "g2", "g3"));
		RandomVariableDefinition RV_DEF_LETTER = new RandomVariableDefinition("RV_DEF_LETTER", Arrays.asList("l0", "l1"));
		RandomVariableDefinition RV_DEF_SAT = new RandomVariableDefinition("RV_DEF_SAT", Arrays.asList("s0", "s1"));

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



		LocalProbabilityModel distributionDifficulty = new LocalProbabilityModel("distributionDifficulty");
		distributionDifficulty.setTargetAndDependencies(RV_DEF_DIFFICULTY, null);
		distributionDifficulty.setValue("d0", 0.6);
		distributionDifficulty.setValue("d1", 0.4);

		LocalProbabilityModel distributionIntelligence = new LocalProbabilityModel("distributionIntelligence");
		distributionIntelligence.setTargetAndDependencies(RV_DEF_INTELLIGENCE, null);
		distributionIntelligence.setValue("i0", 0.7);
		distributionIntelligence.setValue("i1", 0.3);

		LocalProbabilityModel distributionGrade = new LocalProbabilityModel("distributionGrade");
		distributionGrade.setTargetAndDependencies(RV_DEF_GRADE, Arrays.asList(distributionDifficulty, distributionIntelligence));
		distributionGrade.setValue("g1", Arrays.asList(distributionIntelligence.event("i0"),distributionDifficulty.event("d0")), 0.3);
		distributionGrade.setValue("g2", Arrays.asList(distributionIntelligence.event("i0"),distributionDifficulty.event("d0")), 0.4);
		distributionGrade.setValue("g3", Arrays.asList(distributionIntelligence.event("i0"),distributionDifficulty.event("d0")), 0.3);
		distributionGrade.setValue("g1", Arrays.asList(distributionIntelligence.event("i0"),distributionDifficulty.event("d1")), 0.05);
		distributionGrade.setValue("g2", Arrays.asList(distributionIntelligence.event("i0"),distributionDifficulty.event("d1")), 0.25);
		distributionGrade.setValue("g3", Arrays.asList(distributionIntelligence.event("i0"),distributionDifficulty.event("d1")), 0.7);
		distributionGrade.setValue("g1", Arrays.asList(distributionIntelligence.event("i1"),distributionDifficulty.event("d0")), 0.9);
		distributionGrade.setValue("g2", Arrays.asList(distributionIntelligence.event("i1"),distributionDifficulty.event("d0")), 0.08);
		distributionGrade.setValue("g3", Arrays.asList(distributionIntelligence.event("i1"),distributionDifficulty.event("d0")), 0.02);
		distributionGrade.setValue("g1", Arrays.asList(distributionIntelligence.event("i1"),distributionDifficulty.event("d1")), 0.5);
		distributionGrade.setValue("g2", Arrays.asList(distributionIntelligence.event("i1"),distributionDifficulty.event("d1")), 0.3);
		distributionGrade.setValue("g3", Arrays.asList(distributionIntelligence.event("i1"),distributionDifficulty.event("d1")), 0.2);

		LocalProbabilityModel distributionLetter = new LocalProbabilityModel("distributionLetter");
		distributionLetter.setTargetAndDependencies(RV_DEF_LETTER, Arrays.asList(distributionGrade));
		distributionLetter.setValue("l0",  Arrays.asList(distributionGrade.event("g1")), 0.1);
		distributionLetter.setValue("l1",  Arrays.asList(distributionGrade.event("g1")), 0.9);
		distributionLetter.setValue("l0",  Arrays.asList(distributionGrade.event("g2")), 0.4);
		distributionLetter.setValue("l1",  Arrays.asList(distributionGrade.event("g2")), 0.6);
		distributionLetter.setValue("l0",  Arrays.asList(distributionGrade.event("g3")), 0.99);
		distributionLetter.setValue("l1",  Arrays.asList(distributionGrade.event("g3")), 0.01);

		LocalProbabilityModel distributionSat = new LocalProbabilityModel("distributionSat");
		distributionSat.setTargetAndDependencies(RV_DEF_SAT, Arrays.asList(distributionIntelligence));
		distributionSat.setValue("s0",  Arrays.asList(distributionIntelligence.event("i0")), 0.95);
		distributionSat.setValue("s1",  Arrays.asList(distributionIntelligence.event("i0")), 0.05);
		distributionSat.setValue("s0",  Arrays.asList(distributionIntelligence.event("i0")), 0.2);
		distributionSat.setValue("s1",  Arrays.asList(distributionIntelligence.event("i0")), 0.8);




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
