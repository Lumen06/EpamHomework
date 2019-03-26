package homework.Country.domain.BaseCountry;

import homework.City.domain.City;
import homework.Common.Business.domain.BaseDomain;
import homework.Country.domain.CountryTemperature;

public abstract class Country extends BaseDomain {

    private String countryName;
    private String language;
    private City[] cities;
    private City capital;
    private CountryTemperature discriminator;


    public City getCapital() {
        return capital;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public Long getId() {
        return id;
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

    public void setDiscriminator(CountryTemperature discriminator) {
        this.discriminator = discriminator;
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
