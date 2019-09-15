package jfluentvalidation.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

// QUESTION: should we implement MessageInterpolator?
// TODO: review hibernate validator AbstractMessageInterpolator
// TODO: use ResourceBundle / PropertyResourceBundle
public class MessageFormatter {

    /**
     * Default property name placeholder
     */
    private static final String PROPERTY_NAME = "PropertyName";

    /**
     * Default property value placeholder
     */
    private static final String PROPERTY_VALUE = "PropertyValue";

    private static final Pattern LEFT_BRACE = Pattern.compile( "\\{", Pattern.LITERAL );
    private static final Pattern RIGHT_BRACE = Pattern.compile( "\\}", Pattern.LITERAL );
    private static final Pattern SLASH = Pattern.compile( "\\\\", Pattern.LITERAL );
    private static final Pattern DOLLAR = Pattern.compile( "\\$", Pattern.LITERAL );

    private final Map<String, Object> placeholderValues = new HashMap<>();

    /**
     * Appends a property name to the message.
     *
     * @param name  The name of the property
     * @return
     */
    public MessageFormatter appendPropertyName(String name) {
        return appendArgument(PROPERTY_NAME, name);
    }

    /**
     * Appends a property value to the message.
     *
     * @param value  The value of the property
     * @return
     */
    public MessageFormatter appendPropertyValue(Object value) {
        return appendArgument(PROPERTY_VALUE, value);
    }

    /**
     * Adds a value for a validation message placeholder.
     *
     * @param name
     * @param value
     * @return
     */
    public MessageFormatter appendArgument(String name, Object value) {
        placeholderValues.put(name, value);
        return this;
    }

    /**
     * Constructs the final message from the specified template.
     *
     * @param messageTemplate  Message template
     * @return The message with placeholders replaced with their appropriate values
     */
    public String buildMessage(String messageTemplate) {

        String result = messageTemplate;

        for (Map.Entry<String, Object> entry : placeholderValues.entrySet()) {
            result = replacePlaceholderWithValue(result, entry.getKey(), entry.getValue());
        }

        return result;
    }

    protected String replacePlaceholderWithValue(String template, String key, Object value) {
        String placeholder = getPlaceholder(key);
        return template.replace(placeholder, value.toString());
    }

    protected String getPlaceholder(String key) {
        switch (key) {
            case PROPERTY_NAME:
                return "{" + PROPERTY_NAME + "}";
            case PROPERTY_VALUE:
                return "{" + PROPERTY_VALUE + "}";
            default:
                return "{" + key + "}";
        }
    }

}
