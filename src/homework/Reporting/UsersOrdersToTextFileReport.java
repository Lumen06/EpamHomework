package homework.Reporting;

import homework.City.service.CityService.CityService;
import homework.Country.domain.BaseCountry.Country;
import homework.Country.service.CounryService.CountryService;
import homework.Order.domain.Order;
import homework.Order.service.OrderService.OrderService;
import homework.User.domain.User;
import homework.User.service.UserService.UserService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class UsersOrdersToTextFileReport implements ReportComponent {

    private static final String USER_SEPARATOR = "--------------------------";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private final UserService userService;
    private final OrderService orderService;


    public UsersOrdersToTextFileReport(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;

    }

    @Override
    public File generateReport() throws Exception {
        File tempFile = File.createTempFile(System.currentTimeMillis() + "_user_orders_report", "_io.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            List<String> report = getReportData();

            for (String reportLine : report) {
                writer.write(reportLine);
                writer.newLine();
            }

            writer.flush();
        }

        return tempFile;
    }

    private List<String> getReportData() {
        List<String> report = new ArrayList<>();
        List<User> users = userService.findAll();

        if (users != null && !users.isEmpty()) {
            for (User user : users) {
                report.add(USER_SEPARATOR);
                report.add(userToReportLine(user));
                report.addAll(getOrderInformation(user));
            }
        }

        return report;
    }

    private String userToReportLine(User user) {
        StringBuilder userAsStr = new StringBuilder();

        userAsStr.append("User:").append(LINE_SEPARATOR)
                .append("Full name: ").append(user.getName()).append(" ").append(user.getlastName());

        return userAsStr.toString();
    }

    private List<String> getOrderInformation(User user) {
        List<String> reportData = new ArrayList<>();

        List<Order> orders = orderService.getOrdersByUser(user.getId());
        reportData.add("Total orders: " + orders.size());

        if (!orders.isEmpty()) {
            reportData.add("Orders:");
            for (Order order : orders) {
                reportData.add(orderToReportLine(order));
            }
        }

        return reportData;
    }

    private String orderToReportLine(Order order) {
        StringBuilder orderAsStr = new StringBuilder();

        orderAsStr.append("Order: ");
        for (int i = 0; i < order.getCountries().length; i++) {
            orderAsStr.append("Country: ").append(order.getCities()[i].getName()).append("; ");
            for (int j = 0; j < order.getCities().length; j++) {
                orderAsStr.append(" City: ").append(order.getCities()[i].getName()).append("; ");
            }
        }
        orderAsStr.append(" Price: ").append(order.getPrice());

        return orderAsStr.toString();
    }

}
