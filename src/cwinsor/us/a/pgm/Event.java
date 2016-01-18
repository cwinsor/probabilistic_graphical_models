package cwinsor.us.a.pgm;

public class Event {

	String name;
	RandomVariableInstance parent;
	
	public Event(String name, RandomVariableInstance parent) {
		this.name = name;
		this.parent = parent;
	}
	
	public String getName() {
		return name;
	}

	public RandomVariableInstance getParent() {
		return parent;
	}
}
