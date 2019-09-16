package jfluentvalidation.internal;

import java.util.*;
import java.util.regex.Pattern;

public class ResourceBundleMessageInterpolator {

    /**
     * The name of the default message bundle.
     */
    public static final String DEFAULT_VALIDATION_MESSAGES = "jfluentvalidation.ValidationMessages";

    /**
     * The name of the user-provided message bundle as defined in the specification.
     */
    public static final String USER_VALIDATION_MESSAGES = "ValidationMessages";

    private static final Pattern LEFT_BRACE = Pattern.compile( "\\{", Pattern.LITERAL );
    private static final Pattern RIGHT_BRACE = Pattern.compile( "\\}", Pattern.LITERAL );
    private static final Pattern SLASH = Pattern.compile( "\\\\", Pattern.LITERAL );
    private static final Pattern DOLLAR = Pattern.compile( "\\$", Pattern.LITERAL );  // EL_DESIGNATOR



    /**
     * The default locale in the current JVM.
     */
    private final Locale defaultLocale;

    // private final VariableResolverFactory variableResolverFactory;


    public ResourceBundleMessageInterpolator() {
        this.defaultLocale = Locale.getDefault();
        // this.variableResolverFactory = new MapVariableResolverFactory();
    }

    public String interpolate(String key, Map<String, Object> context) {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(DEFAULT_VALIDATION_MESSAGES, defaultLocale);
            String expression = bundle.getString(key);

            String[] tokens = expression.split("(?=\\$)");
            List<String> eval = new ArrayList<>();
            for (String token : tokens) {
                if (token.startsWith("$")) {
//                String t = token.substring(2, token.length() - 16);
//                eval.add(MVEL.evalToString(t, new MapVariableResolverFactory(context)));
                }
            }


            // String evaluated = MVEL.evalToString(expression, new MapVariableResolverFactory(context));
            return "";
        } catch (MissingResourceException ex) {
            return key;
        }
    }

    private String removeCurlyBraces(String parameter) {
        return parameter.substring( 1, parameter.length() - 1 );
    }
}
