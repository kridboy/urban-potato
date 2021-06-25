package com.demo.hibernate.service;

import com.demo.hibernate.dao.ContinentDAO;
import com.demo.hibernate.dao.ContinentDAOImpl;
import com.demo.hibernate.model.Continent;

import java.util.Set;

public class ContinentServiceImpl implements ContinentService {
    ContinentDAO continentDAO;

    public ContinentServiceImpl() {
        this.continentDAO = ContinentDAOImpl.getInstance();
    }

    @Override
    public void addContinent(Continent continent) {
        this.continentDAO.addContinent(continent);
    }

    @Override
    public Continent getContinentById(int id) {
        return continentDAO.getContinentById(id);
    }

    @Override
    public Set<Continent> getAllContinents() {
        return continentDAO.getAllContinents();
    }

    @Override
    public void updateContinent(Continent continent) {
        continentDAO.updateContinent(continent);
    }

    @Override
    public void deleteContinent(Continent continent) {
        continentDAO.deleteContinent(continent);
    }
}
