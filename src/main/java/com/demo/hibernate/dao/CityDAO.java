package com.demo.hibernate.dao;

import com.demo.hibernate.model.City;

import java.util.Set;

// DAO classes are usually defined as interfaces first and then are provided with an implementation.
// The reason for doing so is so we don't have to overhaul a lot of code in the case we create a different implementation of the DAO interface
// Since we provide a contract for all the methods a DAO needs, we can create new implementations and swap them as needed without having to change code
// further along the way.
public interface CityDAO {

    void addCity(City city);

    City getCityById(int id);

    Set<City> getAllCities();

    void updateCity(City city);

    void deleteCity(City city);
}
