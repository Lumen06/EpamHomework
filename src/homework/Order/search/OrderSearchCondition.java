package homework.Order.search;

import homework.City.domain.City;
import homework.Common.Business.Search.BaseSearchCondition;
import homework.Country.domain.BaseCountry.Country;
import homework.User.domain.User;

public class OrderSearchCondition extends BaseSearchCondition<Long> {

    private String price;
    private User user;
    private Country country;
    private City city;
    private OrderOrderByField orderOrderByField;


    public OrderOrderByField getOrderOrderByField() {
        return orderOrderByField;
    }

    public void setOrderOrderByField(OrderOrderByField orderOrderByField) {
        this.orderOrderByField = orderOrderByField;
    }

    public String getPrice() {
        return price;
    }

    public User getUser() {
        return user;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public boolean needSearchByOrder() {
        return true;
    }
}
