package com.demo.hibernate.service;

import com.demo.hibernate.model.Continent;

import java.util.Set;

public interface ContinentService {
    void addContinent(Continent continent);

    Continent getContinentById(int id);

    Set<Continent> getAllContinents();

    void updateContinent(Continent continent);

    void deleteContinent(Continent continent);
}
