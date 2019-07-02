package jfluentvalidation.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public final class InputStreams {

    // TODO: might not be the best place to put these
    public static List<String> readLines(InputStream inputStream) throws IOException {
        return readLines(new BufferedReader(new InputStreamReader(inputStream)));
    }

    // TODO: review guava CharStreams.readLines
    public static List<String> readLines(BufferedReader reader) throws IOException {
        try {
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } finally {
            // TODO: move to utility method?
            if (reader != null) {
                reader.close();
            }
        }
    }

    private InputStreams() {
        // statics only
    }
}
