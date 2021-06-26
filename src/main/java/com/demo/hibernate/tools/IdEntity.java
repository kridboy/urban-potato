package com.demo.hibernate.tools;

// this is a simple interface to enable us to reduce redundant code
// otherwise we would have to write the same method X-amount of times when logic is basically identical
// We can use this interface because the entity classes already implement those methods
// and they're all we need for certain operations
public interface IdEntity {
    int getId();
    String toString();
}
