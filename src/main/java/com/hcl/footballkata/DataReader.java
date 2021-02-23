package com.hcl.footballkata;

import java.util.function.Function;
import java.util.stream.Stream;

public interface  DataReader <F, S> {
    Pair<F, S> read(String[] data);
    Function<Stream<String>, Stream<String>> getLinesFilter();
}
