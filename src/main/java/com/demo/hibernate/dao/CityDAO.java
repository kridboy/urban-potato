package com.demo.hibernate.dao;

import com.demo.hibernate.model.City;

import java.util.Set;

public interface CityDAO {

    void addCity(City city);

    City getCityById(int id);

    Set<City> getAllCities();

    void updateCity(City city);

    void deleteCity(City city);
}
