package homework.City.Repo.impl;

import homework.City.Repo.CityRepo.CityRepo;
import homework.City.search.CitySearchCondition;
import homework.City.domain.City;
import homework.Common.Business.Search.Paginator;
import homework.Common.Solutions.Utils.Utils.CollectionUtils;
import homework.Common.Solutions.Utils.Utils.StringUtils;
import homework.Storage.SequenceGenerator;

import java.util.*;

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
    public Optional<City> findById(Long id) {
        return findCityById(id);
    }

    @Override
    public List<City> search(CitySearchCondition citySearchCondition) {


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


    private List <City> getPageableData(List<City> cities, Paginator paginator) {
        return CollectionUtils.getPageableData(cities, paginator.getLimit(), paginator.getOffset());
    }

    @Override
    public void update(City city) {

    }

    @Override
    public void deleteById(Long id) {
        Optional<City> city = findCityById(id);
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


    private Optional<City> findCityById(long index) {

        return citiesList.stream().filter(city -> Long.valueOf(index).equals(city.getId())).findAny();
    }

    @Override
    public List<City> findAll() {
        return citiesList;
    }
}





