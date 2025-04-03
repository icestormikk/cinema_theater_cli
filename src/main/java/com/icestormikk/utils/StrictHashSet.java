package com.icestormikk.utils;

import java.util.Collection;
import java.util.HashSet;

public class StrictHashSet<T> extends HashSet<T> {
    public StrictHashSet() {}

    public StrictHashSet(Collection<? extends T> c) {
        super(c);
    }

    public StrictHashSet<T> without(T element) {
        if (!contains(element))
            return new StrictHashSet<>(this);

        StrictHashSet<T> newSet = new StrictHashSet<>(this);
        newSet.remove(element);

        return newSet;
    }

    public StrictHashSet<T> with(T element) {
        if (contains(element))
            return new StrictHashSet<>(this);

        StrictHashSet<T> newSet = new StrictHashSet<>(this);
        newSet.add(element);

        return newSet;
    }
}
