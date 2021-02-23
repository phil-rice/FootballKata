package com.hcl.footballkata;

public class Pair<F, S> {
    public final S snd;
    public final F fst;

    private Pair(F fst, S snd) {
        this.snd = snd;
        this.fst = fst;
    }

    public static <F, S> Pair<F, S> of(F fst, S snd) {
        return new Pair<>(fst, snd);
    }

    @Override
    public boolean equals(Object obj) {
        //correct implemention
        return super.equals(obj);
    }
}
