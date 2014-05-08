# Expected Exceptions

How do you verify that code throws exceptions as expected?
Verifying that code completes normally is important, but making sure the code behaves as expected in exceptional situations is vital too. For example:
```java
    new ArrayList<Object>().get(0);
```
This code should throw an IndexOutOfBoundsException. The `@Test` annotation has an optional parameter "`expected`" that takes as values subclasses of `Throwable`. If we wanted to verify that `ArrayList` throws the correct exception, we would write:
```java
    @Test(expected= IndexOutOfBoundsException.class) 
    public void empty() { 
         new ArrayList<Object>().get(0); 
    }
```
The `expected` parameter should be used with care. The above test will pass if *any* code in the method throws `IndexOutOfBoundsException`. For longer tests, it's recommended to use the `ExpectedException` rule, which is described below.

## Deeper Testing of the Exception
The above approach is useful for simple cases, but it has its limits. For example, you can't test the value of the message in the exception, or the state of a domain object after the exception has been thrown.  

### Try/Catch Idiom
To address this you can use the try/catch idiom which prevailed in JUnit 3.x:
```java
    @Test
    public void testExceptionMessage() {
        try {
            new ArrayList<Object>().get(0);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException anIndexOutOfBoundsException) {
            assertThat(anIndexOutOfBoundsException.getMessage(), is("Index: 0, Size: 0"));
        }
    }
```
### ExpectedException Rule
Alternatively, use the `ExpectedException` rule. This rule lets you indicate not only what exception you are expecting, but also the exception message you are expecting:

```java    
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldTestExceptionMessage() throws IndexOutOfBoundsException {
        List<Object> list = new ArrayList<Object>();

        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index: 0, Size: 0");
        list.get(0); // execution will never get past this line
    }
``` 
The expectMessage also lets you use Matchers, which gives you a bit more flexibility in your tests. An example:

`thrown.expectMessage(JUnitMatchers.containsString("Size: 0"));`


For an expanded discussion of the `ExpectedException` rule, see this [blog post](http://baddotrobot.com/blog/2012/03/27/expecting-exception-with-junit-rule/index.html).