package com.demo.hibernate;

import com.demo.hibernate.model.Continent;
import com.demo.hibernate.model.Country;
import com.demo.hibernate.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static com.demo.hibernate.MenuConstants.*;

public class TestClass {


    private final ContinentService continentService;
    private final CountryService countryService;
    private final CityService cityService;
    private final Scanner scanner;

    public TestClass() {
        this.continentService = new ContinentServiceImpl();
        this.countryService = new CountryServiceImpl();
        this.cityService = new CityServiceImpl();
        this.scanner = new Scanner(System.in);

    }

    public static void main(String[] args) {
        new TestClass().run();
    }

    void run() {
        try {
            mainMenuLoop();
        } finally {
            scanner.close();
        }
    }

    void mainMenuLoop() {
        while (true) {
            System.out.println(MAIN_MENU);
            int numberInput = getNumberInput();

            switch (numberInput) {
                case 1:
                case 2:
                    subMenu(numberInput);
                    break;
                case 0:
                    System.out.println(PROGRAM_END);
                    System.exit(0);
                    return;
                default:
                    System.err.println(INVALID_INPUT);
                    break;
            }
        }
    }

    private void subMenu(int choice) {
        System.out.println(SUB_MENU);
        int numberInput = getNumberInput();

        if (choice == 1)
            countryMenu(numberInput);
        else
            continentMenu(numberInput);
    }

    private void countryMenu(int choice) {
        switch (choice) {
            case 1:
                countryService.getAllCountries().forEach(System.out::println);
                break;
            case 2:
                seeCountry();
                break;
            case 3:
                addCountry();
                break;
            case 4:
                editCountry();
                break;
            case 5:
                deleteCountry();
                break;
            case 0:
                mainMenuLoop();
                break;
            default:
                System.err.println(INVALID_INPUT);
                break;
        }
        subMenu(1);
    }

    private void seeCountry() {
        int input = getNumberInput();
        countryService.getCountryById(input);
    }

    private void addCountry() {
        Country c = new Country();
        System.out.println(CHOOSE_CONTINENT_FOR_COUNTRY);
        Set<Continent> continents = continentService.getAllContinents();
        if (continents.isEmpty()) {
            System.err.println(NO_CONTINENT_AVAILABLE);
        } else {
            int idInput = validateIdInput(printContinentsGetIds(continents));
            c.setContinent(continentService.getContinentById(idInput));
            System.out.println(PROVIDE_COUNTRY_NAME);
            c.setName(getInput());
            countryService.addCountry(c);
        }
    }


    private void editCountry() {
        System.out.println(CHOOSE_COUNTRY);
        var countries = countryService.getAllCountries();
        if (countries.isEmpty()) {
            System.err.println(NO_COUNTRY_AVAILABLE);
            subMenu(1);
        }

        int idInput = validateIdInput(printCountriesGetIds(countries));
        var c = countryService.getCountryById(idInput);
        c.setName(getInput());
        countryService.updateCountry(c);

    }

    private void deleteCountry() {
        System.out.println(CHOOSE_COUNTRY);
        var countries = countryService.getAllCountries();
        if (countries.isEmpty()) {
            System.err.println(NO_COUNTRY_AVAILABLE);
            subMenu(1);
        }

        int idInput = validateIdInput(printCountriesGetIds(countries));
        countryService.deleteCountry(countryService.getCountryById(idInput));
    }

    private void continentMenu(int choice) {
        switch (choice) {
            case 1:
                continentService.getAllContinents().forEach(System.out::println);
                break;
            case 2:
                seeContinent();
                break;
            case 3:
                addContinent();
                break;
            case 4:
                editContinent();
                break;
            case 5:
                deleteContinent();
                break;
            case 0:
                mainMenuLoop();
                break;
            default:
                System.err.println(INVALID_INPUT);
                break;
        }
        subMenu(2);
    }

    private void seeContinent() {
        int input = getNumberInput();
        countryService.getCountryById(input);
    }

    private void addContinent() {
        Continent c = new Continent();
        System.out.println(PROVIDE_COUNTRY_NAME);
        c.setName(getInput());
        continentService.addContinent(c);
    }

    private void editContinent() {
        System.out.println(CHOOSE_CONTINENT);
        var continents = continentService.getAllContinents();
        if (continents.isEmpty()) {
            System.err.println(NO_COUNTRY_AVAILABLE);
            subMenu(2);
        }
        int idInput = validateIdInput(printContinentsGetIds(continents));

        var c = continentService.getContinentById(idInput);
        c.setName(getInput());
        continentService.updateContinent(c);
    }

    private void deleteContinent() {
        System.out.println(CHOOSE_CONTINENT);
        var continents = continentService.getAllContinents();
        if (continents.isEmpty()) {
            System.err.println(NO_CONTINENT_AVAILABLE);
            subMenu(2);
        }
        int idInput = validateIdInput(printContinentsGetIds(continents));
        var c = continentService.getContinentById(idInput);
        continentService.deleteContinent(c);
    }

    int validateIdInput(List<Integer> idlist) {
        int input;
        while (true) {
            input = getNumberInput();
            if (idlist.contains(input)) {
                return input;
            }
            System.err.println(INVALID_ID_INPUT);
        }
    }

    private List<Integer> printContinentsGetIds(Set<Continent> continents) {
        var ids = new ArrayList<Integer>();
        continents.forEach(c -> {
            System.out.println(c);
            ids.add(c.getId());
        });
        return ids;
    }

    private List<Integer> printCountriesGetIds(Set<Country> countries) {
        var ids = new ArrayList<Integer>();
        countries.forEach(c -> {
            System.out.println(c);
            ids.add(c.getId());
        });
        return ids;
    }

    private int getNumberInput() {
        try {
            return Integer.parseInt(getInput());
        } catch (NumberFormatException nfe) {
            System.err.println(NAN_INVALID);
            return getNumberInput();
        }
    }

    String getInput() {
        try {
            System.out.println(PROVIDE_INPUT);
            String str = scanner.nextLine();
            return str;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println(INVALID_INPUT);
            return getInput();
        }
    }
}
