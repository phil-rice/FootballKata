package com.hcl.footballkata;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FootballSource {
    static public Stream<String> stream(String resourceName) {
        try {
            return Files.lines(Paths.get(FootballSource.class.getClassLoader().getResource(resourceName).toURI())).
                    skip(1).
                    filter(s -> !s.trim().startsWith("-")).
                    filter(s -> !s.isEmpty());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
