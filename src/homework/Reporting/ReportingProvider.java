package homework.Reporting;

import homework.City.service.CityService.CityService;
import homework.Country.service.CounryService.CountryService;
import homework.Order.service.OrderService.OrderService;
import homework.User.service.UserService.UserService;

import java.io.File;

public class ReportingProvider {


    private final UserService userService;
    private final OrderService orderService;
    private final CountryService countryService;
    private final CityService cityService;

    private ReportComponent userOrdersTextFileReport;

    public ReportingProvider(UserService userService, OrderService orderService, CountryService countryService, CityService cityService) {
        this.userService = userService;
        this.orderService = orderService;
        this.countryService = countryService;
        this.cityService = cityService;

        initReportComponents();
    }

    private void initReportComponents() {
        userOrdersTextFileReport = new UsersOrdersToTextFileReport(
                userService,
                orderService);
    }

    public File getUserOrdersTextFileReport() throws Exception {
        return userOrdersTextFileReport.generateReport();
    }


}
