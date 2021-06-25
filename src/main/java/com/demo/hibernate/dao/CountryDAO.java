package com.demo.hibernate.dao;

import com.demo.hibernate.model.Country;

import java.util.Set;

public interface CountryDAO {

    void addCountry(Country country);

    Country getCountryById(int id);

    Set<Country> getAllCountries();

    void updateCountry(Country country);

    void deleteCountry(Country country);
}
