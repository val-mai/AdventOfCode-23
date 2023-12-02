package it.valmai;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class AocInputReader {

    public static List<String> getLinesFromInput(String input) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(input);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        return reader.lines().toList();
    }

}
