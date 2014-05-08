### IDE support - graphical runners
NetBeans, Eclipse and IntelliJ Idea have native graphical test runners built in.

### Console based Test runner
JUnit provides tools to define the suite to be run and to display its results. To run tests and see the results on the console, run this from a Java program:
```java
    org.junit.runner.JUnitCore.runClasses(TestClass1.class, ...);
```
or this from the command line, with both your test class and junit on the classpath:
```java
    java org.junit.runner.JUnitCore TestClass1 [...other test classes...]
```
This usage is documented further here: http://junit.org/apidocs/junit/textui/TestRunner.html

### Using older runners with Adapter
`JUnit4Adapter` enables running the new JUnit4 tests using the old JUnit runners. To use it add the following  to a test class:
```java
      public static Test suite() {
        return new JUnit4TestAdapter(AdditionAllTests.class);
      }
```
## @RunWith annotation
When a class is annotated with `@RunWith` or extends a class annotated with `@RunWith`, JUnit will invoke the class it references to run the tests in that class instead of the runner built into JUnit.
- JavaDoc for @RunWith http://junit.sourceforge.net/javadoc/org/junit/runner/RunWith.html
- The default runner is `BlockJUnit4ClassRunner` which supersedes the older `JUnit4ClassRunner`
- Annotating a class with `@RunWith(JUnit4.class)` will always invoke the default JUnit 4 runner in the current version of JUnit, this class aliases the current default JUnit 4 class runner.

## Specialized Runners ##
### Suite ###
- `Suite` is a standard runner that allows you to manually build a suite containing tests from many classes.
 - More information at [[Aggregating tests in Suites]] page.
 - http://junit.sourceforge.net/javadoc/org/junit/runners/Suite.html

### Parameterized ###
- `Parameterized` is a standard runner that implements parameterized tests. When running a parameterized test class, instances are created for the cross-product of the test methods and the test data elements.
 - More information at [[Parameterized Tests]] page.
 - Javadoc: http://junit.sourceforge.net/javadoc/org/junit/runners/Parameterized.html

### Categories ###
- `Categories` is a standard runner enabling subsets of tests tagged with certain categories to execute/be excluded from a  given test run.
 - More information at [[Categories]] page.

## Experimental Runners ##
### Enclosed ###
- `Enclosed` - If you put tests in inner classes, Ant, for example, won't find them. By running the outer class with Enclosed, the tests in the inner classes will be run. You might put tests in inner classes to group them for convenience or to share constants.
- Javadoc: 
 - http://junit-team.github.com/junit/javadoc/4.10/org/junit/experimental/runners/Enclosed.html
- Working Example of use on the [['Enclosed'-test-runner-example]] page

## Third Party Runners ##
Some popular third party implementations of runners for use with `@RunWith` include:
- SpringJUnit4ClassRunner 
 - http://static.springsource.org/spring/docs/3.0.x/javadoc-api/org/springframework/test/context/junit4/SpringJUnit4ClassRunner.html
- MockitoJUnitRunner 
 - http://docs.mockito.googlecode.com/hg/latest/org/mockito/runners/MockitoJUnitRunner.html