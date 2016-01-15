package cwinsor.us.a.pgm;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class DistributionBc {


	private String myName;

	// map Event to RandomVariable
	//private Map<Event,RandomVariable> eventToRandomVariable = new HashMap<Event,RandomVariable>();

	// epoch - transform matrix to linear array
	private Map<RandomVariable, Integer> epoch = new HashMap<RandomVariable, Integer>();

	// linear array of values
	private Double[] value;

	public DistributionBc(String name, List<RandomVariable> list) {
		this.myName = name;
		//	this.randomVariableList = list;

		// create a local map from event to its (parent) random variable
		for (RandomVariable rv : list) {
			for (Event e : rv.eventList()) {
				//				eventToRandomVariable.put(e, rv);
			}
		}

		// establish epoch for each variable
		// and total number of values represented
		Integer runningEpoch = 1;
		for (RandomVariable rv : list) {
			epoch.put(rv,runningEpoch);
			runningEpoch = runningEpoch * rv.cardinality();
		}

		// create an array for variable values, and initialize
		value = new Double[runningEpoch];
		for (Integer i=0; i<value.length; i++) {
			value[i] = 0.0;
		}
	}

	public Integer numRandomVariables() {
		return epoch.size();
	}

	public Set<RandomVariable> randomVariableSet() {
		return epoch.keySet();
	}

	public	List<RandomVariable> randomVariableList() {
		Set<RandomVariable> blah2 = randomVariableSet();
		List<RandomVariable> foo = new ArrayList<RandomVariable>();
		for (Object o : blah2) {
			foo.add((RandomVariable)o);
		}
		return foo;
	}



	public void setValues(List<Object> list) {

		// sanity check
		// the parameters should be:  (m) { (n)randomVariables, Double }
		if (list.size() != (numRandomVariables() + 1)) {
			errorHalt(String.format("ERROR - in setValues - asList.size() != (numRandomVariables+1) %d %d\n",
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


	private Double castToDouble(Object valueObj) {
		if (valueObj instanceof Double) {
			Double valueD = (Double) valueObj;
			return valueD;
		} else {
			errorHalt(String.format("ERROR - 2222 - unable to cast array item to Double - it is \"%s\"",valueObj.toString()));
		}
		return null;
	}


	private Integer toArrayIndex(List<Object> eventListObj) {

		List<Event> eventList = new ArrayList<Event>();

		// cast Objects to Events
		for (Object o : eventListObj) {
			if (o instanceof Event) {
				eventList.add((Event)o);
			} else {
				errorHalt(String.format("ERROR - 1111 - unable to cast array item to Event - it is \"%s\"",o.toString()));
			}
		}
		Integer out = 0;
		for (Event e : eventList) {
			out = out + (epoch.get(e) * e.getParent().offset(e));
		}
		return out;
	}
	/*
	private Integer[] toIntegerIndices(String[] indicesS) {
		// sanity check
		if (indicesS.length != numRandomVariables()) {
			errorHalt(String.format("ERROR - 1132556"));
		}

		errorHalt(String.format("ERROR - 1132556 - not coded"));

		Integer[] out = new Integer[indicesS.length];
		//		for (Integer i=0; i<indicesS.length; i++) {
		//			out[i] = randomVariableList.get(i).val(string)
		//					
		//					.nameToIndex(indicesS[i]);
		//		}
		return out;
	}
	 */

	private void errorHalt(String in) {
		System.out.println(in);
		Thread.dumpStack();
		Runtime.getRuntime().halt(0);
	}



	/**
	 * Performs the factor product
	 * Example - "F1.product(F2)" produces the factor product F1xF2
	 */
	public DistributionBc product(DistributionBc f2) {

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
		return null;
	}



	public void marginalization(List<Double> asList) {
		// TODO Auto-generated method stub

	}


	public void zeros() {
		for (Integer i=0; i<value.length; i++) {
			value[i] = 0.0;
		}
	}


	public void ones() {
		for (Integer i=0; i<value.length; i++) {
			value[i] = 1.0;
		}
	}

	public String toString() {
		return moreString("", randomVariableList());
	}


	/* recursive function to iterate through the random variables
	 * returns a string reflecting the matrix
	 */
	private String moreString(String prefix, List<RandomVariable> list) {
		if (list.size() == 0) {
			return prefix + "\n";
		} else {
			RandomVariable myRv = list.get(0);
			List<RandomVariable> remainingList = list.subList(1, list.size());
			String accum = new String();
			for (Event e : myRv.eventList()) {
				accum = accum.concat(moreString(prefix + " " + e.getName(), remainingList));
			}
			return accum;
		}
	}



	public String toString2() {
		System.out.printf("zona here 1\n");
		String out = moreString2("", randomVariableList(), 0);
		System.out.printf("zona here 2\n");
		return out;
	}


	/* recursive function to iterate through the random variables
	 * returns a string reflecting the matrix
	 */
	private String moreString2(String prefix, List<RandomVariable> list, Integer depth) {
		String indent = new String("+++");
		for (int i=0; i<=depth; i++) {
			indent += "------";
		}
		System.out.printf("zona %s current level %s entry    ->%s\n",indent,depth,prefix);


		if (list.size() == 0) {
			System.out.printf("zona %s current level %s returning %s\n",indent,depth,prefix);
			String blah = new String(prefix);
			return blah;
			//			return prefix;
		} else {
			RandomVariable myRv = list.get(0);
			List<RandomVariable> newRemaining = list.subList(1, list.size());
			String accum = new String("");
			for (Event e : myRv.eventList()) {

				String out = new String("Q");
				String newPrefix = new String(prefix +  e.getName());
				System.out.printf("zona %s current level %s going in ->%s\n",indent,depth,newPrefix);		
				out = moreString2(newPrefix, newRemaining, depth+1);
				accum = accum.concat(out);
				System.out.printf("zona %s current level %s returned ->%s   accum=%s\n",indent,depth,out,accum);	
			}
			System.out.printf("zona %s current level %s returning ->%s\n",indent,depth,accum);	
			return accum;
		}
		//System.out.printf("zona %s current level %s THIS IS BAD BAD\n",indent,depth);	
		//	return null;
	}


	// zona - temporary example code
	public void run() {
		String word = readLine("Give a word to anagram: ");
		printAnagrams("", word);
	}

	public void printAnagrams(String prefix, String word) {
		if(word.length() <= 1) {
			System.out.println(prefix + word);
		} else {
			for(int i = 0; i < word.length(); i++) {
				String cur = word.substring(i, i + 1);
				String before = word.substring(0, i); // letters before cur
				String after = word.substring(i + 1); // letters after cur
				printAnagrams(prefix + cur, before + after);
			}
		}
	}


	private String readLine(String string) {
		// TODO Auto-generated method stub
		return null;
	}


}
