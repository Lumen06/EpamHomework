package homework.Storage.initor.datasourcereader;

import homework.City.domain.City;
import homework.Country.domain.BaseCountry.Country;
import homework.Country.domain.ColdCountry;
import homework.Country.domain.HotCountry;
import homework.Storage.initor.exceptions.NotExistingCountryException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static homework.Country.domain.CountryTemperature.HIGH_TEMPERATURE;
import static homework.Country.domain.CountryTemperature.LOW_TEMPERATURE;

public class CountriesAndCitiesDOMXMLParser implements FileParser<List<Country>> {


    @Override
    public List<Country> parseFile(String file) throws Exception {

        if (!new File(file).exists() || new File(file).isDirectory()) {
            throw new Exception("No such file");
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document doc = documentBuilder.parse(new File(file));

        Node root = doc.getElementsByTagName("countries").item(0);

        NodeList countriesNodeList = ((Element) root).getElementsByTagName("country");

        List<Country> countryList = new ArrayList<>();

        if (countriesNodeList.getLength() > 0) {

            for (int i = 0; i < countriesNodeList.getLength(); i++) {

                City capital = new City();
                Node countryElement = countriesNodeList.item(i);

                String discriminator = ((Element) countryElement).getAttribute("discriminator");
                NodeList countryInnerTags = countryElement.getChildNodes();

                if ("LOW_TEMPERATURE".equals(discriminator)) {

                    ColdCountry coldCountry = new ColdCountry();

                    countryList.add(coldCountry);
                    coldCountry.setDiscriminator(LOW_TEMPERATURE);

                    for (int k = 0; k < countryInnerTags.getLength(); k++) {
                        Node countryInner = countryInnerTags.item(k);

                        switch (countryInner.getNodeName()) {
                            case "countryName": {
                                coldCountry.setCountryName(countryInner.getTextContent());
                                break;
                            }
                            case "telephoneCode": {
                                coldCountry.setTelephoneCode(countryInner.getTextContent());
                                break;
                            }
                            case "language": {
                                coldCountry.setLanguage(countryInner.getTextContent());
                                break;
                            }
                            case "polarNight": {
                                if ("NO".equals(countryInner.getTextContent())) {
                                    coldCountry.setPolarNight(false);
                                } else if ("YES".equals(countryInner.getTextContent())) {
                                    coldCountry.setPolarNight(true);
                                } else throw new Exception();
                                break;
                            }
                            case "capital": {
                                coldCountry.setCapital(capital);
                                break;
                            }
                            case "cities": {
                                NodeList xmlCities = ((Element) countryInner).getElementsByTagName("city");
                                List<City> citiesList = new ArrayList<>();

                                for (int j = 0; j < xmlCities.getLength(); j++) {
                                    Element childElement = (Element) xmlCities.item(j);

                                    City city = new City();
                                    citiesList.add(city);

                                    city.setName(childElement.getElementsByTagName("name").item(0).getTextContent());
                                    city.setPopulation(Long.parseLong(childElement.getElementsByTagName("population").item(0).getTextContent()));

                                    if ("No".equals(childElement.getElementsByTagName("isCapital").item(0).getTextContent())) {
                                        city.setCapital(false);
                                    } else if ("Yes".equals(childElement.getElementsByTagName("isCapital").item(0).getTextContent())) {
                                        city.setCapital(true);
                                    } else throw new Exception();

                                    if (city.isCapital()) {
                                        coldCountry.setCapital(city);
                                    }
                                }

                                coldCountry.setCities(citiesList.toArray(new City[citiesList.size()]));
                            }
                        }
                    }


                } else if ("HIGH_TEMPERATURE".equals(discriminator)) {

                    HotCountry hotCountry = new HotCountry();
                    countryList.add(hotCountry);
                    hotCountry.setDiscriminator(HIGH_TEMPERATURE);

                    for (int k = 0; k < countryInnerTags.getLength(); k++) {
                        Node countryInner = countryInnerTags.item(k);
                        switch (countryInner.getNodeName()) {
                            case "countryName": {
                                hotCountry.setCountryName(countryInner.getTextContent());
                                break;
                            }
                            case "language": {
                                hotCountry.setLanguage(countryInner.getTextContent());
                                break;
                            }
                            case "averageTemperature": {
                                hotCountry.setAverageTemperature(countryInner.getTextContent());
                                break;
                            }
                            case "capital": {
                                hotCountry.setCapital(capital);
                                break;
                            }
                            case "cities": {
                                NodeList xmlCities = ((Element) countryInner).getElementsByTagName("city");
                                List<City> citiesList = new ArrayList<>();

                                for (int j = 0; j < xmlCities.getLength(); j++) {
                                    Element childElement = (Element) xmlCities.item(j);

                                    City city = new City();
                                    citiesList.add(city);

                                    city.setName(childElement.getElementsByTagName("name").item(0).getTextContent());
                                    city.setPopulation(Long.parseLong(childElement.getElementsByTagName("population").item(0).getTextContent()));

                                    if ("No".equals(childElement.getElementsByTagName("isCapital").item(0).getTextContent())) {
                                        city.setCapital(false);
                                    } else if ("Yes".equals(childElement.getElementsByTagName("isCapital").item(0).getTextContent())) {
                                        city.setCapital(true);
                                    } else throw new Exception();

                                    if (city.isCapital()) {
                                        hotCountry.setCapital(city);
                                    }
                                }
                                hotCountry.setCities(citiesList.toArray(new City[citiesList.size()]));
                            }
                        }
                    }
                } else throw new NotExistingCountryException();
            }
        }
        return countryList;
    }

    public static void main(String[] args) {
        try {
            CountriesAndCitiesDOMXMLParser countriesAndCitiesDOMXMLParser = new CountriesAndCitiesDOMXMLParser();
            List<Country> list = countriesAndCitiesDOMXMLParser.parseFile("C:\\Users\\Royal\\IdeaProjects\\homework2\\src\\AgencyDemo\\dataInit.xml");
            for (Country country : list) {
                System.out.println(country.toString());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
