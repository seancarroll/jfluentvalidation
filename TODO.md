TODO:
- move subjects into core/subjects package
- where to put exceptions?
- Do some of the CharSequence constraints need to be moved to String?
- What about adding a base class that checks property value for null
- what about adding a Not|NegateConstraint that wraps another constraint to negate it. similar ot what we do for softconstraint
- I'm not sure 100% happy with how we do error messages. when I get to localization take another look at how we could improve
- Do we need to return true when property is null instead of false...showcase examples.


Potential Constraints to Add
- CharSequence/String
    - isXmlEqualTo: `isXmlEqualTo(CharSequence expectedXml)`
    - inHexadecimal
    - inUnicode
    - hasLineCount: `hasLineCount(int expected)`
    - isEqualToNormalizingNewlines: `isEqualToNormalizingNewlines(CharSequence expected)`
    - isEqualToIgnoringNewLines: `isEqualToIgnoringNewLines(CharSequence expected)`
    - containsSequence: 
        - `containsSequence(CharSequence... values)`
        - `containsSequence(Iterable<? extends CharSequence> values)`
    - containsSubsequence
        - `containsSubsequence(CharSequence... values)`
        - `containsSubsequence(Iterable<? extends CharSequence> values)`
    - isURL 
    - isURI
    - isNotNullOrEmpty
- File
    - hasSameContentAs
       - `hasSameContentAs(File expected)`
       - `hasSameContentAs(File expected, Charset expectedCharset)`


