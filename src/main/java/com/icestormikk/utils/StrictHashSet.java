package com.icestormikk.utils;

import java.util.HashSet;

public class StrictHashSet<T> extends HashSet<T> {
    @Override
    public boolean remove(Object element) {
        if (!contains(element)) {
            throw new RuntimeException("Element not found in the set.");
        }
        return super.remove(element);
    }

    @Override
    public boolean add(T element) {
        if (contains(element)) {
            throw new RuntimeException("Element already exists in the set.");
        }
        return super.add(element);
    }
}
