package homework.Order.service.impl;

import homework.City.Repo.CityRepo.CityRepo;
import homework.City.domain.City;
import homework.Country.Repo.CountryRepo.CountryRepo;
import homework.Country.domain.BaseCountry.Country;
import homework.Order.Repo.OrderRepo.OrderRepo;
import homework.Order.domain.Order;
import homework.Order.search.OrderSearchCondition;
import homework.Order.service.OrderService.OrderService;

import java.util.Collections;
import java.util.List;


public class OrderDefaultMemoryService implements OrderService {


    private final OrderRepo orderRepo;
    private final CountryRepo countryRepo;
    private final CityRepo cityRepo;


    public OrderDefaultMemoryService(OrderRepo orderRepo, CountryRepo countryRepo, CityRepo cityRepo) {
        this.orderRepo = orderRepo;
        this.countryRepo = countryRepo;
        this.cityRepo = cityRepo;
    }

    @Override
    public void add(Order order) {
        if (order != null) {
            orderRepo.add(order);
        }

        if (order.getCountries() != null) {
            for (Country country : order.getCountries()) {
                countryRepo.add(country);
            }

            if (order.getCities() != null) {
                for (City city : order.getCities()) {
                    if (city != null) {
                        cityRepo.add(city);
                    }
                }

            }
        }
    }

    @Override
    public Order findById(Long id) {
        if (id != null) {
            return orderRepo.findById(id);
        }

        return null;
    }

    @Override
    public void delete(Order order) {
        if (order != null) {
            this.deleteById(order.getId());
        }
    }

    @Override
    public List<Order> search(OrderSearchCondition orderSearchCondition) {
        return orderRepo.search(orderSearchCondition);
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            orderRepo.deleteById(id);
        }

    }

    @Override
    public void deleteByUserId(Long userId) {
        if (userId != null) {
            orderRepo.deleteByUserId(userId);
        }
    }

    @Override
    public List<Order> getOrdersByUser(Long userId) {
        if (userId != null) {
            orderRepo.findByUserId(userId);
        }

        return Collections.emptyList();
    }

    @Override
    public void printAll() {
        orderRepo.printAll();
    }

    @Override
    public void update(Order order) {
        if (order != null) {
            orderRepo.update(order);
        }
    }

    @Override
    public List<Order> findAll() {
        return orderRepo.findAll();
    }
}
