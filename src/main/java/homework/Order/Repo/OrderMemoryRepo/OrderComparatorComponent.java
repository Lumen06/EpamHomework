package homework.Order.Repo.OrderMemoryRepo;

import homework.Order.domain.Order;
import homework.Order.search.OrderOrderByField;

import java.util.*;

import static homework.Common.Solutions.Utils.BaseRepo.CommonComparatorHolder.getComparatorForNullableLongs;
import static homework.Common.Solutions.Utils.BaseRepo.CommonComparatorHolder.getComparatorForNullableStrings;
import static homework.Order.search.OrderOrderByField.PRICE;
import static homework.Order.search.OrderOrderByField.USER;

public class OrderComparatorComponent {
    private static final OrderComparatorComponent INSTANCE = new OrderComparatorComponent();
    private static Map<OrderOrderByField, Comparator<Order>> comparatorsByFields = new HashMap<>();
    private static Set<OrderOrderByField> fieldComparePriorityOrder = new LinkedHashSet<>(Arrays.asList(USER, PRICE));


    static {
        comparatorsByFields.put(USER, getOrderComparatorByUser());
        comparatorsByFields.put(PRICE, getOrderComparatorByPrice());
    }

    private OrderComparatorComponent() {
    }


    public static Comparator<Order> getOrderComparatorByUser() {
        return new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return getComparatorForNullableStrings().compare(o1.getUser().getlastName(), o2.getUser().getlastName());
            }
        };
    }

    public static Comparator<Order> getOrderComparatorByPrice() {
        return new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return getComparatorForNullableLongs().compare(Long.parseLong(o1.getPrice()), Long.parseLong(o2.getPrice()));
            }
        };
    }

    public static Comparator<Order> getComparatorForField(OrderOrderByField field) {
        return comparatorsByFields.get(field);
    }

    public static OrderComparatorComponent getInstance() {
        return INSTANCE;
    }

    public static Comparator<Order> getComplexComparator(OrderOrderByField field) {
        return new Comparator<Order>() {
            Comparator<Order> comparator = getComparatorForField(field);

            int result = 0;

            @Override

            public int compare(Order o1, Order o2) {
                if (comparator != null) {
                    result = comparator.compare(o1, o2);
                } else for (OrderOrderByField otherfields : fieldComparePriorityOrder) {
                    if (!otherfields.equals(field)) {
                        result = getComparatorForField(otherfields).compare(o1, o2);
                    }
                    if (result != 0) {
                        break;
                    }
                }
                return result;
            }
        };
    }
}
