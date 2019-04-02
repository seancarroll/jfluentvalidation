package jfluentvalidation.rules;

import java.util.ArrayList;
import java.util.List;

public class RuleSetCollection extends ArrayList<Rule<?, ?>> {

    private List<String> ruleSet = RuleSet.DEFAULT_LIST;

    public void setRuleSet(List<String> ruleSet) {
        this.ruleSet = ruleSet;
    }

    public void defaultRuleSet() {
        this.ruleSet = RuleSet.DEFAULT_LIST;
    }

    @Override
    public boolean add(Rule rule) {
        rule.setRuleSet(ruleSet);
        return super.add(rule);
    }
}
