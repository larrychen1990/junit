package org.junit.custom.rule;


import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class OperateRule implements MethodRule {

	public Statement apply(final Statement base,
			final FrameworkMethod method, Object target) {
		// TODO Auto-generated method stub
		
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				// TODO Auto-generated method stub
				if (method.getAnnotation(BeforeMethod.class) != null) {
					System.out.println(((BeforeMethod) method
							.getAnnotation(BeforeMethod.class)).message());
				}
				base.evaluate();
				// After method
				if (method.getAnnotation(AfterMethod.class) != null) {
					System.out.println(((AfterMethod) method
							.getAnnotation(AfterMethod.class)).message());
				}
			}
		};
	}

}