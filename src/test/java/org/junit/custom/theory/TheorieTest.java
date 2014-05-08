package org.junit.custom.theory;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.junit.Assume;

@RunWith(Theories.class)
public class TheorieTest {

	@DataPoint
	public static String a = "a";

	@DataPoint
	public static String b = "bb";

	@DataPoint
	public static String c = "ccc";

	@DataPoints
	public static String[] strings = { "as", "bbs", "cccs" };

	@DataPoints
	public static Integer[] ints = { 1, 2, 3 };

	@Theory
	public void stringTest(String x, String y) {
		// System.out.println(x+"----"+y);
		// Assume.assumeTrue(x.length() > 1);
		//
		System.out.println(x + "----" + y);
	}

	@Theory
	public void someTest(String x, Integer y) {
		// Assume.assumeTrue(x.length() > 1);

		System.out.println(x + " " + y);
	}
}