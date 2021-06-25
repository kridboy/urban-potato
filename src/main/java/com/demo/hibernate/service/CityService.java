package com.demo.hibernate.service;

import com.demo.hibernate.model.City;

import java.util.Set;

public interface CityService {
    void addCity(City city);

    City getCityById(int id);

    Set<City> getAllCities();

    void updateCity(City city);

    void deleteCity(City city);
}
