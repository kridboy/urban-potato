package com.demo.hibernate.dao;

import com.demo.hibernate.data.EMFactory;
import com.demo.hibernate.model.City;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class CityDAOImpl implements CityDAO {
    private static CityDAO cityDAO;
    private EntityManagerFactory emf;

    private CityDAOImpl() {
        try{
            emf = EMFactory.getEMF();
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    public static CityDAO getInstance() {
        //Singleton -> private constructor, so you can only get once instance
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
        var em= getEntityManager();
        return em.find(City.class,id);
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
        var c = em.find(City.class,city.getId());
        em.remove(c);
        em.getTransaction().commit();
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
