package jfluentvalidation.constraints;

import java.util.function.Predicate;

// TODO: does this make sense to have?
public class AbstractConstraint {

    private String ruleSet;
    private String customMessage; // failure message / constraint message. Can this also be a localization message source key?
    private Object[] messageArgs;
    private Predicate whenClause;


}
