package homework.Country.domain;

import homework.Country.domain.BaseCountry.Country;

public final class HotCountry extends Country {

    private String hottestMonth;
    private String averageTemperature;

    public HotCountry() {

    }

    public HotCountry(String hottestMonth, String averageTemperature) {
        this.hottestMonth = hottestMonth;
        this.averageTemperature = averageTemperature;
    }

    public String getHottestMonth() {
        return hottestMonth;
    }

    public void setHottestMonth(String hottestMonth) {
        this.hottestMonth = hottestMonth;
    }

    public String getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(String averageTemperature) {
        this.averageTemperature = averageTemperature;
    }
}
