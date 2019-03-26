package homework.Storage.initor.datasourcereader.sax;

import homework.Country.domain.BaseCountry.Country;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;

public class CountriesAndCitiesSaxParser implements XmlParser<List<Country>> {

    @Override
    public List<Country> parse(String file) throws Exception {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();

        CountriesAndCitiesSaxHandler handler = new CountriesAndCitiesSaxHandler();
        saxParser.parse(new File(file), handler);

        return handler.getPersons();
    }
}
}
