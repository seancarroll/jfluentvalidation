package jfluentvalidation.rules;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.ValidationContext;

import java.util.List;

/**
 *
 * @param <T>
 * @param <P>
 */
public interface Rule<T, P> {

    /**
     *
     * @param context
     * @return
     */
    List<ValidationFailure> validate(ValidationContext<T, P> context);

    /**
     * Name of the rule-set to which this rule belongs.
     * @return
     */
    List<String> getRuleset();

    // TODO: add ruleset?

//    Subject<?, P> getSubject();

}
