package com.demo.hibernate.service;

import com.demo.hibernate.dao.CountryDAO;
import com.demo.hibernate.dao.CountryDAOImpl;
import com.demo.hibernate.model.Country;

import java.util.Set;

public class CountryServiceImpl implements CountryService {
    CountryDAO countryDAO;

    public CountryServiceImpl() {
        countryDAO = CountryDAOImpl.getInstance();
    }

    @Override
    public void addCountry(Country country) {
        countryDAO.addCountry(country);
    }

    @Override
    public Country getCountryById(int id) {
        return countryDAO.getCountryById(id);
    }

    @Override
    public Set<Country> getAllCountries() {
        return countryDAO.getAllCountries();
    }

    @Override
    public void updateCountry(Country country) {
        countryDAO.updateCountry(country);
    }

    @Override
    public void deleteCountry(Country country) {
        countryDAO.deleteCountry(country);
    }
}
