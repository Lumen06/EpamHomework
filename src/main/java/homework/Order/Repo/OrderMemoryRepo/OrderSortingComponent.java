package homework.Order.Repo.OrderMemoryRepo;

import homework.Order.domain.Order;
import homework.Order.search.OrderOrderByField;
import homework.Order.search.OrderSearchCondition;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static homework.Common.Business.Search.SortType.SIMPLE;

public class OrderSortingComponent {
    public void sorting(List<Order> countries, OrderSearchCondition OrderSearchCondition) {
        Comparator<Order> OrderComparator = null;

        OrderOrderByField field = OrderSearchCondition.getOrderOrderByField();

        switch (OrderSearchCondition.getSortType()) {

            case SIMPLE: {
                OrderComparator = OrderComparatorComponent.getInstance().getComparatorForField(field);
                break;
            }
            case COMPLEX: {
                OrderComparator = OrderComparatorComponent.getInstance().getComplexComparator(field);
                break;
            }
        }

        if (OrderComparator != null) {
            switch (OrderSearchCondition.getSortDirection()) {

                case ASC:
                    Collections.sort(countries, OrderComparator);
                    break;
                case DESC:
                    Collections.sort(countries, OrderComparator.reversed());
                    break;
            }
        }
    }
}
