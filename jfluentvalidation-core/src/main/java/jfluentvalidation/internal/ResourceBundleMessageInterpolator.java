package jfluentvalidation.internal;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
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

    private static final Pattern TOKEN_PATTERN = Pattern.compile("([^\\$\\{]|(?<=\\\\)[${])+|\\$?\\{[^\\$\\{]*}");

    // TODO: fields for
    // - user bundles

//    /**
//     * Step 1-3 of message interpolation can be cached. We do this in this map.
//     */
//    private final ConcurrentReferenceHashMap<LocalizedMessage, String> resolvedMessages;
//
//    /**
//     * Step 4 of message interpolation replaces message parameters. The token list for message parameters is cached in this map.
//     */
//    private final ConcurrentReferenceHashMap<String, List<Token>> tokenizedParameterMessages;
//
//    /**
//     * Step 5 of message interpolation replaces EL expressions. The token list for EL expressions is cached in this map.
//     */
//    private final ConcurrentReferenceHashMap<String, List<Token>> tokenizedELMessages;
//
//    /**
//     * Flag indicating whether this interpolator should cache some of the interpolation steps.
//     */

    private ConcurrentHashMap<String, List<Token>> resolvedMessages;
    private ConcurrentHashMap<String, List<Token>> tokenizedParameterMessages;
    private ConcurrentHashMap<String, List<Token>> tokenizedELMessages;


    /**
     * The default locale in the current JVM.
     */
    private final Locale defaultLocale;


    // private final VariableResolverFactory variableResolverFactory;

    private final Set<String> userBundles;
    private final Set<Locale> localesToInitialize;
    private final boolean cachingEnabled;

    public ResourceBundleMessageInterpolator() {
        this(Collections.emptySet(), Collections.emptySet(), true);
    }

    // TODO: need a way to allow for locale to be changed at runtime.
    // From a Spring perspective we would need a way to integration with their LocaleHolderContext (sp?)
    public ResourceBundleMessageInterpolator(Set<String> userBundles, Set<Locale> localesToInitialize, boolean cachingEnabled) {
        this.defaultLocale = Locale.getDefault();
        this.userBundles = userBundles;
        this.localesToInitialize = localesToInitialize;
        this.cachingEnabled = cachingEnabled;

        // this.variableResolverFactory = new MapVariableResolverFactory();
    }

    public String interpolate(String message, Map<String, Object> context, Locale locale) {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(DEFAULT_VALIDATION_MESSAGES, locale);
            String messageTemplate = bundle.getString(message);

            // TODO: need to split messages into tokens
            // 1. parameters {}...I think hibernate validator also uses {} as a marker for resource bundle keys
            // 2. EL expressions ${}
            // 3. everything else

            // Grab default bundle
            // Grab user bundles...Resolve any message parameters by using them as key for the resource bundle _ValidationMessages_. If
            //this bundle contains an entry for a given message parameter, that parameter will be replaced in the
            //message with the corresponding value from the bundle. ch04.asciidoc
            // Grab contributor bundles...do we need this? To contribute default messages for your custom constraints, place a file _ContributorValidationMessages.properties_

            // cache tokens (text, params, EL expressions)

            // allow users to set resource bundles


            // String[] tokens = expression.split("(?=\\$)");

            // these two work in the regex tester
            // [^${]+|\$?{([^${]*)}
            // [^${]+|\$?{[^${]*}

            // TODO: this does not handle escaped characters (\$, \{, \}) so we can treat them as literals.

            // original one that works for non-escaped
            // [^\$\{]+|\$?\{[^\$\{]*}
            // "[^\\$\\{]+|\\$?\\{[^\\$\\{]*}"
            // "[^${]+|\\$?\\{([^${]*)}"

            // might work for escaped
            // "[^\\$\\{]+|\\$?\\{[^\\$\\{]*}"
            // ([^${]|((?<=\\)[${]))+|\$?{[^${]*}
            // ([^${]|(?<=\\)[${])+|\$?{[^${]*}

            // [^\$\{]+|\$?\{[^\$\{]*}
            // ([^\$\{]|(?<\=\)[\$\{])+|\$?{[^\$\{]*}

            // escape --> ([^\$\{]|(?<=\\)[${])+|\$?\{[^\$\{]*}
            // java ---> "([^\\$\\{]|(?<=\\\\)[${])+|\\$?\\{[^\\$\\{]*}"


            // regex negative lookbehind (?<!Y)X matches an X that is not preceded by a Y
            // (?<!\\{1,2})

            // "[^\\$\\{]+|\\$?\\{[^\\$\\{]*}"

            // js works but not java "([^${]|(?<=\\\\)[${])+|\\$?{[^${]*}"




            // Pattern pattern = Pattern.compile("([^\\$\\{]|(?<=\\\\)[${])+|\\$?\\{[^\\$\\{]*}");
            Matcher matcher = TOKEN_PATTERN.matcher(messageTemplate);
            List<Token> matches = new ArrayList<>();
            while (matcher.find()) {
                matches.add(new Token(matcher.group()));
            }



            // TODO: Replace parameters. iterate over placeholders...

            // TODO: EL

            // TODO: replace escaped

//            String group = matcher.group();
//            if (group.startsWith("$")) {
//                sb.append((MVEL.evalToString(group, new MapVariableResolverFactory(context)));
//            } else if (group.startsWith("{")) {
//                sb.append()
//            } else {
//                sb.append(group);
//            }


            String[] tokens = messageTemplate.split("[^\\$\\{]+|\\$?\\{[^\\$\\{]*}");
            List<String> eval = new ArrayList<>();
            for (String token : tokens) {
//                if (token.startsWith("$")) {
//                String t = token.substring(2, token.length() - 16);
//                eval.add(MVEL.evalToString(t, new MapVariableResolverFactory(context)));
//                }
            }


            // String evaluated = MVEL.evalToString(expression, new MapVariableResolverFactory(context));
            return "";
        } catch (MissingResourceException ex) {
            return message;
        }
    }

    public String interpolate(String key, Map<String, Object> context) {
        return interpolate(key, context, defaultLocale);
    }

    protected String replacePlaceholderWithValue(String template, String key, Object value) {
        String placeholder = getPlaceholder(key);
        return template.replace(placeholder, value == null ? "" : value.toString());
    }

    /**
     * Default property name placeholder
     */
    private static final String PROPERTY_NAME = "PropertyName";

    /**
     * Default property value placeholder
     */
    private static final String PROPERTY_VALUE = "PropertyValue";

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

    private String removeCurlyBraces(String parameter) {
        return parameter.substring( 1, parameter.length() - 1 );
    }


    // Hibernate validator classes...
    // InterpolationTerm
    // TermResolver
    // ElTermResolver
    // ParameterTermResolver
    // Token
    //  private boolean isParameter;
    //  private boolean isEL;
    //  private String value;

    /**
     * Meta character to designate an EL expression.
     */
    private static final String EL_DESIGNATION_CHARACTER = "$";

    private static final String PARAMETER_DESIGNATION_CHARACTER = "{";

    public static boolean isElExpression(String expression) {
        return expression.startsWith( EL_DESIGNATION_CHARACTER );
    }

    public enum InterpolationTermType {
        /**
         * EL message expression, eg ${foo}.
         */
        EL,

        /**
         * Message parameter, eg {foo}.
         */
        PARAMETER
    }

    public class Token {
        private boolean isParameter;
        private boolean isEL;
        private String value;

        public Token(String value) {
            this.value = Ensure.notNull(value);
            if (value.startsWith(EL_DESIGNATION_CHARACTER)) {
                isEL = true;
            } else if (value.startsWith(PARAMETER_DESIGNATION_CHARACTER)) {
                isParameter = true;
            }
        }

        public boolean isParameter() {
            return isParameter;
        }

        public boolean isEL() {
            return isEL;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder( "Token{" );
            sb.append( "value='" ).append( value ).append( '\'' );
            sb.append( ", isEL=" ).append( isEL );
            sb.append( ", isParameter=" ).append( isParameter );
            sb.append( '}' );
            return sb.toString();
        }
    }


}
