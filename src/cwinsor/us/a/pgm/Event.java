package cwinsor.us.a.pgm;

public class Event {

	String name;
	RandomVariable parent;
	
	public Event(String name, RandomVariable parent) {
		this.name = name;
		this.parent = parent;
	}
	
	public String getName() {
		return name;
	}

	public RandomVariable getParent() {
		return parent;
	}
}
