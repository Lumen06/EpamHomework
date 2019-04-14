package homework.Storage.initor;

import homework.Country.domain.BaseCountry.Country;
import homework.Country.service.CounryService.CountryService;
import homework.Storage.Storage;
import homework.Storage.initor.datasourcereader.CountriesAndCitiesTxtParser;
import homework.Storage.initor.exceptions.CountryCitylParseXmlFileException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static homework.Storage.initor.StorageInitializer.DataSourceType.TXT_FILE;
import static homework.Storage.initor.StorageInitializer.DataSourceType.XML_FILE;

public class StorageInitializer {

    CountryService countryService;

    public enum DataSourceType {
        TXT_FILE, XML_FILE
    }

    public void initStorageWithCountriesAndCities(List<File> files, DataSourceType dataSourceType) throws Exception{

        List<CountryCitiesFileParser> listOfParsers = initListOfParsers(files, dataSourceType);
        List<Country> countriesToPersist = new ArrayList<>();
        for (CountryCitiesFileParser parser : listOfParsers) {

            parser.parseTheCountries();

        }

        for (CountryCitiesFileParser parser: listOfParsers) {

            parser.blockUntilJobIsFinished();
            countriesToPersist.addAll(parser.getCountryList());

            if (parser.getParseException()!= null) {
                throw new CountryCitylParseXmlFileException();
            }
        }

        countryService.insert(countriesToPersist);



    }

    private List<CountryCitiesFileParser> initListOfParsers(List<File> files,  DataSourceType dataSourceType) {
        List<CountryCitiesFileParser> listOfParsers = new ArrayList<>();

        for (File file: files) {
            listOfParsers.add(new CountryCitiesFileParser(dataSourceType, file));
        }

        return listOfParsers;

    }

    public static void main(String[] args) throws Exception{

/*        File file = new File("C:\\Users\\Royal\\IdeaProjects\\TravelAgency\\resources\\dataInit.xml");
        File file1 = new File("C:\\Users\\Royal\\IdeaProjects\\TravelAgency\\resources\\dataInit.xml");
        File file2 = new File("C:\\Users\\Royal\\IdeaProjects\\TravelAgency\\resources\\dataInit.xml");
        File file3 = new File("C:\\Users\\Royal\\IdeaProjects\\TravelAgency\\resources\\dataInit.xml");*/

        File file = new File("C:\\Users\\Royal\\IdeaProjects\\TravelAgency\\resources\\dataInit.txt");
        File file1 = new File("C:\\Users\\Royal\\IdeaProjects\\TravelAgency\\resources\\dataInit.txt");
        File file2 = new File("C:\\Users\\Royal\\IdeaProjects\\TravelAgency\\resources\\dataInit.txt");
        File file3 = new File("C:\\Users\\Royal\\IdeaProjects\\TravelAgency\\resources\\dataInit.txt");


        StorageInitializer storageInitializer = new StorageInitializer();
        List<File> files = new ArrayList<>();
        files.add(file);
        files.add(file1);
        files.add(file2);
        files.add(file3);

        storageInitializer.initStorageWithCountriesAndCities(files, TXT_FILE);

        for (Country country : Storage.countriesList) {
            System.out.println(country);

        }
    }

}
