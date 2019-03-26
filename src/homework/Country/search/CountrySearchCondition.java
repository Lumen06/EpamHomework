package homework.Country.search;

import homework.City.domain.City;
import homework.Common.Business.Search.BaseSearchCondition;

public class CountrySearchCondition extends BaseSearchCondition<Long> {

    private String countryName;
    private String language;
    private City capital;
    private CountryOrderByField countryOrderByField;


    public CountryOrderByField getCountryOrderByField() {
        return countryOrderByField;
    }

    public void setCountryOrderByField(CountryOrderByField countryOrderByField) {
        this.countryOrderByField = countryOrderByField;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    public City getCapital() {
        return capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getLanguage() {
        return language;
    }


    public boolean needSearchbyCountry() {
        return true;
    }
}
