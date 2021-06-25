package com.demo.hibernate.model;

import javax.persistence.*;
import java.util.List;

    @Entity
    public class Country {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;
        private String name;
        @ManyToOne
        private Continent continent;
        @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "country")
        private List<City> cities;

        public Country() {
        }

        public Country(String name, Continent continent) {
            this.name = name;
            this.continent = continent;
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

        public Continent getContinent() {
            return continent;
        }

        public void setContinent(Continent continent) {
            this.continent = continent;
        }

        @Override
        public String toString() {
            return "Country{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", continent=" + continent +
                    '}';
        }
    }

