package homework.Country.Repo.impl;

import homework.Common.Business.Search.Paginator;
import homework.Common.Solutions.Utils.Utils.CollectionUtils;
import homework.Common.Solutions.Utils.Utils.StringUtils;
import homework.Country.Repo.CountryRepo.CountryRepo;
import homework.Country.domain.BaseCountry.Country;
import homework.Country.domain.ColdCountry;
import homework.Country.domain.CountryTemperature;
import homework.Country.domain.HotCountry;
import homework.Country.search.ColdCountrySearchCondition;
import homework.Country.search.CountrySearchCondition;
import homework.Country.search.HotCountrySearchCondition;
import homework.Storage.SequenceGenerator;

import java.util.*;

import static homework.Storage.Storage.countries;
import static homework.Storage.Storage.countriesList;

public class CountryListMemoryRepo implements CountryRepo {

    private CountrySortingComponent countrySortingComponent = new CountrySortingComponent();

    @Override
    public void insert(Collection<Country> items) {

    }

    @Override
    public void add(Country country) {

        country.setId(SequenceGenerator.nextValue());
        countriesList.add(country);
    }

    @Override
    public Optional<Country> findById(Long id) {
        return findCountryById(id);

    }

    @Override
    public List<? extends Country> search(CountrySearchCondition countrySearchCondition) {


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

            if (!foundedCountries.isEmpty() && countrySearchCondition.shouldPaginate()) {
                return getPageableData(foundedCountries, countrySearchCondition.getPaginator());
            }
            if (foundedCountries.size() > 0 && countrySearchCondition.needSorting()) {
                countrySortingComponent.sorting(foundedCountries, countrySearchCondition);
            }

            return foundedCountries;
        }


    private List<ColdCountry> searchColdCountries(ColdCountrySearchCondition searchCondition) {
        List<ColdCountry> result = new ArrayList<>();

        for (Country country : countriesList) {

            if (CountryTemperature.LOW_TEMPERATURE.equals(country.getDiscriminator())) {
                ColdCountry coldCountry = (ColdCountry) country;

                boolean found = true;
                if (searchCondition.searchByTelephoneCode()) {
                    found = searchCondition.getTelephoneCode().equals(coldCountry.getTelephoneCode());
                }

                if (found && searchCondition.searchByPolarNight()) {
                    found = searchCondition.getPolarNight().equals(coldCountry.isPolarNight());
                }


                if (found) {
                    result.add(coldCountry);
                }
            }
        }

        return result;
    }
    private List<HotCountry> searchHotCountries(HotCountrySearchCondition searchCondition) {
        List<HotCountry> result = new ArrayList<>();

        for (Country country : countriesList) {

            if (CountryTemperature.HIGH_TEMPERATURE.equals(country.getDiscriminator())) {
                HotCountry hotCountry = (HotCountry) country;

                boolean found = true;
                if (searchCondition.searchByHottestMonth()) {
                    found = searchCondition.getHottestMonth().equals(hotCountry.getHottestMonth());
                }

                if (found && searchCondition.searchByAverageTemperature()) {
                    found = searchCondition.getAverageTemperature().equals(hotCountry.getAverageTemperature());
                }

                if (found) {
                    result.add(hotCountry);
                }
            }
        }

        return result;
    }


    private List<? extends Country> getPageableData(List<? extends Country> countries, Paginator paginator) {
        return CollectionUtils.getPageableData(countries, paginator.getLimit(), paginator.getOffset());
    }

    @Override
    public void update(Country country) {

    }

    @Override
    public void deleteById(Long id) {
        Optional<Country> country = findCountryById(id);
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


    private Optional<Country> findCountryById(long id) {
        return countriesList.stream().filter(country -> Long.valueOf(id).equals(country.getId())).findAny();

    }

    @Override
    public List<Country> findAll() {
        return countriesList;
    }
}
