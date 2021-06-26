package com.demo.hibernate.service;

import com.demo.hibernate.dao.CityDAO;
import com.demo.hibernate.dao.CityDAOImpl;
import com.demo.hibernate.model.City;

import java.util.Set;

// Service classes should never perform business logic,
// they should only be concerned with storing and retrieving data from DAO classes
// they serve as an intermediate layer between data and front-end logic.
public class CityServiceImpl implements CityService {
    private final CityDAO cityDAO;

    public CityServiceImpl() {
        this.cityDAO = CityDAOImpl.getInstance();
    }

    @Override
    public void addCity(City city) {
        cityDAO.addCity(city);
    }

    @Override
    public City getCityById(int id) {
        return cityDAO.getCityById(id);
    }

    @Override
    public Set<City> getAllCities() {
        return cityDAO.getAllCities();
    }

    @Override
    public void updateCity(City city) {
        cityDAO.updateCity(city);
    }

    @Override
    public void deleteCity(City city) {
        cityDAO.deleteCity(city);
    }
}
