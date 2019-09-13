TODO:
[ ] add tests for bytes / charsequence / stringbuffer / stringbuilder
[ ] Instead of returning a list of failures return an wrapper object. 
  should include boolean such as hasErrors. 
  See what other helper methods might be useful.
  Lots of tests will break
[ ] better way to pass context. I think we are juggling too many.
[ ] better way to do ruleForEach on collections, maps, etc
[ ] tests verify static method for each validation...I want to make sure we can use all approprivate validations with ruleForEach
[ ] move subjects into core/subjects package
[ ] where to put exceptions?
[ ] Do some of the CharSequence constraints need to be moved to String?
[ ] What about adding a base class that checks property value for null
[ ] what about adding a Not|NegateConstraint that wraps another constraint to negate it. similar ot what we do for softconstraint
[ ] I'm not sure 100% happy with how we do error messages. when I get to localization take another look at how we could improve
[ ] Do we need to return true when property is null instead of false...showcase examples.
[ ] have an issue getting the appropriate property name for nested fields ex: `ruleForString(p -> p.getAddress().getZip())`. 
I'm not sure what would be a good mechanism to determine if the property is nested or not. 
My initial thought is if I have some way to determine if its nested than I can return another bytebuddy proxy. 
One idea is to use serialablelamba. 
If I'm bringing back serialableLamba then I can use that for method references and fall back to bytebuddy for lambda expressions.
If I do that then I may need to alter when I create the proxy (right now its in the constructor of the validator) and how I grab the property name. 
[ ] Might want to check constraint null checks and when to return true vs false.
[ ] review assertj AbstractIterableAssert which has 
```java 
@Override
public SELF hasOnlyOneElementSatisfying(Consumer<? super ELEMENT> elementAssertions) {
  iterables.assertHasSize(info, actual, 1);
  elementAssertions.accept(actual.iterator().next());
  return myself;
}
```
and 
```java
@CheckReturnValue
public ELEMENT_ASSERT first() {
  isNotEmpty();
  return toAssert(actual.iterator().next(), navigationDescription("check first element"));
}
```  
which allows us to get the appropriate element assert. From javadoc
```
* If you have created the Iterable assertion using an {@link AssertFactory} or the element assert class,
* you will be able to chain {@code first()} with more specific typed assertion.
* <p>
* Example: use of {@code String} assertions after {@code first()}
* <pre><code class='java'> Iterable&lt;String&gt; hobbits = newArrayList("frodo", "sam", "pippin");
*
* // assertion succeeds
* // String assertions are available after first()
* assertThat(hobbits, StringAssert.class).first()
*                                        .startsWith("fro")
*                                        .endsWith("do");
* // assertion fails
* assertThat(hobbits, StringAssert.class).first()
*                                        .startsWith("pip");</code></pre>
```

I'm not a fan of having to pass in the assertion but as I work through this myself Ive come to this conclusion as well. 
AssertJ offers `ClassBasedNavigableIterableAssert` and `FactoryBasedNavigableIterableAssert`
[ ] Maybe add a 'OrCompositeConstraint'


Potential Constraints to Add
[ ] CharSequence/String
  [ ] isXmlEqualTo: `isXmlEqualTo(CharSequence expectedXml)`
  [ ] inHexadecimal
  [ ] inUnicode
  [ ] hasLineCount: `hasLineCount(int expected)`
  [ ] isEqualToNormalizingNewlines: `isEqualToNormalizingNewlines(CharSequence expected)`
  [ ] isEqualToIgnoringNewLines: `isEqualToIgnoringNewLines(CharSequence expected)`
  [ ] containsSequence: 
    [ ] `containsSequence(CharSequence... values)`
    [ ] `containsSequence(Iterable<? extends CharSequence> values)`
  [ ] containsSubsequence
    [ ] `containsSubsequence(CharSequence... values)`
    [ ] `containsSubsequence(Iterable<? extends CharSequence> values)`
  [ ] isURL 
  [ ] isURI
  [ ] isNotNullOrEmpty
[ ] File
  [ ] hasSameContentAs
    [ ] `hasSameContentAs(File expected)`
    [ ] `hasSameContentAs(File expected, Charset expectedCharset)`
[ ] Array
  [ ] DoesNotContain
[ ] Contains with a use of a Comparison strategy
[ ] Constraints that works with Optional including OptionalDouble, OptionalInt, OptionalLong
[ ] Dates (Date/Calendar/Time etc)
  [ ] isToday
  [ ] IsYear / IsMonth / IsDay
  [ ] assertIsInSameYearAs / areInSameYear
  [ ] assertIsInSameMonthAs / areInSameMonth
  [ ] assertIsInSameDayAs / areInSameDay
[ ] Have Date and Calendar use Instant similar to Hibernate Validator's AbstractInstantBasedTimeValidator
 
 

add test similar to 
```java
@SuppressWarnings({ "unchecked", "rawtypes" })
  @Disabled
  @Test
  public void raw_map_mixing_assertions_from_AbstractAssert_and_AbstractMapAssert() {
    Description description = emptyDescription();

    Map map1 = new java.util.HashMap();
    map1.put("Key1", "Value1");
    map1.put("Key2", "Value2");

    // try all base assertions followed by map specific ones using generics
    assertThat(map1).as("desc")
                    .containsOnlyKeys("Key1", "Key2")
                    .as(description)
                    .containsOnlyKeys("Key1", "Key2")
                    .describedAs(description)
                    .describedAs("describedAs")
                    .has(null)
                    .hasSameClassAs(map1)
                    .hasToString(map1.toString())
                    .is(null)
                    .isEqualTo(map1)
                    .isExactlyInstanceOf(Map.class)
                    .isIn(new ArrayList<>())
                    .isIn(Map.class)
                    .isInstanceOf(Map.class)
                    .isInstanceOfAny(Map.class, String.class)
                    .isNot(null)
                    .isNotEqualTo(null)
                    .isNotEmpty()
                    .isNotExactlyInstanceOf(String.class)
                    .isNotIn(new ArrayList<>())
                    .isNotIn(Map.class)
                    .isNotInstanceOf(Map.class)
                    .isNotInstanceOfAny(Map.class, String.class)
                    .isNotNull()
                    .isNotOfAnyClassIn(Map.class, String.class)
                    .isNotSameAs(null)
                    .isOfAnyClassIn(Map.class, String.class)
                    .isSameAs("")
                    .overridingErrorMessage("")
                    .withFailMessage("")
                    .withThreadDumpOnError()
                    .usingDefaultComparator()
                    .containsOnlyKeys("Key1", "Key2");
  }
```

handling temporal tolerance. how should we allow users to override?


https://issues.sonatype.org/secure/CreateIssue.jspa?issuetype=21&pid=10134
* Summary
* Description
* group Id: com.seanthomascarroll
* Project Url
* SCM url
