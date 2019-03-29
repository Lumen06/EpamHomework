package homework.City.search;

public enum CityOrderByField {
    NAME("city name"), POPULATION("city population");

    private String description;

    CityOrderByField(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}
