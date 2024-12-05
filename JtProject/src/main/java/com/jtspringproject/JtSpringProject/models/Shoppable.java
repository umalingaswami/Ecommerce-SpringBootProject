package com.jtspringproject.modeltest;

import java.util.List;

public interface Shoppable {
    int getPrecio();
    String getName();
    void add(Shoppable item);
    void remove(Shoppable item);
    List<Shoppable> getChildren();
} 