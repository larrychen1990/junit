### Rules ###

- Rules allow very flexible addition or redefinition of the behavior
  of each test method in a test class.  Testers can reuse or extend one of the 
  provided Rules below, or write their own.
 
 - For more on this feature, see http://www.threeriversinstitute.org/blog/?p=155

### Example ###

For an example of a rule usage, there follows a test using the TemporaryFolder and ExpectedException rules:

```java
public class DigitalAssetManagerTest {
  @Rule
  public TemporaryFolder tempFolder = new TemporaryFolder();

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Test
  public void countsAssets() throws IOException {
    File icon = tempFolder.newFile("icon.png");
    File assets = tempFolder.newFolder("assets");
    createAssets(assets, 3);

    DigitalAssetManager dam = new DigitalAssetManager(icon, assets);
    assertEquals(3, dam.getAssetCount());
  }

  private void createAssets(File assets, int numberOfAssets) throws IOException {
    for (int index = 0; index < numberOfAssets; index++) {
      File asset = new File(assets, String.format("asset-%d.mpg", index));
      Assert.assertTrue("Asset couldn't be created.", asset.createNewFile());
    }
  }

  @Test
  public void throwsIllegalArgumentExceptionIfIconIsNull() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Icon is null, not a file, or doesn't exist.");
    new DigitalAssetManager(null, null);
  }
}
```


# Base Rules Provided in The Distribution #

## TemporaryFolder Rule 

- The TemporaryFolder Rule allows creation of files and folders that are guaranteed to be deleted when the test method finishes (whether it passes or fails):

```java
public static class HasTempFolder {
  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  @Test
  public void testUsingTempFolder() throws IOException {
    File createdFile = folder.newFile("myfile.txt");
    File createdFolder = folder.newFolder("subfolder");
    // ...
  }
} 
```

- `TemporaryFolder#newFolder(String... folderNames)` creates recursively deep temporary folders 
- `TemporaryFolder#newFile()` creates a randomly named new file, and `#newFolder()` creates a randomly named new folder

## ExternalResource Rules 

- ExternalResource is a base class for Rules (like TemporaryFolder)
  that set up an external resource before a test (a file, socket, server,
  database connection, etc.), and guarantee to tear it down afterward:

```java
public static class UsesExternalResource {
  Server myServer = new Server();
  
  @Rule
  public ExternalResource resource = new ExternalResource() {
    @Override
    protected void before() throws Throwable {
      myServer.connect();
    };
    
    @Override
    protected void after() {
      myServer.disconnect();
    };
  };
  
  @Test
  public void testFoo() {
    new Client().run(myServer);
  }
}
```

## ErrorCollector Rule

- The ErrorCollector Rule allows execution of a test to continue
  after the first problem is found (for example, to collect _all_ the 
  incorrect rows in a table, and report them all at once):

```java
public static class UsesErrorCollectorTwice {
  @Rule
  public ErrorCollector collector= new ErrorCollector();
  
  @Test
  public void example() {
    collector.addError(new Throwable("first thing went wrong"));
    collector.addError(new Throwable("second thing went wrong"));
  }
}
```

## Verifier Rule
- Verifier is a base class for Rules like ErrorCollector, which
  can turn otherwise passing test methods into failing tests if a verification
  check is failed.

```java
private static String sequence;

public static class UsesVerifier {
  @Rule
  public Verifier collector = new Verifier() {
    @Override
    protected void verify() {
      sequence += "verify ";
    }
  };

  @Test
  public void example() {
    sequence += "test ";
  }
}

@Test
public void verifierRunsAfterTest() {
  sequence = "";
  assertThat(testResult(UsesVerifier.class), isSuccessful());
  assertEquals("test verify ", sequence);
}
```

## TestWatchman/TestWatcher Rules

- TestWatcher replaces TestWatchman from version 4.9.  It implements TestRule, not MethodRule
-- http://junit-team.github.com/junit/javadoc/4.10/org/junit/rules/TestWatcher.html
- TestWatchman was introduced in JUnit 4.7, it uses a MethodRule, which is now deprecated. 
-- http://junit-team.github.com/junit/javadoc/4.10/org/junit/rules/TestWatchman.html
- TestWatcher (and the deprecated TestWatchman) are base classes for Rules that take note of the testing action, without modifying it.  For example, this class will keep a log of each passing and failing test:
     
```java
import static org.junit.Assert.fail;  
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class WatchmanTest {
  private static String watchedLog;

  @Rule
  public TestRule watchman = new TestWatcher() {
    @Override
    public Statement apply(Statement base, Description description) {
      return super.apply(base, description);
    }

    @Override
    protected void succeeded(Description description) {
      watchedLog += description.getDisplayName() + " " + "success!\n";
    }

    @Override
    protected void failed(Throwable e, Description description) {
      watchedLog += description.getDisplayName() + " " + e.getClass().getSimpleName() + "\n";
    }

    @Override
    protected void starting(Description description) {
      super.starting(description);
    }

    @Override
    protected void finished(Description description) {
      super.finished(description);
    }
  };

  @Test
  public void fails() {
    fail();
  }

  @Test
  public void succeeds() {
  }
}
```

## TestName Rule

- The TestName Rule makes the current test name available inside test methods:

```java
public class NameRuleTest {
  @Rule
  public TestName name = new TestName();
  
  @Test
  public void testA() {
    assertEquals("testA", name.getMethodName());
  }
  
  @Test
  public void testB() {
    assertEquals("testB", name.getMethodName());
  }
}
```

## Timeout Rule

- The Timeout Rule applies the same timeout to all test methods in a class:

```java
public static class HasGlobalTimeout {
  public static String log;
  
  @Rule
  public TestRule globalTimeout = new Timeout(20);
  
  @Test
  public void testInfiniteLoop1() {
    log+= "ran1";
    for(;;) {}
  }
  
  @Test
  public void testInfiniteLoop2() {
    log+= "ran2";
    for(;;) {}
  }
}
```

## ExpectedException Rules

- The ExpectedException Rule allows in-test specification
  of expected exception types and messages:
    
```java
public static class HasExpectedException {
  @Rule
  public ExpectedException thrown= ExpectedException.none();

  @Test
  public void throwsNothing() {

  }

  @Test
  public void throwsNullPointerException() {
    thrown.expect(NullPointerException.class);
    throw new NullPointerException();
  }

  @Test
  public void throwsNullPointerExceptionWithMessage() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("happened?");
    thrown.expectMessage(startsWith("What"));
    throw new NullPointerException("What happened?");
  }
}
```

## ClassRule ##

The `ClassRule` annotation extends the idea of method-level Rules,
adding static fields that can affect the operation of a whole class.  Any
subclass of `ParentRunner`, including the standard `BlockJUnit4ClassRunner` 
and `Suite` classes, will support `ClassRule`s.

For example, here is a test suite that connects to a server once before
all the test classes run, and disconnects after they are finished:

```java
@RunWith(Suite.class)
@SuiteClasses({A.class, B.class, C.class})
public class UsesExternalResource {
  public static Server myServer= new Server();

  @ClassRule
  public static ExternalResource resource= new ExternalResource() {
    @Override
    protected void before() throws Throwable {
      myServer.connect();
    };

    @Override
    protected void after() {
      myServer.disconnect();
    };
  };
}
```

## RuleChain ##

The RuleChain rule allows ordering of TestRules:

```java
public static class UseRuleChain {
    @Rule
    public TestRule chain= RuleChain
                           .outerRule(new LoggingRule("outer rule")
                           .around(new LoggingRule("middle rule")
                           .around(new LoggingRule("inner rule");

    @Test
    public void example() {
        assertTrue(true);
    }
}
```

writes the log

```
starting outer rule
starting middle rule
starting inner rule
finished inner rule
finished middle rule
finished outer rule
```

# Custom Rules #

Most custom rules can be implemented as an extension of the `ExternalResource` rule. However, if you need more information about the test class or method in question, you'll need to implement the `TestRule` interface.

```java
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class IdentityRule implements TestRule {
  @Override
  public Statement apply(final Statement base, final Description description) {
    return base;
  }
}
```

Of course, the power from implementing `TestRule` comes from using a combination of custom constructors, adding methods to the class for use in tests, and wrapping the provided `Statement` in a new `Statement`. For instance, consider the following test rule that provides a named logger for every test:

```java
package org.example.junit;

import java.util.logging.Logger;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class TestLogger implements TestRule {
  private Logger logger;

  public Logger getLogger() {
    return this.logger;
  }

  @Override
  public Statement apply(final Statement base, final Description description) {
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        logger = Logger.getLogger(description.getTestClass().getName() + '.' + description.getDisplayName());
        try {
          base.evaluate();
        } finally {
          logger = null;
        }
      }
    };
  }
}
```

Then that rule could be applied like so:

```java
import java.util.logging.Logger;

import org.example.junit.TestLogger;
import org.junit.Rule;
import org.junit.Test;

public class MyLoggerTest {

  @Rule
  public TestLogger logger = new TestLogger();

  @Test
  public void checkOutMyLogger() {
    final Logger log = logger.getLogger();
    log.warn("Your test is showing!");
  }

}
```