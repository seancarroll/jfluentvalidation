package jfluentvalidation.internal;

import java.util.Locale;
import java.util.ResourceBundle;

// TODO: name sucks
public class LocalizationManager {

    private static final String RESOURCE_BUNDLE_BASE_NAME = "jfluentvalidation.ValidationMessages";

    public String getString(String key) {
        return getString(key, Locale.getDefault());
    }

    public String getString(String key, Locale locale) {

        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME, locale);

        return bundle.getString(key);
    }

}
