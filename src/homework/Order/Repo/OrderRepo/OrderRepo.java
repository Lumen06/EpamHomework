package homework.Order.Repo.OrderRepo;

import homework.Common.Solutions.Utils.BaseRepo.BaseRepo;
import homework.Order.domain.Order;
import homework.Order.search.OrderSearchCondition;

import java.util.List;

public interface OrderRepo extends BaseRepo<Order, Long> {

    List<Order> search(OrderSearchCondition searchCondition);

    int countByCountry(long countryId);

    int countByCity(long cityId);

    int countByUser(long userId);

    void deleteByUserId(long userId);

    List<Order> findByUserId(long userId);


}
