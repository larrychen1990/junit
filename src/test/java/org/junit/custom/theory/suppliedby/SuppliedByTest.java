package org.junit.custom.theory.suppliedby;

import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;


@RunWith(Theories.class)
public class SuppliedByTest {

	@Theory
	public void imagineThisIsATest(@AllCreditCards String x, @AllNames String y) {
		System.out.println("consider " + x + " / " + y + " tested.");
	}

	@Theory
	public void testIntegers(@TestedOn(ints = { 2, 3, 4, 7, 13, 23, 42 }) int i) {
		System.out.println(i);
	}
}
