package com.hcl.footballkata;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Stream;

public class Main {

    public static String[] parseLineToValues(String line) {
        return line.split("\\s+");
    }

    public static void main(String[] args) throws NumberFormatException {
        readDataFile("football.dat", new FootballReader())
                .min(Comparator.comparingInt(a -> a.snd))
                .ifPresent(p -> System.out.println(p.fst));

        readDataFile("weather.dat", new WeatherReader())
                .min(Comparator.comparingInt(a -> a.snd))
                .ifPresent(p -> System.out.println(p.fst));
    }

    public static <F, S> Stream<Pair<F, S>> readDataFile(String path, DataReader<F, S> reader) {
        return (new LocalFileDataSource(path, reader.getLinesFilter())).stream()
                .map(Main::parseLineToValues)
                .map(reader::read);
    }


    public static class FootballReader implements DataReader<String, Integer> {
        public FootballReader() {}

        @Override
        public Pair<String, Integer> read(String[] data) {
            Integer fCell = Integer.parseInt(data[7]);
            Integer aCell = Integer.parseInt(data[9]);
            return Pair.of(data[2], Math.abs(fCell - aCell));
        }

        @Override
        public Function<Stream<String>, Stream<String>> getLinesFilter() {
            return (stream) ->
                    stream.skip(1).
                            filter(s -> !s.trim().startsWith("-")).
                            filter(s -> !s.isEmpty());
        }
    }

    public static class WeatherReader implements DataReader<Integer, Integer> {
        public WeatherReader() {}

        @Override
        public Pair<Integer, Integer> read(String[] data) {
            Integer day = Integer.parseInt(data[1]);
            Integer maxTemp = parseTemp(data[2]);
            Integer minTemp = parseTemp(data[3]);
            return Pair.of(day, Math.abs(maxTemp - minTemp));
        }

        @Override
        public Function<Stream<String>, Stream<String>> getLinesFilter() {
            return (stream) ->
                stream.skip(1).
                        filter(s -> !s.isEmpty())
                        .filter(s -> !s.trim().startsWith("mo"));
        }

        public Integer parseTemp(String val) {
            return Integer.parseInt(val.replace("*", ""));
        }
    }
}
