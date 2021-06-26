package com.demo.hibernate.dao;

import com.demo.hibernate.data.EMFactory;
import com.demo.hibernate.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.HashSet;
import java.util.Set;

public class CountryDAOImpl implements CountryDAO {
    private static CountryDAO countryDAO;

    private EntityManagerFactory emf;

    private CountryDAOImpl() {
        emf = EMFactory.getEMF();
    }

    public static CountryDAO getInstance() {
        if (countryDAO == null)
            countryDAO = new CountryDAOImpl();
        return countryDAO;
    }

    @Override
    public void addCountry(Country country) {
        var em = getEntityManager();
        em.getTransaction().begin();
        em.persist(country);
        em.getTransaction().commit();
    }

    @Override
    public Country getCountryById(int id) {
        var em = getEntityManager();
        return em.find(Country.class, id);
    }

    @Override
    public Set<Country> getAllCountries() {
        var em = getEntityManager();
        var query = em.createQuery("from Country c");
        return new HashSet<>(query.getResultList());
    }

    @Override
    public void updateCountry(Country country) {
        var em = getEntityManager();
        em.getTransaction().begin();
        em.merge(country);
        em.getTransaction().commit();
    }

    @Override
    public void deleteCountry(Country country) {
        var em = getEntityManager();
        em.getTransaction().begin();
        var c = em.find(Country.class, country.getId());
        em.remove(c);
        em.getTransaction().commit();
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
