package homework.Country.search;

public class HotCountrySearchCondition {

    private String hottestMonth;
    private String averageTemperature;

    public boolean searchByHottestMonth() {
        return hottestMonth != null;
    }
    public boolean searchByAverageTemperature() {
        return averageTemperature != null;
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
