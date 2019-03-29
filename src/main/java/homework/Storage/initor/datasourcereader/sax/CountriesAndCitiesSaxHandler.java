package homework.Storage.initor.datasourcereader.sax;

import homework.City.domain.City;
import homework.Country.domain.BaseCountry.Country;
import homework.Country.domain.ColdCountry;
import homework.Country.domain.CountryTemperature;
import homework.Country.domain.HotCountry;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

public class CountriesAndCitiesSaxHandler extends DefaultHandler {

    private List<Country> countryList;
    private Country country;
    private List<City> cityList;
    private City city;
    private City capital;
    private String element;
    private String content;



    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        element = qName;
        switch (element) {
            case "countries": {
                countryList = new ArrayList<>();
                break;
            }
            case "country": {
                if ("LOW_TEMPERATURE".equals(attributes.getValue(0))) {
                    ColdCountry coldCountry = new ColdCountry();
                    coldCountry.setDiscriminator(CountryTemperature.LOW_TEMPERATURE);
                    country = coldCountry;
                } else if ("HIGH_TEMPERATURE".equals(attributes.getValue(0))) {
                    HotCountry hotCountry = new HotCountry();
                    hotCountry.setDiscriminator(CountryTemperature.HIGH_TEMPERATURE);
                    country = hotCountry;
                }
                break;
            }
            case "cities" : {
                cityList = new ArrayList<>();
                break;
            }
            case "city" : {
                city = new City();
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "countryName" : {
                country.setCountryName(content);
                break;
            }
            case "telephoneCode" : {
                ColdCountry coldCountry = (ColdCountry) country;
                coldCountry.setTelephoneCode(content);
                country = coldCountry;
                break;
            }
            case "polarNight" : {
                ColdCountry coldCountry = (ColdCountry) country;
                if ("NO".equals(content)) {
                    coldCountry.setPolarNight(false);
                }
                else if ("YES".equals(content)) {
                    coldCountry.setPolarNight(true);
                }
                country = coldCountry;
                break;
            }
            case "language" : {
                country.setLanguage(content);
                break;
            }
        /*    case "capital" : {
                capital = new City();
                capital.setName(content);
                country.setCapital(capital);
                break;
            }*/

            case "name" : {
                city.setName(content);
                break;
            }
            case "population" : {
                city.setPopulation(Integer.parseInt(content));
                break;
            }
            case "isCapital" : {
                if ("Yes".equals(content)) {
                    city.setCapital(true);
                    country.setCapital(city);
                    break;
                }
                else if ("No".equals(content)) {
                    city.setCapital(false);
                    break;
                }
            }
            case "city" : {
                cityList.add(city);
                break;
            }
            case "cities" : {
                country.setCities(cityList.toArray(new City[countryList.size()]));
                break;
            }
            case "hottestMonth" : {
                HotCountry hotCountry = (HotCountry) country;
                hotCountry.setHottestMonth(content);
                country = hotCountry;
                break;
            }
            case "averageTemperature" : {
                HotCountry hotCountry = (HotCountry) country;
                hotCountry.setAverageTemperature(content);
                country = hotCountry;
                break;
            }
            case "country" : {
                countryList.add(country);
            }

            }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        content = new String(ch, start, length);

    }

    public List<Country> getCountriesList() {
        return countryList;
    }

/*
    private String stackAsStringPath() {
        StringBuilder fullPath = new StringBuilder();

        Iterator<String> iter = tagStack.iterator();
        while (iter.hasNext()) {
            String tag = iter.next();
            fullPath.append(tag);

            if (iter.hasNext()) {
                fullPath.append("/");
            }
        }

        return fullPath.toString();
    }*/

}
