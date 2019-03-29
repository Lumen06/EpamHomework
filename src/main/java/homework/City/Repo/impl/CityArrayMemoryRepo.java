package homework.City.Repo.impl;

import homework.City.Repo.CityRepo.CityRepo;
import homework.City.domain.City;
import homework.City.search.CitySearchCondition;
import homework.Common.Business.Search.Paginator;
import homework.Common.Solutions.Utils.Utils.ArrayUtils;
import homework.Common.Solutions.Utils.Utils.CollectionUtils;
import homework.Common.Solutions.Utils.Utils.StringUtils;
import homework.Storage.SequenceGenerator;

import java.util.*;

import static homework.Storage.Storage.cities;

public class CityArrayMemoryRepo implements CityRepo {


    private CitySortingComponent citySortingComponent = new CitySortingComponent();
    private int cityIndex = -1;


    @Override
    public void add(City city) {
        if (cityIndex == cities.length - 1) {
            City[] newCities = new City[(int) (cityIndex * 1.5)];
            System.arraycopy(cities, 0, newCities, 0, cities.length);
            cities = newCities;
        }
        cityIndex++;
        city.setId(SequenceGenerator.nextValue());
        cities[cityIndex] = city;

    }

    @Override
    public City findById(Long id) {
        Integer index = findCityByIndex(id);
        if (index != null) {
            return cities[index];
        }

        return null;
    }

    @Override
    public void update(City entity) {

    }

    @Override
    public List<City> search(CitySearchCondition citySearchCondition) {

        if (citySearchCondition.getId() != null) {
            return Collections.singletonList(findById(citySearchCondition.getId()));

        } else {

            boolean searchByName = StringUtils.isNotBlank(citySearchCondition.getName());

            boolean searchByPopulation = StringUtils.isNotBlank(citySearchCondition.getPopulation() + "");

            boolean searchByCapital = StringUtils.isNotBlank(citySearchCondition.isCapital() + "");

            int resultIndex = 0;

            City[] newArrOfCities = new City[cities.length];

            for (City city : cities) {

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
                    newArrOfCities[resultIndex] = city;
                    resultIndex++;
                }
            }
            if (resultIndex > 0) {
                City[] allFoundedCities = new City[resultIndex];
                System.arraycopy(newArrOfCities, 0, allFoundedCities, 0, resultIndex);
                List<City> resultListOfCities = new ArrayList<>(Arrays.asList(allFoundedCities));

                if (!resultListOfCities.isEmpty() && citySearchCondition.shouldPaginate()) {
                    return getPageableData(resultListOfCities, citySearchCondition.getPaginator());
                }

                if (resultListOfCities.size() > 0 && citySearchCondition.needSorting()) {
                    citySortingComponent.sorting(resultListOfCities, citySearchCondition);
                }
                return resultListOfCities;

            } else {
                return Collections.emptyList();
            }
        }
    }

    @Override
    public void insert(Collection<City> items) {

    }

    @Override
    public void deleteById(Long id) {
        Integer index = findCityByIndex(id);
        if (index != null) {
            deleteCityByIndex(index);
        }
    }

    @Override
    public void printAll() {
        for (City city : cities) {
            System.out.println(city);
        }
    }


    private Integer findCityByIndex(Long index) {
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].getId().equals(index)) {
                return i;
            }
        }
        return null;

    }


    private void deleteCityByIndex(int index) {
        ArrayUtils.removeElement(cities, index);
        cityIndex--;
    }

    private void deleteCityByEntity(City city) {
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].equals(city)) {
                deleteCityByIndex(i);
            }
        }
    }

    private List <City> getPageableData(List<City> cities, Paginator paginator) {
        return CollectionUtils.getPageableData(cities, paginator.getLimit(), paginator.getOffset());
    }

    @Override
    public List<City> findAll() {
        return new ArrayList<>(Arrays.asList(cities));
    }
}
