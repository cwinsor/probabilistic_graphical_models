/**
 * 
 */
package cwinsor.us.a.pgm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cwinsor
 *
 */
public class App04 {


	public App04() {
	}

	/**
	 * run
	 */
	public void run() {



		// define a variable with two values
		VariableDef A_DEF = new VariableDef("A_DEF", Arrays.asList("a1", "a2"));

		// define a variable with two values - several steps
		VariableDef B_DEF = new VariableDef("B_DEF", Arrays.asList("b1","b2"));

		// define a variable with two values
		VariableDef C_DEF = new VariableDef("C_DEF", Arrays.asList("c1", "c2", "c3"));


		// instance some random variables
		RandomVariable a = new RandomVariable("a", A_DEF);
		RandomVariable b = new RandomVariable("b", B_DEF);
		RandomVariable c = new RandomVariable("c", C_DEF);
		String temp = a.toString();

		System.out.println(a.toString());
		System.out.println("---");

		// get value/values of a random variable


		// create a small distribution - a single random variable
		DistributionBc dist_a = new DistributionBc("distribution_a", Arrays.asList(a));
		dist_a.setValues(Arrays.asList(
				 a.eventRef("a1"), 0.11,
				a.eventRef("a2"), 0.89));

		// create a distribution involving two random variables
		DistributionBc dist_ba = new DistributionBc("distribution_ba", Arrays.asList(b,a));
		dist_ba.setValues(Arrays.asList(
				b.event("b1"), a.event("a1"), 0.59,
				b.event("b2"), a.event("a2"), 0.41,
				b.event("b1"), a.event("a3"), 0.22,
				b.event("b2"), a.event("a1"), 0.78));

		DistributionBc dist_cb = new DistributionBc("distribution_cb", Arrays.asList(c,b));
		dist_cb.setValues(Arrays.asList(
				c.event("c1"), b.event("b1"), .39,
				c.event("c2"), b.event("b1"), .61,
				c.event("c1"), b.event("b2"), .06,
				c.event("c2"), b.event("b2"), .94));

		// a network is a group of factors
		// a network can be "evaluated" and a specific procedure is involved here
		// if a condition has been applied to a variable in the network then it can be marginalized out.

		// create a new factor as the product of two factors
		DistributionBc dist_abc = dist_a.product(dist_cb); 

		/*
//		ff_a.mmarginalization(Arrays.asList(b.reference("b1"), c.reference("c2")));
//		ff_ba.mmarginalization(Arrays.asList(b.reference("b1"), c.reference("c2")));
//		ff_cb.mmarginalization(Arrays.asList(b.reference("b1"), c.reference("c2")));

		 */
		System.out.println(dist_a.toString());
		System.out.println(dist_ba.toString());
		System.out.println(dist_cb.toString());
		System.out.println(dist_abc.toString());


		/* construct a Bayes Network */
		Network bn = new NetworkBayesian("bn", Arrays.asList(dist_a, dist_ba, dist_cb));


		//	JointDistribution j1 = f_a.joint(f_ba);
		//		Arrays.asList(b,a));

		// questions ...
		// joint distribution is every random variable at the same level

		// a factor is... a set of operations on tables.  Can an factor operate on conditionals?
		// in the case of Bayes the "factor" is a CPD table
		// in the case of Markov a "factor" is a ?????


		// a conditional is one or more variables conditioned on one or more variables
		// a joint is one or more variables (jointly combined)


		//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

		/* construct a Pairwise Markov Network */
		// define a variable with two values
		//	VariableDef H_DEF = new VariableDef("H_DEF", Arrays.asList("h1", "h2", "h3"));
		//	VariableDef I_DEF = new VariableDef("I_DEF", Arrays.asList("i1", "i2", "i3"));

		// instance some random variables
		//	RandomVariable h = new RandomVariable("h", H_DEF);
		//		RandomVariable i = new RandomVariable("i", I_DEF);	

		//	Factor f_6 = new FactorUnnormalizedPair("f_6", Arrays.asList(h,i));
		//		Factor f_7 = new FactorUnnormalizedPair("f_7", Arrays.asList(h,i));
		//		Factor f_8 = new FactorUnnormalizedPair("f_8", Arrays.asList(h,i));

		//		Network pmn = new NetworkPairwiseMarkov("pmn", Arrays.asList(f_7, f_7, f_8));





	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("---start---");
		App04 theApp = new App04();
		theApp.run();
		System.out.println("---done---");

	}
}
