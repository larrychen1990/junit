package org.junit.custom;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;




public class JUnitTest {

	//Must be static
	@BeforeClass
	public static void junitBeforeClass() {
		System.out.println("before class");
	}

	@AfterClass
	public static void junitAfterClass() {
		System.out.println("after class");
	}
	
	//excute once before every @Test method
	@Before
	public void junitBefore() {
		System.out.println("before");
		
	}
	//excute once after every @Test method
	 @After
	 public void junitAfter() {
	 System.out.println("after");
	 }
	
	
	@Test
	public void junitT() {
		System.out.println("Test");
		fail("fail");
	}
	@Test(timeout=(long) 0.000000000058)
	public void junitTimeout() {
		System.out.println("Test timeout");
		try {
			wait((long) Math.random());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test(expected=Exception.class)
	public void junitException() throws Exception {
		System.out.println("Test exception");
			wait(1000);
			throw new Exception("Exception");
	}
	
	
}
