package com.demo.hibernate.dao;

import com.demo.hibernate.data.EMFactory;
import com.demo.hibernate.model.Continent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.HashSet;
import java.util.Set;

public class ContinentDAOImpl implements ContinentDAO {
    private static ContinentDAO continentDAO;
    private EntityManagerFactory emf;

    private ContinentDAOImpl() {
        emf = EMFactory.getEMF();
    }

    public static ContinentDAO getInstance() {
        continentDAO = continentDAO == null ? new ContinentDAOImpl() : continentDAO;
        return continentDAO;

    }

    @Override
    public void addContinent(Continent continent) {
        var em = getEntityManager();
        em.getTransaction().begin();
        em.persist(continent);
        em.getTransaction().commit();
    }

    @Override
    public Continent getContinentById(int id) {
        var em = getEntityManager();
        return em.find(Continent.class, id);
    }


    @Override
    public Set<Continent> getAllContinents() {
        var em = getEntityManager();
        var query = em.createQuery("from Continent c");
        return new HashSet<>(query.getResultList());
    }

    @Override
    public void updateContinent(Continent continent) {
        var em = getEntityManager();
        em.getTransaction().begin();
        em.merge(continent);
        em.getTransaction().commit();
    }

    @Override
    public void deleteContinent(Continent continent) {
        var em = getEntityManager();
        em.getTransaction().begin();
        var c = em.find(Continent.class, continent.getId());
        em.remove(c);
        em.getTransaction().commit();
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
