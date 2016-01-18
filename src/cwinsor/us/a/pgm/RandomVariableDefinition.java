package cwinsor.us.a.pgm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomVariableDefinition {

	private String myName;
	private List<String> eventNames;

	public RandomVariableDefinition(String variableName, List<String> eventNames) {

		this.myName = variableName;
		this.eventNames = eventNames;
	}

	public String name() {
		return myName;
	}

	public List<String> eventNames() {
		return eventNames;
	}

	public String toString() {
		return String.format("%s %s",myName, eventNames.toString());
	}
}
