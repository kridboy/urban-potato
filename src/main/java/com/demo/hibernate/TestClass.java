//When working with Maven, and Java 9+ in general it is advised to use correct package naming.
//This gives classes a FQN (Fully Qualified Name) (can be anything but usually in the format of com.xxx.xxx.class)
//This this reflects package structure (in this case package com.demo.hibernate.[subpackages].Class)
package com.demo.hibernate;

import com.demo.hibernate.model.City;
import com.demo.hibernate.model.Continent;
import com.demo.hibernate.model.Country;
import com.demo.hibernate.service.*;
import com.demo.hibernate.tools.IdEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static com.demo.hibernate.tools.MenuConstants.*;

public class TestClass {
    private final ContinentService continentService;
    private final CountryService countryService;
    private final CityService cityService;
    //Use only 1 scanner for all input operations, make sure to close upon end of execution.
    private final Scanner scanner;

    public TestClass() {
        //Instantiate service classes in constructor, not in method bodies
        this.continentService = new ContinentServiceImpl();
        this.countryService = new CountryServiceImpl();
        this.cityService = new CityServiceImpl();
        this.scanner = new Scanner(System.in);

    }

    public static void main(String[] args) {
        new TestClass().run();
    }

    //Our main run method, where all business logic starts, and at end of runtime scanner gets closed.
    void run() {
        try {
            mainMenuLoop();
        } finally {
            scanner.close();
        }
    }


    void mainMenuLoop() {
        // We can use while(true) since our ending condition is a System#exit(0) call, effectively ending the loop.
        while (true) {
            System.out.println(MAIN_MENU);
            System.out.println(PROVIDE_INPUT);
            int numberInput = getNumberInput();

            switch (numberInput) {
                // Country, city, Continent can use same subMenu method, where further delegation is handled.
                case 1:
                case 2:
                case 3:
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
        // Since submenu operations are the same for all entities we can ask the question first (no code duplication)
        // using choice from previous input we can delegate calls to correct submenu along with next choice
        System.out.println(SUB_MENU);
        System.out.println(PROVIDE_INPUT);
        int numberInput = getNumberInput();

        if (choice == 1)
            countryMenu(numberInput);
        else if (choice == 2)
            continentMenu(numberInput);
        else
            cityMenu(numberInput);
    }


    // I'm pretty sure we can abstract these submenus into one (instead of one for each entity)
    // although it might require reflection which would become a little complicated.
    private void countryMenu(int choice) {
        switch (choice) {
            case 1:
                var countries = countryService.getAllCountries();
                if (countries.isEmpty())
                    System.err.println(NO_COUNTRY_AVAILABLE);
                else
                    countries.forEach(System.out::println);
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
        var country = countryService.getCountryById(input);
        // It's okay to not use brackets in single statement if-conditions, but be mindful when doing so.
        if (country == null)
            System.err.println(INVALID_ID_INPUT);
        else
            System.out.println(country);
    }

    private void addCountry() {
        Country c = new Country();
        System.out.println(CHOOSE_CONTINENT_FOR_COUNTRY);
        Set<Continent> continents = continentService.getAllContinents();
        if (continents.isEmpty()) {
            System.err.println(NO_CONTINENT_AVAILABLE);
        } else {
            // below is a nice example of how abstracting logic and nesting method calls can shorten code considerably!
            // we need an ID based on a list of entities and it needs to be validated to be sure it's actually in the list.
            int idInput = validateIdInput(printEntityAndListIds(continents));
            c.setContinent(continentService.getContinentById(idInput));
            System.out.println(PROVIDE_COUNTRY_NAME);
            c.setName(getInput());
            countryService.addCountry(c);
        }
    }


    private void editCountry() {
        int idInput = getCountryId();
        var c = countryService.getCountryById(idInput);
        System.out.println(PROVIDE_COUNTRY_NAME);
        // At the moment a bit of an unsafe call to #getInput(). I think a string like ""," " or "n" for example would be accepted
        // but #getInput can be refactored to check for these types of input. It will then be applied to everywhere this method is called.
        c.setName(getInput());
        countryService.updateCountry(c);

    }

    private void deleteCountry() {
        int idInput = getCountryId();
        countryService.deleteCountry(countryService.getCountryById(idInput));
    }

    // We know that we need to get a country ID in 2 methods, so instead of rewriting code we can extract it into a separate method
    // for easy re-use and to simplify changes if they need to happen.
    // (We use very similar for city and continent, so perhaps another layer of abstraction could be possible?)
    private int getCountryId() {
        System.out.println(CHOOSE_COUNTRY);
        var countries = countryService.getAllCountries();
        if (countries.isEmpty()) {
            System.err.println(NO_COUNTRY_AVAILABLE);
            subMenu(1);
        }

        // Returning nested method calls can shorten code considerably, but be careful not lose track of code-flow
        return validateIdInput(printEntityAndListIds(countries));
    }

    private void continentMenu(int choice) {
        switch (choice) {
            case 1:
                var continents = continentService.getAllContinents();
                if (continents.isEmpty())
                    System.err.println(NO_CONTINENT_AVAILABLE);
                else
                    continents.forEach(System.out::println);
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
        var continent = continentService.getContinentById(input);
        if (continent == null)
            System.err.println(INVALID_ID_INPUT);
        else
            System.out.println(continent);
    }

    private void addContinent() {
        Continent c = new Continent();
        System.out.println(PROVIDE_CONTINENT_NAME);
        c.setName(getInput());
        continentService.addContinent(c);
    }

    private void editContinent() {
        int idInput = getContinentId();
        var c = continentService.getContinentById(idInput);
        System.out.println(PROVIDE_CONTINENT_NAME);
        c.setName(getInput());
        continentService.updateContinent(c);
    }

    private void deleteContinent() {
        int idInput = getContinentId();
        var c = continentService.getContinentById(idInput);
        continentService.deleteContinent(c);
    }

    private int getContinentId() {
        System.out.println(CHOOSE_CONTINENT);
        var continents = continentService.getAllContinents();
        if (continents.isEmpty()) {
            System.err.println(NO_COUNTRY_AVAILABLE);
            subMenu(2);
        }
        return validateIdInput(printEntityAndListIds(continents));
    }

    private void cityMenu(int choice) {
        switch (choice) {
            case 1:
                var cities = cityService.getAllCities();
                if (cities.isEmpty())
                    System.err.println(NO_CITY_AVAILABLE);
                else
                    cities.forEach(System.out::println);
                break;
            case 2:
                seeCity();
                break;
            case 3:
                addCity();
                break;
            case 4:
                editCity();
                break;
            case 5:
                deleteCity();
                break;
            case 0:
                mainMenuLoop();
                break;
            default:
                System.err.println(INVALID_INPUT);
                break;
        }
        subMenu(3);
    }


    private void seeCity() {
        int input = getNumberInput();
        var city = cityService.getCityById(input);
        if (city == null)
            System.err.println(INVALID_ID_INPUT);
        else
            System.out.println(city);
    }


    private void addCity() {
        var c = new City();
        System.out.println(CHOOSE_COUNTRY_FOR_CITY);
        Set<Country> countries = countryService.getAllCountries();
        if (countries.isEmpty()) {
            System.err.println(NO_COUNTRY_AVAILABLE);
        } else {
            int idInput = validateIdInput(printEntityAndListIds(countries));
            c.setCountry(countryService.getCountryById(idInput));
            System.out.println(PROVIDE_CITY_NAME);
            c.setName(getInput());
            cityService.addCity(c);
        }
    }

    private void editCity() {
        int idInput = getCityId();
        var c = cityService.getCityById(idInput);
        System.out.println(PROVIDE_CITY_NAME);
        c.setName(getInput());
        cityService.updateCity(c);
    }

    private void deleteCity() {
        int idInput = getCityId();
        var c = cityService.getCityById(idInput);
        cityService.deleteCity(c);
    }


    private int getCityId() {
        System.out.println(CHOOSE_CITY);
        var cities = cityService.getAllCities();
        if (cities.isEmpty()) {
            System.err.println(NO_CITY_AVAILABLE);
            subMenu(3);
        }
        return validateIdInput(printEntityAndListIds(cities));
    }

    // We need to validate Id's for several entities. By providing a List of valid Id's
    // we can abstract validation away from specific entitiies
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

    // Knowing that each entity implements a toString and getId method we can use an interface and wildcard
    // this abstracts the code in a way that we don't have to repeat same code for multiple entities just because
    // they are not the exact same object.
    private List<Integer> printEntityAndListIds(Set<? extends IdEntity> entities) {
        var ids = new ArrayList<Integer>();
        entities.forEach(c -> {
            System.out.println(c);
            ids.add(c.getId());
        });
        return ids;
    }

    //We can use a simply Try-catch to assert number use, and recurse this method as long as needed until a valid input is given.
    private int getNumberInput() {
        try {
            return Integer.parseInt(getInput());
        } catch (NumberFormatException nfe) {
            System.err.println(NAN_INVALID);
            return getNumberInput();
        }
    }

    // Instead of making scanners for every user input we can just delegate all user input to a single method
    //  this promotes code re-use and makes it easier to perform simple changes to logic
    String getInput() {
        try {
            //A REGEX and/or length check could be added to ensure correct input, and if input not correct recurse
            return scanner.nextLine();

        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println(INVALID_INPUT);
            return getInput();
        }
    }
}
