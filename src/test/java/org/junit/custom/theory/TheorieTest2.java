package org.junit.custom.theory;

import org.junit.Assume;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class TheorieTest2 {

	@DataPoints
	public static String[] a = { "a", "bb", "ccc" };

	@DataPoints
	public static Integer[] j = { 1, 2, 3 };

	@Theory
	public void someTest(String x, Integer y) {
//		Assume.assumeTrue(x.length() > 1);

		System.out.println(x + " " + y);
		
	}
}
