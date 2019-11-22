package jfluentvalidation.messageinterpolation;

import org.mvel2.MVEL;
import org.mvel2.integration.impl.MapVariableResolverFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
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

    private static final Pattern LEFT_BRACE = Pattern.compile("\\{", Pattern.LITERAL);
    private static final Pattern RIGHT_BRACE = Pattern.compile("}", Pattern.LITERAL);
    private static final Pattern SLASH = Pattern.compile("\\\\", Pattern.LITERAL);
    private static final Pattern DOLLAR = Pattern.compile("\\$", Pattern.LITERAL);  // EL_DESIGNATOR

    private static final Pattern TOKEN_PATTERN = Pattern.compile("([^${]|(?<=\\\\)[${])+|\\$?\\{[^${]*}");

    /**
     * Default property name placeholder
     */
    private static final String PROPERTY_NAME = "PropertyName";

    /**
     * Default property value placeholder
     */
    private static final String PROPERTY_VALUE = "PropertyValue";

    /**
     * The default locale in the current JVM.
     */
    private final Locale defaultLocale;

    private ConcurrentHashMap<LocalizedMessage, String> localizedMessageCache;

    private final Set<String> userBundles;
    private final Set<Locale> localesToInitialize;
    private final boolean cachingEnabled;

    // TODO: allow users to set resource bundles
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
        if (cachingEnabled) {
            localizedMessageCache = new ConcurrentHashMap<>();
        }
    }

    public String interpolate(String key, Map<String, Object> context) {
        return interpolate(key, context, defaultLocale);
    }

    public String interpolate(String message, Map<String, Object> context, Locale locale) {
        // if the message does not contain any message parameter, no need to continue just return the unescaped message.
        // It avoids storing the message in the cache and a cache lookup.
        if (message.indexOf('{') < 0) {
            return replaceEscapedLiterals(message);
        }

        final String resolvedMessage;
        // either retrieve message from cache, or if message is not yet there or caching is disabled
        resolvedMessage = cachingEnabled
            ? localizedMessageCache.computeIfAbsent(new LocalizedMessage(message, locale), lm -> resolveMessage(message, locale))
            : resolveMessage(message, locale);

        Matcher matcher = TOKEN_PATTERN.matcher(resolvedMessage);
        List<Token> tokens = new ArrayList<>();
        while (matcher.find()) {
            tokens.add(new Token(matcher.group()));
        }

        // TODO: clean up
        List<String> resolvedMessages = new ArrayList<>();
        for (Token t : tokens) {
            if (t.isParameter()) {
                String placeHolderKey = removeCurlyBraces(t.getValue());
                Object placeHolderValue = context.get(placeHolderKey);
                String resolvedToken = placeHolderValue == null
                    ? t.getValue()
                    : replacePlaceholderWithValue(t.getValue(), placeHolderKey, placeHolderValue);
                resolvedMessages.add(resolvedToken);
            } else if (t.isEL()) {
                // TODO: catch unresolvable property or identifier exception. Others?
                resolvedMessages.add(MVEL.evalToString(removeDollarAndCurlyBraces(t.getValue()), new MapVariableResolverFactory(context)));
            } else {
                resolvedMessages.add(t.getValue());
            }
        }

        // TODO: replace escaped?
        return String.join("", resolvedMessages);
    }

    private String resolveMessage(String message, Locale locale) {
        try {
            // Grab default bundle
            // Grab user bundles...Resolve any message parameters by using them as key for the resource bundle _ValidationMessages_. If
            // this bundle contains an entry for a given message parameter, that parameter will be replaced in the
            // message with the corresponding value from the bundle. ch04.asciidoc

            // ResourceBundle userResourceBundle = userResourceBundleLocator.getResourceBundle( locale );
            // ResourceBundle defaultResourceBundle = defaultResourceBundleLocator.getResourceBundle( locale );

            ResourceBundle bundle = ResourceBundle.getBundle(DEFAULT_VALIDATION_MESSAGES, locale);
            return bundle.getString(removeCurlyBraces(message));
        } catch (MissingResourceException ex) {
            return message;
        }
    }

    private static String replacePlaceholderWithValue(String template, String key, Object value) {
        String placeholder = getPlaceholder(key);
        return template.replace(placeholder, value == null ? "" : value.toString());
    }

    private static String getPlaceholder(String key) {
        // Probably a micro optimization...
        // Concatenate constants results in constants being compiled so we avoid String concatenation when not needed.
        switch (key) {
            case PROPERTY_NAME:
                return "{" + PROPERTY_NAME + "}";
            case PROPERTY_VALUE:
                return "{" + PROPERTY_VALUE + "}";
            default:
                return "{" + key + "}";
        }
    }

    private static String removeCurlyBraces(String parameter) {
        return parameter.substring(1, parameter.length() - 1);
    }

    private static String removeDollarAndCurlyBraces(String parameter) {
        return parameter.substring(2, parameter.length() - 1);
    }

    private static String replaceEscapedLiterals(String resolvedMessage) {
        if (resolvedMessage.indexOf('\\') > -1) {
            resolvedMessage = LEFT_BRACE.matcher(resolvedMessage).replaceAll("{");
            resolvedMessage = RIGHT_BRACE.matcher(resolvedMessage).replaceAll("}");
            resolvedMessage = SLASH.matcher(resolvedMessage).replaceAll(Matcher.quoteReplacement("\\"));
            resolvedMessage = DOLLAR.matcher(resolvedMessage).replaceAll(Matcher.quoteReplacement("$"));
        }
        return resolvedMessage;
    }

}
