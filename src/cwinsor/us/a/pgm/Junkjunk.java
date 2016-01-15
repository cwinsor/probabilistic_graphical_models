/**
 * 
 */
package cwinsor.us.a.pgm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cwinsor
 */
public class Junkjunk {

	private String myName;

	// values (linear array)
	private List<Double> value = new ArrayList<Double>();

	// epoch - maps from n-dimensional matrix to linear array
	private Map<RandomVariable, Integer> epoch = new HashMap<RandomVariable, Integer>();


	public Junkjunk(String name, List<RandomVariable> list) {
		myName = name;

		// establish epoch for each variable
		// and total number of values represented
		Integer runningEpoch = 1;
		for (RandomVariable rv : list) {
			epoch.put(rv,runningEpoch);
			runningEpoch = runningEpoch * rv.cardinality();
		}

		// create an array of values, and initialize
		value = new ArrayList<Double>(runningEpoch);
		for (Integer i=0; i<value.size(); i++) {
			value.set(i, 0.0);
		}
	}


	private Integer toLinearOffset(RandomVariable rv, VariableDef vd) {
		return null;
	}


	public void updateValues(RandomVariable thing_left) {
	
		}


	public void updateValuesList(List<RandomVariable> asList) {
		// TODO Auto-generated method stub
		
	}
}

