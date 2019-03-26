package homework.Common.Business.Application;

import homework.City.service.CityService.CityService;
import homework.Country.service.CounryService.CountryService;
import homework.Order.service.OrderService.OrderService;
import homework.Passport.service.PassportService.PassportService;
import homework.User.service.UserService.UserService;

public interface ServiceFactory {

    CountryService getCountryService();

    CityService getCityService();

    OrderService getOrderService();

    UserService getUserService();

    PassportService getPassportService();

    /////////
}
