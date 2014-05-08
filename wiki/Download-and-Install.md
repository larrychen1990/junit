To download and install JUnit you currently have the following options.
 
# Plain-old JAR

Download the following JARs and put them on your test classpath:

* [`junit.jar`](http://bit.ly/My9IXz)
* [`hamcrest-core.jar`](http://bit.ly/1gbl25b)

# Maven

Add a dependency to `junit:junit` in `test` scope.  (Note: 4.11 was the latest version as of the latest edit on this page.  It may now be stable.)

    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>