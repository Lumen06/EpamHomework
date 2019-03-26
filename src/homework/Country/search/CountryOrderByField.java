package homework.Country.search;

public enum CountryOrderByField {
    COUNTRY_NAME("sorting by country's name"), NUMBER_OF_COUNTRIES("sorting by number of countries");

    String description;

    CountryOrderByField(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
