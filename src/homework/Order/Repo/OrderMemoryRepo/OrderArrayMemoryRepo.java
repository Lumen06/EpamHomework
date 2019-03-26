package homework.Order.Repo.OrderMemoryRepo;

import homework.Common.Solutions.Utils.Utils.ArrayUtils;
import homework.Common.Solutions.Utils.Utils.StringUtils;
import homework.Country.domain.BaseCountry.Country;
import homework.Order.Repo.OrderRepo.OrderRepo;
import homework.Order.domain.Order;
import homework.Order.search.OrderSearchCondition;
import homework.Storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static homework.Storage.Storage.countries;
import static homework.Storage.Storage.orders;

public class OrderArrayMemoryRepo implements OrderRepo {

    private OrderSortingComponent orderSortingComponent = new OrderSortingComponent();
    private int orderIndex = -1;


    @Override
    public void add(Order order) {
        if (orderIndex == orders.length - 1) {
            Order[] newOrders = new Order[(int) (orderIndex * 1.5)];
            System.arraycopy(orders, 0, newOrders, 0, orders.length);
            orders = newOrders;
        }
        orderIndex++;
        order.setId(SequenceGenerator.nextValue());
        orders[orderIndex] = order;

    }

    @Override
    public void update(Order order) {

    }

    @Override
    public Order findById(Long id) {
        Integer index = findOrderByIndex(id);
        if (index != null) {
            return orders[index];
        }

        return null;
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
    public void deleteByUserId(long userId) {

    }

    @Override
    public List<Order> search(OrderSearchCondition orderSearchCondition) {
        if (orderSearchCondition.getId() != null) {
            return Collections.singletonList(findById(orderSearchCondition.getId()));

        } else {

            boolean searchByPrice = StringUtils.isNotBlank(orderSearchCondition.getPrice());

            boolean searchByUser = StringUtils.isNotBlank(orderSearchCondition.getUser() + "");

            boolean searchByCountries = StringUtils.isNotBlank(orderSearchCondition.getCountry() + "");

            int resultIndex = 0;

            Order[] newArrOfOrders = new Order[orders.length];

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
                    newArrOfOrders[resultIndex] = order;
                    resultIndex++;
                }
            }
            if (resultIndex > 0) {
                Order[] allFoundedOrders = new Order[resultIndex];
                System.arraycopy(newArrOfOrders, 0, allFoundedOrders, 0, resultIndex);

                List<Order> resultListOfOrders = new ArrayList<>(Arrays.asList(allFoundedOrders));

                if (resultListOfOrders.size() > 0 && orderSearchCondition.needSorting()) {
                    orderSortingComponent.sorting(resultListOfOrders, orderSearchCondition);
                }
                return resultListOfOrders;
            } else {
                return Collections.emptyList();
            }
        }
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
    public void deleteById(Long id) {
        Integer index = findOrderByIndex(id);
        deleteOrderByIndex(index);
    }

    @Override
    public void printAll() {
        for (Order Order : orders) {
            System.out.println(Order);

        }
    }

    public Integer findOrderByIndex(Long index) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getId().equals(index)) {
                return i;
            }
        }
        return null;

    }


    public void deleteOrderByIndex(int index) {
        ArrayUtils.removeElement(orders, index);
        orderIndex--;
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(Arrays.asList(orders));

    }
}
