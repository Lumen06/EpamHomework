package homework.Storage.initor;

import homework.Country.domain.BaseCountry.Country;
import homework.Storage.initor.datasourcereader.CountriesAndCitiesTxtParser;
import homework.Storage.initor.datasourcereader.sax.CountriesAndCitiesSaxParser;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CountryCitiesFileParser implements Runnable{

  private StorageInitializer.DataSourceType dataSourceType;
  private Thread thread;
  private File file;
  private List<Country> countryList;
  private volatile Exception parseEsxception;

    public CountryCitiesFileParser(StorageInitializer.DataSourceType dataSourceType, File file) {

        this.dataSourceType = dataSourceType;
        thread = new Thread();
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
            }
        }
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public Exception getParseEsxception() {
        return parseEsxception;
    }

    public void asyncParseMarks() {
        thread.start();
    }

    public void blockUntilJobIsFinished() throws InterruptedException {
        thread.join();
    }
}
