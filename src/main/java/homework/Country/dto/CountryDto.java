package homework.Country.dto;

import homework.City.domain.City;
import homework.Country.domain.CountryTemperature;

public abstract class CountryDto {

    private String countryName;
    private String language;
    private City[] cities;
    private City capital;

    public City getCapital() {
        return capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getLanguage() {
        return language;
    }

    public City[] getCities() {
        return cities;
    }

    public void setCities(City[] cities) {
        this.cities = cities;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        String citiesList = " ";
        for (City city : cities) {
            citiesList += city.getName() + " ";
        }

        return "Страна: " + countryName + " Язык: " + language + " Cписок городов: " + citiesList;
    }
}
