package org.junit.custom.rule;

import org.junit.Rule;
import org.junit.Test;


public class RuleTestCase {
	
	@Rule
	public OperateRule rule2 = new OperateRule();

	@Test
	@BeforeMethod(message="test_1 before method")
	public void test_1() {
		System.out.println("test_1");
	}
	
	@Test
	@AfterMethod(message="test_2 after method")
	public void test_2() {
		System.out.println("test_2");
	}
	
	@Test
	@BeforeMethod(message="test_3 before method")
	@AfterMethod(message="test_3 after method")
	public void test_3() {
		System.out.println("test_3");
	}
}
