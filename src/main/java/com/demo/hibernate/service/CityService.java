package com.demo.hibernate.service;

import com.demo.hibernate.model.City;

import java.util.Set;

//Service Classes should be defined with an interface that has basic methods which implementations will inherit
public interface CityService {
    void addCity(City city);

    City getCityById(int id);

    Set<City> getAllCities();

    void updateCity(City city);

    void deleteCity(City city);
}
