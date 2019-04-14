package homework.Order.dto;

import homework.City.domain.City;
import homework.Country.domain.BaseCountry.Country;
import homework.User.domain.User;

public class OrderDto {

    private String price;
    private User user;
    private Country[] countries;
    private City[] cities;


    public OrderDto() {

    }

    public String getPrice() {
        return price;
    }

    public User getUser() {
        return user;
    }

    public Country[] getCountries() {
        return countries;
    }

    public City[] getCities() {
        return cities;
    }

    @Override
    public String toString() {

        String countryList = "";
        for (Country country : countries) {
            countryList += country.getCountryName() + " ";
        }

        String citiesList = "";
        for (City city : cities) {
            citiesList += city.getName() + " ";
        }

        return "Cтоимость: " + price + "\nЗаказчик " + user.toString() + "\nСтрана(-ы): " + countryList + "\nГород(-а): " + citiesList;
    }


}
