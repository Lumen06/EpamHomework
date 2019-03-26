package homework.Country.service.impl;

import homework.City.Repo.CityRepo.CityRepo;
import homework.City.Repo.impl.CityListMemoryRepo;
import homework.City.domain.City;
import homework.City.service.CityService.CityService;
import homework.Common.Business.Exceptions.TravelAgencyUncheckedException;
import homework.Country.Repo.CountryRepo.CountryRepo;
import homework.Country.domain.BaseCountry.Country;
import homework.Country.Repo.impl.CountryArrayMemoryRepo;
import homework.Country.exception.DeleteCountryException;
import homework.Country.search.CountrySearchCondition;
import homework.Country.service.CounryService.CountryService;
import homework.Order.Repo.OrderRepo.OrderRepo;

import java.lang.reflect.Array;
import java.util.*;

import static homework.Country.exception.CountryExceptionMeta.DELETE_COUNTRY_CONSTRAINT_ERROR;

public class CountryDefaultService implements CountryService {

    private final CityService cityService;
    private final CountryRepo countryRepo;
    private final OrderRepo orderRepo;


    public CountryDefaultService(CityService cityService, CountryRepo countryRepo, OrderRepo orderRepo) {
        this.cityService = cityService;
        this.countryRepo = countryRepo;
        this.orderRepo = orderRepo;
    }

    @Override
    public void add(Country country) {
        if (country != null) {
            countryRepo.add(country);
        }
        if (country.getCities() != null) {
            for (City city : country.getCities()) {
                this.cityService.add(city);
            }

        }
    }

    @Override
    public void deleteById(Long id) throws TravelAgencyUncheckedException {

        if (id != null) {
            boolean noOrders = orderRepo.countByCountry(id) == 0;

            if (noOrders) {
                removeAllCitiesFromCountry(id);
                countryRepo.deleteById(id);
            } else {
                throw new DeleteCountryException(DELETE_COUNTRY_CONSTRAINT_ERROR);
            }
        }
        if (id != null) {
            countryRepo.deleteById(id);
        }

    }

    @Override
    public void printAll() {
        countryRepo.printAll();
    }


    @Override
    public Country findById(Long id) {
        if (id != null) {
            return countryRepo.findById(id);
        }
        return null;
    }

    @Override
    public List<Country> search(CountrySearchCondition countrySearchCondition) {
        return countryRepo.search(countrySearchCondition);
    }


    public void delete(Country country) {
        if (country.getId() != null) {
            this.deleteById(country.getId());
        }
    }

    @Override
    public void update(Country country) {
        if (country != null) {
            countryRepo.update(country);
        }
    }

    @Override
    public void removeAllCitiesFromCountry(Long countyId) throws TravelAgencyUncheckedException {
        Country country = findById(countyId);

        if (country != null) {
            List<City> listOfCities = country.getCities() == null ? Collections.emptyList() : Arrays.asList(country.getCities());

            for (City city : listOfCities) {
                cityService.deleteById(city.getId());
            }
        }
    }

    @Override
    public List<Country> findAll() {
        return countryRepo.findAll();
    }
}
