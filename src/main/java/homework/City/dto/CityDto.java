package homework.City.dto;

import homework.Common.Business.BaseDto;

public class CityDto extends BaseDto<Long>{

    private String name;
    private long population;
    private boolean isCapital;

    public CityDto() {}


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

    @Override
    public String toString() {
        String capital = "";
        if (this.isCapital) {
            capital = "да";
        } else capital = "нет";

        return "Город: " + name + " Население: " + population + " Является столицей: " + capital;
    }
}
