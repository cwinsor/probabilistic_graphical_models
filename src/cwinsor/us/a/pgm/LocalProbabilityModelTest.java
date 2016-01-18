package cwinsor.us.a.pgm;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LocalProbabilityModelTest {

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

		//////////////////////
		// define the random variables - this in turn defines the Events
		RandomVariableDefinition RV_DEF_X = new RandomVariableDefinition("DEF_X", Arrays.asList("x0", "x1", "x2"));
		RandomVariableDefinition RV_DEF_Y = new RandomVariableDefinition("DEF_Y", Arrays.asList("y0", "y1"));
		RandomVariableDefinition RV_DEF_Z = new RandomVariableDefinition("DEF_Y", Arrays.asList("z0", "z1"));

		///////////////////////////////////
		// define the local probability models
		LocalProbabilityModel X = new LocalProbabilityModel("X");
		LocalProbabilityModel Y = new LocalProbabilityModel("Y");
		LocalProbabilityModel Z = new LocalProbabilityModel("Z");
		LocalProbabilityModel X1 = new LocalProbabilityModel("X1");
		LocalProbabilityModel X2 = new LocalProbabilityModel("X2");
		LocalProbabilityModel X3 = new LocalProbabilityModel("X3");
		LocalProbabilityModel X7 = new LocalProbabilityModel("X7");
		
		////////////////////////////////
		// specify target variable type, and dependencies
		
		// two a-priori variables - target only, no dependencies
		X.setTargetAndDependencies(RV_DEF_X, null);
		Y.setTargetAndDependencies(RV_DEF_Y, null);
		
		// a LPM conditionally dependent on two variables
		Z.setTargetAndDependencies(RV_DEF_Z, Arrays.asList(X, Y));

		// a LPM conditionally dependent on several variables of the same type
		X3.setTargetAndDependencies(RV_DEF_X, Arrays.asList(X1, X2));

		// a LPM conditionally dependent on itself (i.e. a state variable)
		X7.setTargetAndDependencies(RV_DEF_X, Arrays.asList(X7));

	
		// the first has no dependencies just values
		X.setValue("x0", 0.6);
		X.setValue("x1", 0.1);
		X.setValue("x2", 0.1);

		// the second has dependencies
		Z.setValue("z0", Arrays.asList(X.event("x0"), Y.event("y0")), 0.3);
		Z.setValue("z0", Arrays.asList(X.event("x0"), Y.event("y1")), 0.3);
		Z.setValue("z0", Arrays.asList(X.event("x1"), Y.event("y0")), 0.3);
		Z.setValue("z0", Arrays.asList(X.event("x1"), Y.event("y1")), 0.3);
		Z.setValue("z0", Arrays.asList(X.event("x2"), Y.event("y0")), 0.3);
		Z.setValue("z0", Arrays.asList(X.event("x2"), Y.event("y1")), 0.3);
		Z.setValue("z1", Arrays.asList(X.event("x0"), Y.event("y0")), 0.3);
		Z.setValue("z1", Arrays.asList(X.event("x0"), Y.event("y1")), 0.3);
		Z.setValue("z1", Arrays.asList(X.event("x1"), Y.event("y0")), 0.3);
		Z.setValue("z1", Arrays.asList(X.event("x1"), Y.event("y1")), 0.3);
		Z.setValue("z1", Arrays.asList(X.event("x2"), Y.event("y0")), 0.3);
		Z.setValue("z1", Arrays.asList(X.event("x2"), Y.event("y1")), 0.3);

		// the third has a recurrent dependency
		X1.setValue("x0", Arrays.asList(X1.event("x0")), 0.1);
		X1.setValue("x0", Arrays.asList(X1.event("x1")), 0.1);
		X1.setValue("x1", Arrays.asList(X1.event("x0")), 0.1);
		X1.setValue("x1", Arrays.asList(X1.event("x1")), 0.1);
		
		
		
		/* NOTE - should we enforce that a conditional probability should always be 'set' with requirement that the conditioning variables should all be identified ? */
		// this would be  Z.setValue(
	//	Z.setValue(Arrays.asList(X.event("x0"), Y.event("y0")),
	//			Arrays.asList(
	//					"z0", 0.5,
	//					"z1", 0.3,
	//					"z2", 0.7));



		String s = Z.toString();
		System.out.println("==============\n" + s);

		System.out.println("done");


	}

}

