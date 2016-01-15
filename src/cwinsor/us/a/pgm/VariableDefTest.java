package cwinsor.us.a.pgm;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VariableDefTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		VariableDef FRUIT_DEF = new VariableDef(
				"FRUIT_DEF",
				Arrays.asList("apple", "banana", "grape"));

		String str = FRUIT_DEF.toString();
		System.out.printf("%s\n",str);
	
		// check .name
		assertEquals(FRUIT_DEF.name(), "FRUIT_DEF");
		// check .offset
		List<String> eNames = FRUIT_DEF.eventNames();

		assertTrue(eNames.contains("apple"));
		assertTrue(eNames.contains("banana"));
		assertTrue(eNames.contains("grape"));
	}

}
