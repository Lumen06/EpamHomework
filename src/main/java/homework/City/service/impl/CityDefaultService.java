package homework.City.service.impl;

import homework.City.Repo.CityRepo.CityRepo;
import homework.City.Repo.impl.CityArrayMemoryRepo;
import homework.City.Repo.impl.CityListMemoryRepo;
import homework.City.domain.City;
import homework.City.exception.CityExceptionMeta;
import homework.City.exception.DeleteCityException;
import homework.City.search.CitySearchCondition;
import homework.City.service.CityService.CityService;
import homework.Order.Repo.OrderRepo.OrderRepo;

import java.util.Collection;
import java.util.List;

public class CityDefaultService implements CityService {

    private final CityRepo cityRepo;
    private final OrderRepo orderRepo;

    @Override
    public void insert(Collection<City> items) {

    }

    public CityDefaultService(CityRepo cityRepo, OrderRepo orderRepo) {
        this.cityRepo = cityRepo;
        this.orderRepo = orderRepo;
    }

    @Override
    public void deleteById(Long id) {

        if (id != null) {
            boolean noOrders = orderRepo.countByCity(id) == 0;

            if (noOrders) {
                cityRepo.deleteById(id);
            } else {
                throw new DeleteCityException(CityExceptionMeta.DELETE_CITY_CONSTRAINT_ERROR);
            }
        }
    }

    @Override
    public void printAll() {
        cityRepo.printAll();
    }

    @Override
    public void add(City city) {
        if (city != null) {
            cityRepo.add(city);
        }
    }

    @Override
    public City findById(Long id) {
        if (id != null) {
            return cityRepo.findById(id);
        }
        return null;
    }

    @Override
    public List<City> search(CitySearchCondition citySearchCondition) {
        return cityRepo.search(citySearchCondition);
    }


    public void delete(City city) {
        if (city.getId() != null) {
            this.deleteById(city.getId());
        }
    }

    @Override
    public void update(City city) {
        if (city != null) {
            cityRepo.update(city);
        }
    }

    @Override
    public List<City> findAll() {
        return cityRepo.findAll();
    }
}
