package com.demo.hibernate.data;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class EMFactory {
    private static EntityManagerFactory emf;

    private EMFactory() {
    }

    //only be able to return single same instance of EMF, no use in making 1 factory per DAO..
    public static EntityManagerFactory getEMF() throws SQLException {
        if (emf == null)
            emf = Persistence.createEntityManagerFactory("florianDB");
        return emf;
    }

}
