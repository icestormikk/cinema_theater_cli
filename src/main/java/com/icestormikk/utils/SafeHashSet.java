package com.icestormikk.utils;

import java.util.Collection;
import java.util.HashSet;

public class SafeHashSet<T> extends HashSet<T> {
    public SafeHashSet() {}

    public SafeHashSet(Collection<? extends T> c) {
        super(c);
    }

    public static <R> SafeHashSet<R> without(SafeHashSet<R> set, R element) {
        if (!set.contains(element))
            return new SafeHashSet<>(set);

        SafeHashSet<R> newSet = new SafeHashSet<>(set);
        newSet.remove(element);

        return newSet;
    }

    public static <R> SafeHashSet<R> with(SafeHashSet<R> set, R element) {
        if (set.contains(element))
            return new SafeHashSet<>(set);

        SafeHashSet<R> newSet = new SafeHashSet<>(set);
        newSet.add(element);

        return newSet;
    }
}
