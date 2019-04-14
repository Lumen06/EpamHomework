package homework.Order.Repo.OrderMemoryRepo;

import homework.Common.Solutions.Utils.Utils.StringUtils;
import homework.Order.Repo.OrderRepo.OrderRepo;
import homework.Order.domain.Order;
import homework.Order.search.OrderSearchCondition;
import homework.Storage.SequenceGenerator;

import java.util.*;

import static homework.Storage.Storage.countriesList;
import static homework.Storage.Storage.orders;
import static homework.Storage.Storage.ordersList;

public class OrderListMemoryRepo implements OrderRepo {

    private OrderSortingComponent orderSortingComponent = new OrderSortingComponent();


    @Override
    public void insert(Collection<Order> items) {

    }

    @Override
    public void add(Order order) {
        order.setId(SequenceGenerator.nextValue());
        ordersList.add(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return findOrderById(id);

    }

    @Override
    public void update(Order order) {

    }


    @Override
    public int countByCountry(long countryId) {
        int count = 0;
        for (Order order : orders) {
            for (int i = 0; i < order.getCountries().length; i++) {
                if (countryId == order.getCountries()[i].getId()) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public int countByCity(long cityId) {

        int count = 0;
        for (Order order : orders) {
            for (int i = 0; i < order.getCities().length; i++) {
                if (cityId == order.getCountries()[i].getId()) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public void deleteByUserId(long userId) {

    }

    @Override
    public List<Order> findByUserId(long userId) {
        List<Order> foundOrders = new ArrayList<>();

        for (Order order : orders) {
            if (order.getUser().getId().equals(userId)) {
                foundOrders.add(order);
            }
        }

        return foundOrders;
    }

    @Override
    public List<Order> search(OrderSearchCondition orderSearchCondition) {

            boolean searchByPrice = StringUtils.isNotBlank(orderSearchCondition.getPrice());

            boolean searchByUser = StringUtils.isNotBlank(orderSearchCondition.getUser() + "");

            boolean searchByCountries = StringUtils.isNotBlank(orderSearchCondition.getCountry() + "");

            List<Order> foundedOrders = new ArrayList<>();

            for (Order order : orders) {

                boolean found = true;

                if (searchByUser) {
                    found = orderSearchCondition.getUser().equals(order.getUser());
                }
                if (found && searchByPrice) {
                    found = orderSearchCondition.getPrice().equals(order.getPrice());
                }
                if (found && searchByCountries) {
                    found = Arrays.asList(order.getCountries()).contains(orderSearchCondition.getCountry());
                }
                if (found && searchByCountries) {
                    found = Arrays.asList(order.getCountries()).contains(orderSearchCondition.getCountry());
                }
                if (found) {
                    foundedOrders.add(order);
                }
            }


            if (foundedOrders.size() > 0 && orderSearchCondition.needSorting()) {
                orderSortingComponent.sorting(foundedOrders, orderSearchCondition);
            }
            return foundedOrders;
        }


    @Override
    public void deleteById(Long id) {
        Optional<Order> order = findOrderById(id);
        if (order != null) {
            ordersList.remove(order);
        }
    }

    @Override
    public void printAll() {
        for (Order order : ordersList) {
            System.out.println(order);

        }
    }

    public Optional<Order> findOrderById(long id) {

        return ordersList.stream().filter(country -> Long.valueOf(id).equals(country.getId())).findAny();


    }

    @Override
    public int countByUser(long userId) {
        int count = 0;
        for (Order order : orders) {
            if (userId == order.getUser().getId()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(Arrays.asList(orders));

    }
}
