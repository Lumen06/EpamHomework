package homework.Storage.initor;

import homework.Country.domain.BaseCountry.Country;
import homework.Storage.initor.datasourcereader.CountriesAndCitiesTxtParser;
import homework.Storage.initor.datasourcereader.sax.CountriesAndCitiesSaxParser;

import java.io.File;
import java.util.List;

public class CountryCitiesFileParser implements Runnable{

  private StorageInitializer.DataSourceType dataSourceType;
  private Thread thread;
  private File file;
  private List<Country> countryList;
  private volatile Exception parseException;

    public CountryCitiesFileParser(StorageInitializer.DataSourceType dataSourceType, File file) {

        this.dataSourceType = dataSourceType;
        thread = new Thread(this);
        this.file = file;
    }

    @Override
    public void run() {
        if (dataSourceType != null) {
            try {
                switch (dataSourceType) {
                    case TXT_FILE: {
                        CountriesAndCitiesTxtParser countriesAndCitiesTxtParser = new CountriesAndCitiesTxtParser();
                        countryList = countriesAndCitiesTxtParser.parseFile(file.toString());
                        break;
                    }
                    case XML_FILE: {
                        CountriesAndCitiesSaxParser countriesAndCitiesSaxParser = new CountriesAndCitiesSaxParser();
                        countryList = countriesAndCitiesSaxParser.parse(file.toString());
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                parseException = e;
            }
        }
    }

    public void parseTheCountries() {
        thread.start();
    }


    public List<Country> getCountryList() {
        return countryList;
    }

    public Exception getParseException() {
        return parseException;
    }


    public void blockUntilJobIsFinished() throws InterruptedException {
        thread.join();
    }
}
