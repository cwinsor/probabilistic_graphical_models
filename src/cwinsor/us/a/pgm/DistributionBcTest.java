package cwinsor.us.a.pgm;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DistributionBcTest {

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
		VariableDef DEF_FRUIT = new VariableDef(
				"DEF_FRUIT",
				Arrays.asList("apple", "banana", "cherry"));
		RandomVariable variable_1 = new RandomVariable("variable_1", DEF_FRUIT);
		RandomVariable variable_2 = new RandomVariable("variable_2", DEF_FRUIT);
		
		VariableDef DEF_PIE_SALAD = new VariableDef(
				"DEF_PIE_SALAD",
				Arrays.asList("pie", "salad"));
		RandomVariable variable_3 = new RandomVariable("variable_3", DEF_PIE_SALAD);

//		variable_1.setAll(1.0);
//		variable_2.setAll(1.0);
//		variable_3.setAll(1.0);

		DistributionBc distribution = new DistributionBc("distribution", Arrays.asList(variable_1, variable_2, variable_3));
//		distribution.zeros();
		distribution.ones();

		String s = distribution.toString();
		System.out.println("==============\n" + s);

		System.out.println("done");


	}

}

