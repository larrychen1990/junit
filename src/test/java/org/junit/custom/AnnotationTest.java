package org.junit.custom;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeThat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;


@RunWith(Theories.class)
public class AnnotationTest {

	@Rule
	public TestName testname = new TestName();

	@DataPoint
	public static String dPoint = "I love JUnit!";

	// 测试@BeforeClass批注，在整个测试类中�?��?行一次，有别于@Before。
	// test the @BeforeClass annotation
	@BeforeClass
	public static void testBeforeClass() {
		System.out.println("BeforeClass");
	}

	// 测试@AfterClass批注，在整个测试类中�?��?行一次，有别于@After
	// test the @AfterClass annotation
	@AfterClass
	public static void testAfterClass() {
		System.out.println("AfterClass");
	}

	// 测试@Before批注，在�?个测试方法�?行�?执行该方法。
	// test the @Before annotation
	@Before
	public void testBefore() {
		System.out.println("Before");
	}

	// 测试@After批注，�?个test方法执行完�?�?��?行此方法
	// test the @After annotation
	@After
	public void testAfter() {
		System.out.println("After");
	}

	// 测试@Test批注。
	// test the @Test annotation
	@Test
	public void testMethod() {
		assertEquals("testMethod", testname.getMethodName());
		
		System.out.println("testMethod");
	}

	// 测试@Theory批注。
	// test the @Theory annotation
	@Theory
	public void testDataPoint(String interests) {
		// interests必须是I lvoe JUnit!，�?�则跳过该测试函数。
		// interests must be I love JUnit!, or skip the test function.
		assumeThat(interests, is("I love JUnit!"));
		assertEquals("testDataPoint", testname.getMethodName());
		System.out.println("testDataPoint" + "\n" + dPoint);
	}

	// 测试@Ignore批注
	// test the @Ignore annotation
	@Ignore("Ignore test")
	@Test
	public void testIgnore() {
		assertEquals("testIgnore", testname.getMethodName());
		System.out.println("testIgnore");
	}
}