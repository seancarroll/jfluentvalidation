package jfluentvalidation.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.*;

/**
 *
 */
public final class Uris {

    private static final Pattern AMPERSAND_PATTERN = Pattern.compile("&");

    private Uris() {
        // statics only
    }

    /**
     *
     * @param query
     * @return
     */
    public static Map<String, List<String>> getParameters(String query) {
        if (query == null) {
            return Collections.emptyMap();
        }
        // TODO: This is probably not the most performant and we should maybe look at making it better
        return AMPERSAND_PATTERN.splitAsStream(query)
            .map(s -> Arrays.copyOf(s.split("="), 2))
            .collect(groupingBy(s -> decode(s[0]), mapping(s -> decode(s[1]), toList())));
    }

    /**
     *
     * @param encoded
     * @return
     */
    private static String decode(final String encoded) {
        try {
            return encoded == null ? null : URLDecoder.decode(encoded, "UTF-8");
        } catch(final UnsupportedEncodingException e) {
            // TODO: throw something other than a RuntimeException
            throw new RuntimeException("UTF-8 is a required encoding", e);
        }
    }
}
