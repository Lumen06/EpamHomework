package homework.Country.Repo.impl;

import homework.Common.Business.Search.Paginator;
import homework.Common.Solutions.Utils.Utils.ArrayUtils;
import homework.Common.Solutions.Utils.Utils.CollectionUtils;
import homework.Common.Solutions.Utils.Utils.OptionalUtils;
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
import java.util.stream.IntStream;

import static homework.Storage.Storage.cities;
import static homework.Storage.Storage.countries;
import static homework.Storage.Storage.countriesList;

public class CountryArrayMemoryRepo implements CountryRepo {

    private int countryIndex = -1;
    private CountrySortingComponent countrySortingComponent = new CountrySortingComponent();

    @Override
    public void insert(Collection<Country> items) {

    }

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
    public Optional<Country> findById(Long id) {

        return findCountryByIndex(id).map(x -> countries[x]);


    }

    @Override
    public List<? extends Country> search(CountrySearchCondition countrySearchCondition) {


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
                if (!resultListOfCountries.isEmpty() && countrySearchCondition.shouldPaginate()) {
                    return getPageableData(resultListOfCountries, countrySearchCondition.getPaginator());
                }

                return resultListOfCountries;
            } else {
                return Collections.emptyList();
            }
        }

    private List<ColdCountry> searchColdCountries(ColdCountrySearchCondition searchCondition) {
        ColdCountry[] foundColdCountries = new ColdCountry[countries.length];
        int resultIndex = 0;

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
                    foundColdCountries[resultIndex] = coldCountry;
                    resultIndex++;
                }
            }

        }

        if (resultIndex > 0) {
            ColdCountry toReturn[] = new ColdCountry[resultIndex];
            System.arraycopy(foundColdCountries, 0, toReturn, 0, resultIndex);
            return new ArrayList<>(Arrays.asList(toReturn));
        }


        return Collections.emptyList();
    }

    private List<HotCountry> searchHotCountries(HotCountrySearchCondition searchCondition) {
        HotCountry[] foundHotCountries = new HotCountry[countries.length];
        int resultIndex = 0;

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
                    foundHotCountries[resultIndex] = hotCountry;
                    resultIndex++;
                }
            }

        }

        if (resultIndex > 0) {
            HotCountry toReturn[] = new HotCountry[resultIndex];
            System.arraycopy(foundHotCountries, 0, toReturn, 0, resultIndex);
            return new ArrayList<>(Arrays.asList(toReturn));
        }

        return Collections.emptyList();
    }


    private List <? extends Country> getPageableData(List<? extends Country> countries, Paginator paginator) {
        return CollectionUtils.getPageableData(countries, paginator.getLimit(), paginator.getOffset());
    }


    @Override
    public void deleteById(Long id) {
        findCountryByIndex(id).ifPresent(this::deleteCountryByIndex);

    }

    @Override
    public void printAll() {
        Arrays.stream(cities).filter(Objects::nonNull).forEach(System.out::println);
    }


    public Optional<Integer> findCountryByIndex(Long index) {
        OptionalInt optionalInt = IntStream.range(0, countries.length).filter(i ->
                countries[i] != null && Long.valueOf(index).equals(countries[i].getId())
        ).findAny();

        return OptionalUtils.valueOf(optionalInt);

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
