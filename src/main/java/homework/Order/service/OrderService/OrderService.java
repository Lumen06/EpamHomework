package homework.Order.service.OrderService;

import homework.Common.Solutions.Utils.BaseService.BaseService;
import homework.Order.domain.Order;
import homework.Order.search.OrderSearchCondition;

import java.util.List;

public interface OrderService extends BaseService<Order, Long> {

    List<Order> search(OrderSearchCondition orderSearchCondition);

    void deleteByUserId(Long userId);

    List<Order> getOrdersByUser(Long userId);


}
