package com.hcl.footballkata;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Stream;

public class LocalFileDataSource {
    private final String path;
    private final Function<Stream<String>, Stream<String>> pipe;

    public LocalFileDataSource(String path, Function<Stream<String>, Stream<String>> pipe) {
        this.path = path;
        this.pipe = pipe;
    }

    public Stream<String> stream() {
        try {
            return pipe.apply(Files.lines(Paths.get(LocalFileDataSource.class.getClassLoader().getResource(path).toURI())));
        }
        catch (URISyntaxException | IOException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }


}
