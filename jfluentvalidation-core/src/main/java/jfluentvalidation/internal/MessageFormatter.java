package jfluentvalidation.internal;

import java.util.*;
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
    private final List<Object> additionalArguments = new ArrayList<>();

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
    public MessageFormatter AppendPropertyValue(Object value) {
        return appendArgument(PROPERTY_VALUE, value);
    }

    /// <summary>
    /// Adds additional arguments to the message for use with standard string placeholders.
    /// </summary>
    /// <param name="additionalArgs">Additional arguments</param>
    /// <returns></returns>
    public MessageFormatter appendAdditionalArguments(Object... additionalArgs) {
        additionalArguments.addAll(Arrays.asList(additionalArgs));
        return this;
    }

    /**
     * Constructs the final message from the specified template.
     *
     * @param messageTemplate  Message template
     * @return The message with placeholders replaced with their appropriate values
     */
    public String BuildMessage(String messageTemplate) {

        String result = messageTemplate;

        for (Map.Entry entry : placeholderValues.entrySet()) {
            // result = ReplacePlaceholderWithValue(result, pair.Key, pair.Value);
            // result
        }

        for (Object arg : additionalArguments) {
            // return string.Format(result, _additionalArguments);
        }

        return result;
    }

//    protected virtual string ReplacePlaceholderWithValue(string template, string key, object value) {
//        string placeholder =  GetPlaceholder(key);
//        return template.Replace(placeholder, value?.ToString());
//    }
//
//    protected string GetPlaceholder(string key) {
//        // Performance: String concat causes much overhead when not needed. Concatting constants results in constants being compiled.
//        switch (key) {
//            case PropertyName:
//                return "{" + PropertyName + "}";
//            case PropertyValue:
//                return "{" + PropertyValue + "}";
//            default:
//                return "{" + key + "}";
//        }
//    }

}
