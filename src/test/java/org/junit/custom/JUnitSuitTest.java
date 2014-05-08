package org.junit.custom;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


//打包测试,测试需�?测试的JUnit类
@RunWith(Suite.class)
@SuiteClasses({
	JUnitTest.class,
	JUnitTestMyCalculator.class,
	JUnitParamTest.class
})
public class JUnitSuitTest {

}
