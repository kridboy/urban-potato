package com.demo.hibernate.tools;

public final class MenuConstants {
    private MenuConstants(){}

    // Static references for all menu operations. promotes re-usability and cleanliness in main business logic
    // it also makes it easier to perform changes without having to apply them several times
    public static final String INVALID_INPUT = "input was invalid, please retry..";
    public static final String NAN_INVALID = "Input was not a number! try again.";
    public static final String MAIN_MENU = "What do you want to look at? \n1: Countries \n2: Continents\n3: Cities\n0: End";
    public static final String SUB_MENU = "What do you want to look at? \n1: See All \n2: See One \n3: Add One \n4: Edit One \n5: Delete One\n0: Return";
    public static final String PROVIDE_INPUT = "Provide input:";
    public static final String PROGRAM_END = "Program end";
    public static final String NO_CONTINENT_AVAILABLE = "No Continent available, please add a continent first!";
    public static final String NO_COUNTRY_AVAILABLE = "No Country available..";
    public static final String NO_CITY_AVAILABLE = "No City available..";
    public static final String CHOOSE_CONTINENT_FOR_COUNTRY = "Choose Continent to add Country to:";
    public static final String CHOOSE_COUNTRY_FOR_CITY = "Choose Country to add City to:";
    public static final String PROVIDE_COUNTRY_NAME = "Provide Country name:";
    public static final String PROVIDE_CONTINENT_NAME = "Provide Continent name:";
    public static final String PROVIDE_CITY_NAME = "Provide City name:";
    public static final String CHOOSE_COUNTRY = "Choose country";
    public static final String CHOOSE_CONTINENT = "Choose continent";
    public static final String CHOOSE_CITY = "Choose city";
    public static final String INVALID_ID_INPUT = "Invalid ID input.";
}
