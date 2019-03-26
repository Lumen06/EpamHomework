package homework.Storage.initor.datasourcereader;

import homework.Storage.initor.exceptions.NotExistingCountryException;
import homework.City.domain.City;
import homework.Country.domain.BaseCountry.Country;
import homework.Country.domain.ColdCountry;
import homework.Country.domain.HotCountry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

import static homework.Country.domain.CountryTemperature.HIGH_TEMPERATURE;
import static homework.Country.domain.CountryTemperature.LOW_TEMPERATURE;

public class CountriesAndCitiesTxtParser implements FileParser<List<Country>> {


    @Override
    public List<Country> parseFile(String file) throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> fileData = new ArrayList<>();

        String str = null;

        while ((str = reader.readLine()) != null) {
            fileData.add(str);
        }

        List<Country> listOfCountries = new ArrayList<>();
        HashMap<Country, List<City>> mapOfCountriesAndCities = new HashMap<>();
        List<City> listOfCities = new ArrayList<>();

        for (String fileDatum : fileData) {

            String[] instanceElements;

            if (fileDatum.contains("Country:")) {

                if (!listOfCities.isEmpty()) {
                    mapOfCountriesAndCities.put(listOfCountries.remove(0), listOfCities);
                    listOfCities = new ArrayList<>();
                }
                instanceElements = fileDatum.substring(8).trim().split("\\|");

                if ("LOW_TEMPERATURE".equals(instanceElements[1].trim())) {
                    ColdCountry coldCountry = new ColdCountry();
                    coldCountry.setCountryName(instanceElements[0].trim());
                    coldCountry.setDiscriminator(LOW_TEMPERATURE);
                    coldCountry.setLanguage(instanceElements[4].trim().toLowerCase());
                    if ("YES".equals(instanceElements[3].trim())) {
                        coldCountry.setPolarNight(true);
                    } else if ("NO".equals(instanceElements[3].trim())) {
                        coldCountry.setPolarNight(false);
                    } else throw new IllegalArgumentException();
                    coldCountry.setTelephoneCode(instanceElements[2].trim());

                    listOfCountries.add(coldCountry);
                } else if ("HIGH_TEMPERATURE".equals(instanceElements[1].trim())) {
                    HotCountry hotCountry = new HotCountry();
                    hotCountry.setCountryName(instanceElements[0].trim());
                    hotCountry.setDiscriminator(HIGH_TEMPERATURE);
                    hotCountry.setLanguage(instanceElements[5].trim().toLowerCase());
                    hotCountry.setAverageTemperature(instanceElements[3]);

                    listOfCountries.add(hotCountry);

                } else throw new NotExistingCountryException();

            } else {
                instanceElements = fileDatum.trim().split("\\|");
                City city = new City();
                city.setName(instanceElements[0]);
                city.setPopulation(Integer.parseInt(instanceElements[1].trim()));
                if ("YES".equals(instanceElements[2].trim())) {
                    city.setCapital(true);
                } else if ("NO".equals(instanceElements[2].trim())) {
                    city.setCapital(false);
                } else throw new IllegalArgumentException();

                listOfCities.add(city);

            }


        }

        if (!listOfCities.isEmpty()) {
            mapOfCountriesAndCities.put(listOfCountries.remove(0), listOfCities);
        }

        for (Map.Entry<Country, List<City>> map : mapOfCountriesAndCities.entrySet()) {
            Country c = map.getKey();
            City[] cities = new City[map.getValue().size()];
            cities = map.getValue().toArray(cities);
            c.setCities(cities);
            for (City city : cities) {
                if (city.isCapital()) {
                    c.setCapital(city);
                }
            }

            System.out.println("Country: " + map.getKey().toString());
            System.out.println("Cities: " + map.getValue().toString());
            System.out.println("--------------------------------");
        }

        return listOfCountries;
    }

    public static void main(String[] args) throws IOException, Exception {

        Path file = new File("C:\\Users\\Royal\\IdeaProjects\\homework2\\src\\AgencyDemo\\dataInit.txt").toPath();
        CountriesAndCitiesTxtParser txtParser = new CountriesAndCitiesTxtParser();
        txtParser.parseFile(file.toString());


    }
}
