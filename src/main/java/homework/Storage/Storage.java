package homework.Storage;

import homework.City.domain.City;
import homework.Country.domain.BaseCountry.Country;
import homework.Order.domain.Order;
import homework.Passport.domain.Passport;
import homework.User.domain.User;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static final int CAPACITY = 5;
    public static City[] cities = new City[CAPACITY];
    public static Country[] countries = new Country[CAPACITY];
    public static Order[] orders = new Order[CAPACITY];
    public static User[] users = new User[CAPACITY];
    public static Passport[] passports = new Passport[CAPACITY];

    public static List<City> citiesList = new ArrayList<>();
    public static List<Country> countriesList = new ArrayList<>();
    public static List<Order> ordersList = new ArrayList<>();
    public static List<Passport> passportsList = new ArrayList<>();
    public static List<User> usersList = new ArrayList<>();
}
