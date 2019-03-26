package homework.Country.Repo.impl;

import homework.Common.Solutions.Utils.Utils.ArrayUtils;
import homework.Common.Solutions.Utils.Utils.StringUtils;
import homework.Country.Repo.CountryRepo.CountryRepo;
import homework.Country.domain.BaseCountry.Country;
import homework.Country.search.CountrySearchCondition;
import homework.Storage.SequenceGenerator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static homework.Storage.Storage.countries;

public class CountryArrayMemoryRepo implements CountryRepo {

    private int countryIndex = -1;
    private CountrySortingComponent countrySortingComponent = new CountrySortingComponent();

    @Override
    public void add(Country country) {

        if (countryIndex == countries.length - 1) {
            Country[] newCountries = new Country[(int) (countryIndex * 1.5)];
            System.arraycopy(countries, 0, newCountries, 0, countries.length);
            countries = newCountries;
        }
        countryIndex++;
        country.setId(SequenceGenerator.nextValue());
        countries[countryIndex] = country;


    }

    @Override
    public void update(Country country) {

    }

    @Override
    public Country findById(Long id) {
        Integer index = findCountryByIndex(id);
        if (index != null) {
            return countries[index];
        }

        return null;

    }

    @Override
    public List<Country> search(CountrySearchCondition countrySearchCondition) {
        if (countrySearchCondition.getId() != null) {
            return Collections.singletonList(findById(countrySearchCondition.getId()));

        } else {

            boolean searchByName = StringUtils.isNotBlank(countrySearchCondition.getCountryName());

            boolean searchByPopulation = StringUtils.isNotBlank(countrySearchCondition.getLanguage() + "");

            boolean searchByCapital = StringUtils.isNotBlank(countrySearchCondition.getCapital() + "");

            int resultIndex = 0;

            Country[] newArrOfCountries = new Country[countries.length];

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
                    newArrOfCountries[resultIndex] = country;
                    resultIndex++;
                }
            }
            if (resultIndex > 0) {
                Country[] allFoundedCountries = new Country[resultIndex];
                System.arraycopy(newArrOfCountries, 0, allFoundedCountries, 0, resultIndex);
                List<Country> resultListOfCountries = new ArrayList<>(Arrays.asList(allFoundedCountries));
                if (resultListOfCountries.size() > 0 && countrySearchCondition.needSorting()) {
                    countrySortingComponent.sorting(resultListOfCountries, countrySearchCondition);
                }
                return resultListOfCountries;
            } else {
                return Collections.emptyList();
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        Integer index = findCountryByIndex(id);
        deleteCountryByIndex(index);

    }

    @Override
    public void printAll() {
        for (Country country : countries) {
            System.out.println(country);
        }
    }


    public Integer findCountryByIndex(Long index) {
        for (int i = 0; i < countries.length; i++) {
            if (countries[i].getId().equals(index)) {
                return i;
            }
        }
        return null;

    }


    public void deleteCountryByIndex(int index) {
        ArrayUtils.removeElement(countries, index);
        countryIndex--;
    }

    @Override
    public List<Country> findAll() {
        return new ArrayList<>(Arrays.asList(countries));
    }
}
