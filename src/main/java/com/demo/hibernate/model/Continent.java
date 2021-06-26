package com.demo.hibernate.model;

import com.demo.hibernate.tools.IdEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Continent implements IdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany(mappedBy = "continent",cascade = CascadeType.REMOVE)
    private List<Country> countryList;

    public Continent() {
    }


    public Continent(String continentName) {
        this.name = continentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Continent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
