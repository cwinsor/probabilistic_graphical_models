/**
 * 
 */
package cwinsor.us.a.pgm;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author cwinsor
 *
 */
public class RandomVariableTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		VariableDef FRUIT_DEF = new VariableDef(
				"FRUIT_DEF",
				Arrays.asList("f_0", "f_1", "f_2"));

		RandomVariable rv = new RandomVariable("fruit", FRUIT_DEF);

		// check .name
		assertEquals(rv.name(), "fruit");
		// check .cardinality
		assertEquals(rv.cardinality(), (Integer)3);



		rv.setAll(456.7);
		System.out.printf("%s\n", rv.toString());			
		assertEquals(rv.event("f_0"), (Double)456.7);
		assertEquals(rv.event("f_1"), (Double)456.7);
		assertEquals(rv.event("f_2"), (Double)456.7);

		rv.set("f_0", 123.4);
		System.out.printf("%s\n", rv.toString());	
		assertEquals(rv.event("f_0"), (Double)123.4);
		assertEquals(rv.event("f_1"), null);
		assertEquals(rv.event("f_2"), null);

		
		Event e0 = rv.eventRef("f_0");
		assertEquals(e0, rv.event("f_0"));
		
		
		
		
		
	}

}
