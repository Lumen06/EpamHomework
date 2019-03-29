package homework.City.search;

import homework.Common.Business.Search.BaseSearchCondition;

public class CitySearchCondition extends BaseSearchCondition<Long> {

    private String name;
    private long population;
    private boolean isCapital;
    private CityOrderByField cityOrderByField;

    public CityOrderByField getCityOrderByField() {
        return cityOrderByField;
    }

    public void setCityOrderByField(CityOrderByField cityOrderByField) {
        this.cityOrderByField = cityOrderByField;
    }

    public String getName() {
        return name;
    }

    public long getPopulation() {
        return population;
    }

    public boolean isCapital() {
        return isCapital;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public void setCapital(boolean capital) {
        isCapital = capital;
    }

}
