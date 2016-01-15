/**
 * 
 */
package cwinsor.us.z.other;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;

/**
 * @author cwinsor
 *
 */
public class aaExamples {

	public void iterating(List<String> names) {

		// using Java 8 "forEach" default method in the Iterable interface
		names.add("blah");
		names.add("zoinks");
		names.forEach(name -> System.out.println(name));

		for (String name : names) {
		    System.out.println(name);
		}
		
		for(Iterator<String> iter = names.iterator(); iter.hasNext(); ) {
		    String name = iter.next();
		    System.out.println(name);
		}
				}

	public void arrayInitialization() {
	
				int[] anArray = { 
					    100, 200, 300,
					    400, 500, 600, 
					    700, 800, 900, 1000
					};
		
		
	}
	
}
