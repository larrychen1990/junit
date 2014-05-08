package org.junit.custom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//�?行器，指定用JUnit中的哪一个runner�?�完�?测试
@RunWith(JUnit4.class)
public class JUnitTestMyCalculator {
	private Calculator cal;

	@BeforeClass
	// 在所有方法执行之�?执行,must be public and static
	public static void globalInit() {
		System.out.println("init all method...");
	}

	@AfterClass
	// 在所有方法执行之�?�执行
	public static void globalDestory() {
		System.out.println("destory all method...");
	}

	@Before
	// 在�?个测试方法之�?执行
	public void setUp() {
		System.out.println("start setUp method");
		cal = new Calculator();
	}

	@After
	// 在�?个测试方法之�?�执行
	public void tearDown() {
		System.out.println("end tearDown method");
	}

	@Test(timeout = 600)
	// 设置�?定测试方法的�?行时间 如果超出则返回错误
	public void testAdd() {
		System.out.println("testAdd method");
		int result = cal.add(2, 3);
		assertEquals(5, result);
//		for(;;) {}						//dead loop to test timeout
	}

	@Test
	public void testSubtract() {
		System.out.println("testSubtract method");
		int result = cal.subtract(1, 2);
		assertEquals(-1, result);
	}

	@Test
	public void testMultiply() {
		System.out.println("testMultiply method");
		int result = cal.multiply(2, 3);
		assertEquals(6, result);
	}

	@Test
	public void testDivide() {
		System.out.println("testDivide method");
		int result = 0;
		try {
			result = cal.divide(6, 2);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertEquals(3, result);
	}

	@Test(expected = Exception.class)
	public void testDivide2() throws Exception {
		System.out.println("testDivide2 method");
		cal.divide(6, 0);
		fail("test Error"); // didn't run this any more
	}

	@Test
	public void testAssertTrue() {
		assertTrue("false", true);
	}

	@Ignore("Ignore this method")
	@Test
	public void testIgnore() {
		System.out.println("test ignore");
	}

	public static void main(String[] args) {
	}
}
