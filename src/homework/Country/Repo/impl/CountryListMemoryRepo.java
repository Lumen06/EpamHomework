package homework.Country.Repo.impl;

import homework.Common.Solutions.Utils.Utils.StringUtils;
import homework.Country.Repo.CountryRepo.CountryRepo;
import homework.Country.domain.BaseCountry.Country;
import homework.Country.search.CountrySearchCondition;
import homework.Storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static homework.Storage.Storage.countries;
import static homework.Storage.Storage.countriesList;

public class CountryListMemoryRepo implements CountryRepo {

    private CountrySortingComponent countrySortingComponent = new CountrySortingComponent();

    @Override
    public void add(Country country) {

        country.setId(SequenceGenerator.nextValue());
        countriesList.add(country);
    }

    @Override
    public Country findById(Long id) {
        return findCountryById(id);

    }

    @Override
    public List<Country> search(CountrySearchCondition countrySearchCondition) {
        if (countrySearchCondition.getId() != null) {
            return Collections.singletonList(findById(countrySearchCondition.getId()));

        } else {

            boolean searchByName = StringUtils.isNotBlank(countrySearchCondition.getCountryName());

            boolean searchByPopulation = StringUtils.isNotBlank(countrySearchCondition.getLanguage() + "");

            boolean searchByCapital = StringUtils.isNotBlank(countrySearchCondition.getCapital() + "");


            List<Country> foundedCountries = new ArrayList<>();
            for (Country country : countries) {

                boolean found = true;

                if (searchByName) {
                    found = countrySearchCondition.getCountryName().equals(country.getCountryName());
                }
                if (found && searchByPopulation) {
                    found = countrySearchCondition.getLanguage().equals(country.getLanguage());
                }
                if (found && searchByCapital) {
                    found = countrySearchCondition.getCapital() == country.getCapital();
                }
                if (found) {
                    foundedCountries.add(country);
                }
            }

            if (foundedCountries.size() > 0 && countrySearchCondition.needSorting()) {
                countrySortingComponent.sorting(foundedCountries, countrySearchCondition);
            }
            return foundedCountries;
        }
    }

    @Override
    public void update(Country country) {

    }

    @Override
    public void deleteById(Long id) {
        Country country = findCountryById(id);
        if (country != null) {
            countriesList.remove(country);
        }

    }

    @Override
    public void printAll() {
        for (Country country : countriesList) {
            System.out.println(country);
        }
    }


    public Country findCountryById(long id) {
        for (Country country : countries) {
            if (Long.valueOf(id).equals(country.getId())) {
                return country;
            }
        }
        return null;

    }

    @Override
    public List<Country> findAll() {
        return countriesList;
    }
}
