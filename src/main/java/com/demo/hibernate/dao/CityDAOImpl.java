package com.demo.hibernate.dao;

import com.demo.hibernate.data.EMFactory;
import com.demo.hibernate.model.City;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.HashSet;
import java.util.Set;

public class CityDAOImpl implements CityDAO {
    private static CityDAO cityDAO;
    private final EntityManagerFactory emf;

    private CityDAOImpl() {
        emf = EMFactory.getEMF();
    }

    public static CityDAO getInstance() {
        // Singleton -> With a private constructor and a single reference to a DAO implementation we force a singleton instance
        // there can never be two or more separate DAO implementations
        if (cityDAO == null)
            cityDAO = new CityDAOImpl();

        return cityDAO;
    }

    @Override
    public void addCity(City city) {
        var em = getEntityManager();
        em.getTransaction().begin();
        em.persist(city);
        em.getTransaction().commit();

    }

    @Override
    public City getCityById(int id) {
        var em = getEntityManager();
        return em.find(City.class, id);
    }

    @Override
    public Set<City> getAllCities() {
        var em = getEntityManager();
        var query = em.createQuery("from City c");
        return new HashSet<>(query.getResultList());
    }

    @Override
    public void updateCity(City city) {
        var em = getEntityManager();
        em.getTransaction().begin();
        em.merge(city);
        em.getTransaction().commit();
    }

    @Override
    public void deleteCity(City city) {
        var em = getEntityManager();
        em.getTransaction().begin();
        var c = em.find(City.class, city.getId());
        em.remove(c);
        em.getTransaction().commit();
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
