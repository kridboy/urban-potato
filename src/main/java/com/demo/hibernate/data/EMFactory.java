package com.demo.hibernate.data;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFactory {
    private static EntityManagerFactory emf;

    private EMFactory() {
    }

    // only be able to return single same instance of EMF, no use in making 1 factory per DAO..
    // this also takes care of problem where sysout gets cluttered because of unnecessary INFO and WARNING messages
    // being repeated due to multiple factory generation. A factory provides all objects and should not be instantiated
    // multiple times.
    public static EntityManagerFactory getEMF() {
        if (emf == null)
            emf = Persistence.createEntityManagerFactory("florianDB");
        return emf;
    }

}
