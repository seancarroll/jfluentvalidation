package jfluentvalidation.messageinterpolation;

import java.util.HashMap;
import java.util.Map;

public class MessageContext {

    /**
     * Default property name placeholder
     */
    private static final String PROPERTY_NAME = "PropertyName";

    /**
     * Default property value placeholder
     */
    private static final String PROPERTY_VALUE = "PropertyValue";

    private final Map<String, Object> placeholderValues = new HashMap<>();

    /**
     * Appends a property name to the message.
     *
     * @param name  The name of the property
     * @return
     */
    public MessageContext appendPropertyName(String name) {
        return appendArgument(PROPERTY_NAME, name);
    }

    /**
     * Appends a property value to the message.
     *
     * @param value  The value of the property
     * @return
     */
    public MessageContext appendPropertyValue(Object value) {
        return appendArgument(PROPERTY_VALUE, value);
    }

    /**
     * Adds a value for a validation message placeholder.
     *
     * @param name
     * @param value
     * @return
     */
    public MessageContext appendArgument(String name, Object value) {
        placeholderValues.put(name, value);
        return this;
    }

    public Map<String, Object> getPlaceholderValues() {
        return placeholderValues;
    }
}
