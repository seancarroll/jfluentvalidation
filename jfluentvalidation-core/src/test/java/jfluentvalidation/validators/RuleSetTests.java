package jfluentvalidation.validators;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

class RuleSetTests {

    // RulesetTests
    // Executes_rules_in_specified_ruleset
    // Executes_rules_not_specified_in_ruleset
    // Ruleset_cascades_to_child_validator -- includes validator.RuleFor in ruleset
    // Ruleset_cascades_to_child_collection_validator -- includes ruleFor items in collection
    // Executes_multiple_rulesets
    // Executes_all_rules
    // Executes_rules_in_default_ruleset_and_specific_ruleset
    // Trims_spaces
    // Applies_multiple_rulesets_to_rule
    // Executes_in_rule_in_ruleset_and_default
    // Executes_in_rule_in_default_and_none


    @Test
    void unassigned() {

    }

    @Test
    void unassignedWithDefault() {

    }

    @Test
    void multipleRuleSet() {

    }

    @Test
    void multipleRuleSetWithUnassigned() {

    }

    @Test
    void wildcard() {

    }

    @Test
    void map() {
        Map<String, Integer> m = new HashMap<>();
        m.put("hello", 1);
        m.put("foo", 2);

        Function<Map<String, Integer>, Collection<Map.Entry<String, Integer>>> fes = map -> map.entrySet();
        Collection<Map.Entry<String, Integer>> ses = fes.apply(m);
        Collection<Map.Entry<String, Integer>> a = getCollection(m, fes);

        Function<Map<String, Integer>, Collection<String>> fks = map -> map.keySet();
        Collection<String> sks = fks.apply(m);
        Collection<String> b = getCollection(m, fks);

        Function<Map<String, Integer>, Collection<Integer>> fmv = map -> map.values();
        Collection<Integer> c = fmv.apply(m);
        Collection<Integer> d = getCollection(m, fmv);
    }

    <K, V, T> Collection<T> getCollection(Map<K, V> m, Function<Map<K, V>, Collection<T>> f) {
        return f.apply(m);
    }

}
