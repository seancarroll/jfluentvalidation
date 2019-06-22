package jfluentvalidation.validators.rulefor.object;

import java.util.Objects;

class IdOverriddenEquals {

    private String value;

    public IdOverriddenEquals(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdOverriddenEquals that = (IdOverriddenEquals) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}