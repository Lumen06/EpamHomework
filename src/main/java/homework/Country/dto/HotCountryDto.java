package homework.Country.dto;

import homework.Common.Business.BaseDto;

public class HotCountryDto extends BaseDto<Long>{

    private String hottestMonth;
    private String averageTemperature;

    public HotCountryDto() {}

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
