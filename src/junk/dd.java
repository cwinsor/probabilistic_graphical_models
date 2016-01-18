package junk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cwinsor.us.a.pgm.Event;
import cwinsor.us.a.pgm.RandomVariableDefinition;
import cwinsor.us.z.other.MyStatic;

public class dd {



	private String myName;

	// map Event to RandomVariable
	//private Map<Event,RandomVariable> eventToRandomVariable = new HashMap<Event,RandomVariable>();

	// epoch - transform matrix to linear array
//	private Map<RandomVariableInstance, Integer> epoch = new HashMap<RandomVariableInstance, Integer>();

	// linear array of values
	private Double[] value;
/*
	public DistributionDefinition(String name, List<RandomVariableInstance> list) {
		this.myName = name;
		//	this.randomVariableList = list;

		// create a local map from event to its (parent) random variable
		for (RandomVariableInstance rv : list) {
			for (Event e : rv.eventList()) {
				//				eventToRandomVariable.put(e, rv);
			}
		}

		// establish epoch for each variable
		// and total number of values represented
		Integer runningEpoch = 1;
		for (RandomVariableInstance rv : list) {
			epoch.put(rv,runningEpoch);
			runningEpoch = runningEpoch * rv.cardinality();
		}

		// create an array for variable values, and initialize
		value = new Double[runningEpoch];
		for (Integer i=0; i<value.length; i++) {
			value[i] = 0.0;
		}
	}
*/
	
	/* Constructor for the definition of a distribution.
	 * Parameters are name, the definition of the target random variable, and the list of definitions of non-target random variables
	 */
//	public DistributionDefinition(String name, RandomVariableDefinition targetDef, List<RandomVariableDefinition> nonTargetDef) {
//		// TODO Auto-generated constructor stub
	}

//	public Integer numRandomVariables() {
//		return epoch.size();
//	}

//	public Set<RandomVariableInstance> randomVariableSet() {
//		return epoch.keySet();
//	}

//	public	List<RandomVariableInstance> randomVariableList() {
//		Set<RandomVariableInstance> blah2 = randomVariableSet();
//		List<RandomVariableInstance> foo = new ArrayList<RandomVariableInstance>();
//		for (Object o : blah2) {
//			foo.add((RandomVariableInstance)o);
//		}
//		return foo;
//	}



//	public void setZeros() {
//		for (Integer i=0; i<value.length; i++) {
//			value[i] = 0.0;
//		}
//	}

//	public void setOnes() {
//		for (Integer i=0; i<value.length; i++) {
//			value[i] = 1.0;
//		}
//	}

/*
	public void setValues(List<Object> list) {

		// sanity check
		// the parameters should be:  (m) { (n)randomVariables, Double }
		if (list.size() != (numRandomVariables() + 1)) {
			MyStatic.errorHalt(String.format("ERROR - in setValues - asList.size() != (numRandomVariables+1) %d %d\n",
					list.size(), (3*value.length)));
		}

		// the parameter list contains:
		//  (M) entries of:
		//     { (N) random variable names,  Double }
		// where "M" is the the number of values to be set
		// where "N" is the number of random variables

		// walk through the list...
		Integer runningParamIndex = 0;
		while(runningParamIndex < list.size()) {
			// get next set of indices and value
			List<Object> subListObj = list.subList(runningParamIndex, runningParamIndex+numRandomVariables()+1);
			Object valueObj = list.get(runningParamIndex + numRandomVariables());
			runningParamIndex += numRandomVariables() + 1;

			// convert to linear array index
			Integer arrayIndex = toArrayIndex(subListObj);
			// cast Object to Double value
			Double val = castToDouble(valueObj);
			// assign
			value[arrayIndex] = val;
		}
	}
*/

	/**
	 * given an Object, checks and casts to Double
	 */
//	private Double castToDouble(Object valueObj) {
//		if (valueObj instanceof Double) {
//			Double valueD = (Double) valueObj;
//			return valueD;
//		} else {
//			MyStatic.errorHalt(String.format("ERROR - 2222 - unable to cast array item to Double - it is \"%s\"",valueObj.toString()));
//		}
//		return null;
//	}

	/**
	 * given a list of Events, return the offset into the linear array of the associated value
	 */
/*	private Integer toArrayIndex(List<Object> eventListObj) {

		List<Event> eventList = new ArrayList<Event>();

		// cast Objects to Events
		for (Object o : eventListObj) {
			if (o instanceof Event) {
				eventList.add((Event)o);
			} else {
				MyStatic.errorHalt(String.format("ERROR - 1111 - unable to cast array item to Event - it is \"%s\"",o.toString()));
			}
		}
		Integer out = 0;
		for (Event e : eventList) {
			out = out + (epoch.get(e) * e.getParent().offset(e));
		}
		return out;
	}


	//	private void errorHalt(String in) {
	//		System.out.println(in);
	//		Thread.dumpStack();
	//		Runtime.getRuntime().halt(0);
	//	}

*/

	/**
	 * Performs the factor product
	 * Example - "F1.product(F2)" produces the factor product F1xF2
	 */
/*
public DistributionDefinition product(DistributionDefinition f2) {

		// create a spare list of variables
		//		 HashSet<RandomVariable> s = new HashSet<RandomVariable>();
		//		s.addAll(this.getVariableDefList());
		//		s.addAll(f2.getVariableDefList());


		/*
		Factor z = new Factor();
Collection<Blah> = 
		z.getValue()
		z.getMarginal();


		// sparse array or set of Variables
 permute(A,B)

		errorHalt(String.format("ERROR - 1132222 - not coded"));
		 */
//		return null;
//	}



//	public void marginalization(List<Double> asList) {
//		// TODO Auto-generated method stub
//
//	}


//	public String toString() {
//		return moreString("", randomVariableList());
//	}


	/* recursive function to iterate through the random variables
	 * returns a string reflecting the matrix
	 */
//	private String moreString(String prefix, List<RandomVariableInstance> list) {
//		if (list.size() == 0) {
//			return prefix + "\n";
//		} else {
//			RandomVariableInstance myRv = list.get(0);
//			List<RandomVariableInstance> remainingList = list.subList(1, list.size());
//			String accum = new String();
//			for (Event e : myRv.eventList()) {
//				accum = accum.concat(moreString(prefix + " " + e.getName(), remainingList));
//			}
//			return accum;
//		}
//	}


//}