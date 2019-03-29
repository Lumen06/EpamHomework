package AgencyDemo;

import homework.City.domain.City;
import homework.Country.domain.BaseCountry.Country;
import homework.Passport.domain.Passport;

public class Main {
    public static void main(String[] args) {

        Passport passport = new Passport(1, 2);

        //User user = new User("Sergey", "Vladimir", passport);

        City city = new City("London", 86000000, true);
        City city1 = new City("Scotland", 5373000, false);
        City city2 = new City("Wales", 3099000, false);

        //Country country = new Country("England", "ENG", new City[]{city, city1, city2});

        City city3 = new City("Magadan", 86000000, false);
        City city4 = new City("Bobruysk", 5373000, false);
        City city5 = new City("Moscow", 3099000, true);


        //Country country1 = new Country("Russia", "RUS", new City[]{city3, city4, city5});

        //Order order = new Order("22 000", user, new Country[]{country, country1}, new City[]{country.getCities()[0], country1.getCities()[2]});

        //System.out.println(order.toString());
    }
}
