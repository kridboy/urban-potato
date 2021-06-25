package com.demo.hibernate.service;

import com.demo.hibernate.model.Country;

import java.util.Set;

public interface CountryService {
    void addCountry(Country country);

    Country getCountryById(int id);

    Set<Country> getAllCountries();

    void updateCountry(Country country);

    void deleteCountry(Country country);
}

