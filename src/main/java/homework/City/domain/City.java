package homework.City.domain;

import homework.Common.Business.domain.BaseDomain;

public class City extends BaseDomain {


    private String name;
    private long population;
    private boolean isCapital;

    public City() {
    }

    public City(Long id, String name, long population, boolean isCapital) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.isCapital = isCapital;
    }

    public City(String name, long population, boolean isCapital) {
        this.name = name;
        this.population = population;
        this.isCapital = isCapital;
    }

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        String capital = "";
        if (this.isCapital) {
            capital = "да";
        } else capital = "нет";

        return "Город: " + name + " Население: " + population + " Является столицей: " + capital;
    }
}
