package com.jtspringproject.JtSpringProject.models;

import java.util.List;

public interface Shoppable {
    int getPrecio();
    String getName();
    void add(Shoppable item);
    void remove(Shoppable item);
    List<Shoppable> getChildren();
} 