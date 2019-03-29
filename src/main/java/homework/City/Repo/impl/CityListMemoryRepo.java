package homework.City.Repo.impl;

import homework.City.Repo.CityRepo.CityRepo;
import homework.City.search.CitySearchCondition;
import homework.City.domain.City;
import homework.Common.Business.Search.Paginator;
import homework.Common.Solutions.Utils.Utils.CollectionUtils;
import homework.Common.Solutions.Utils.Utils.StringUtils;
import homework.Storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static homework.Storage.Storage.cities;
import static homework.Storage.Storage.citiesList;

public class CityListMemoryRepo implements CityRepo {

    private CitySortingComponent citySortingComponent = new CitySortingComponent();

    @Override
    public void insert(Collection<City> items) {

    }

    @Override
    public void add(City city) {
        city.setId(SequenceGenerator.nextValue());
        citiesList.add(city);
    }

    @Override
    public City findById(Long id) {
        return findCityById(id);
    }

    @Override
    public List<City> search(CitySearchCondition citySearchCondition) {


        if (citySearchCondition.getId() != null)
            return Collections.singletonList(findById(citySearchCondition.getId()));
        if (citySearchCondition.getId() != null) {
            return Collections.singletonList(findById(citySearchCondition.getId()));

        } else {

            boolean searchByName = StringUtils.isNotBlank(citySearchCondition.getName());

            boolean searchByPopulation = StringUtils.isNotBlank(citySearchCondition.getPopulation() + "");

            boolean searchByCapital = StringUtils.isNotBlank(citySearchCondition.isCapital() + "");

            List<City> foundedCities = new ArrayList<>();

            for (City city : citiesList) {

                boolean found = true;

                if (searchByName) {
                    found = citySearchCondition.getName().equals(city.getName());
                }
                if (found && searchByPopulation) {
                    found = citySearchCondition.getPopulation() == city.getPopulation();
                }
                if (found && searchByCapital) {
                    found = citySearchCondition.isCapital() == city.isCapital();
                }
                if (found) {
                    foundedCities.add(city);
                }
                if (foundedCities.size() > 0 && citySearchCondition.needSorting()) {
                    citySortingComponent.sorting(foundedCities, citySearchCondition);
                }

                if (!foundedCities.isEmpty() && citySearchCondition.shouldPaginate()) {
                    return getPageableData(foundedCities, citySearchCondition.getPaginator());
                }
            }
            return foundedCities;

        }
    }

    private List <City> getPageableData(List<City> cities, Paginator paginator) {
        return CollectionUtils.getPageableData(cities, paginator.getLimit(), paginator.getOffset());
    }

    @Override
    public void update(City city) {

    }

    @Override
    public void deleteById(Long id) {
        City city = findCityById(id);
        if (city != null) {
            citiesList.remove(city);
        }
    }


    @Override
    public void printAll() {
        for (City city : citiesList) {
            System.out.println(city);
        }
    }


    private City findCityById(long index) {

        for (City city : cities) {
            if (Long.valueOf(index).equals(city.getId())) {
                return city;
            }
        }
        return null;
    }

    @Override
    public List<City> findAll() {
        return citiesList;
    }
}





