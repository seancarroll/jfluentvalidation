package jfluentvalidation.rules;

import java.util.ArrayList;
import java.util.List;

public class RuleSet {

    public static final String ALL = "*";
    public static final String DEFAULT = "default"; // aka unassigned which might be a clearer name?
    public static final List<String> DEFAULT_LIST = new ArrayList<>();


    // TODO: not sure where the best place for this should be but putting it here for now just to work through the rules
    // FluentValidator has IValidatorSelector and DefaultValidatorSelector / RulesetValidatorSelector with method signature
    // public virtual bool CanExecute(IValidationRule rule, string propertyPath, ValidationContext context) {
    public static boolean canExecute(List<String> ruleSetsToExecute, Rule<?, ?> rule) {
        // 1. no rule set passed in. If you call `validate` without passing a ruleset then only rules not in a RuleSet will be executed
        // 2. can pass in "default" and get all rules not in a rule set
        // 3. want multiple rule sets -- You can execute multiple rulesets by using a comma-separated list of strings:
        // 4. want multiple rule sets including those not in a rule set -- you can also include rules not in any ruleset by specifying a ruleset of "default":
        // 5. want all rule sets -- You can force all rules to be executed regardless of whether or not they're in a ruleset by specifying a ruleset of "*":

        if (ruleSetsToExecute == null
            || ruleSetsToExecute.isEmpty()
            || (ruleSetsToExecute.size() == 1 && ruleSetsToExecute.get(0).equals("default"))) {
            return rule.getRuleSet() == null || rule.getRuleSet().isEmpty();
        }

        for (String passedInRule : ruleSetsToExecute) {
            if (passedInRule.equals("*")) {
                return true;
            }

            if (passedInRule.equals("default") && (rule.getRuleSet() == null || rule.getRuleSet().isEmpty())) {
                return true;
            }

            if (rule.getRuleSet().contains(passedInRule)) {
                return true;
            }
        }

        return false;
    }
}
