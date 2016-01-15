/**
 * 
 */
package cwinsor.us.a.pgm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cwinsor.us.b.persistence.MyStatic;

/**
 * @author cwinsor
 *
 */
public class RandomVariable {

	private String myName;
	private String myType;
	private Map<Event,Double> eventToValue = new HashMap<Event,Double>();

	public RandomVariable(String name, VariableDef vdef) {
		myName = name;
		myType = vdef.name();
		for (String eventName : vdef.eventNames()) {
			Event e = new Event(eventName, this);
			eventToValue.put(e, null);
		}
	}

	public Object name() {
		return myName;
	}

		
	public Object type() {
		return myType;
	}

	public Integer cardinality() {
		return eventToValue.size();
	}

	/**
	 * set value for a specific  of the variable
	 */
	public void set(String eventName, Double value) {
		Event e = checkAndGet(eventName);
		eventToValue.put(e, value);	
	}

	public void set(Event e, Double value) {
		check(e);
		eventToValue.put(e, value);	
	}



	public void setAll(double val) {
		for (Event e: eventToValue.keySet()) {
			eventToValue.put(e, val);
		}
	}

	/**
	 * get value for a specific event given event name
	 */
	// event(String)   (returns Event)
	// get(String) (returns Value)
	// get(Event) returns Value)


	public Double event(String eventName) {
		Event e = checkAndGet(eventName);
		return eventToValue.get(e);
	}

	public Double event(Event e) {
		check(e);
		return eventToValue.get(e);
	}

	public Event eventRef(String eventName) {
		Event e = checkAndGet(eventName);
		return e;
	}

	public Set<Event> eventList() {
		return eventToValue.keySet();
	}

	// confirm the value name is part of this variable
	private Event checkAndGet(String eventName) {
		for (Event e : eventToValue.keySet()) {
			if (e.getName().equals(eventName)) {
				return e;
			}
		}
		// not found
		String s = new String(String.format("Error 44325 in %s of type %s - unable to access value named %s (doesn't exist)",
				myName,myType,eventName));
		MyStatic.errorHalt(s);
		return null;
	}

	private void check(Event e) {
		if (!eventToValue.containsKey(e)) {
			// not found
			String s = new String(String.format("Error 5564 in %s of type %s - unable to access value named %s (doesn't exist)",
					myName,myType,e.getName()));
			MyStatic.errorHalt(s);
		}
	}

	public String toString() {
		return   String.format("%s %s %s",myName, myType, eventToValue.toString());
	}

	public Integer offset(Event e) {
		// TODO Auto-generated method stub
		return null;
	}





}
