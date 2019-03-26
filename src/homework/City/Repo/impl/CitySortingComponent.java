package homework.City.Repo.impl;

import homework.City.domain.City;
import homework.City.search.CityOrderByField;
import homework.City.search.CitySearchCondition;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static homework.Common.Business.Search.SortDirection.ASC;
import static homework.Common.Business.Search.SortDirection.DESC;

public class CitySortingComponent {


    public void sorting(List<City> cities, CitySearchCondition citySearchCondition) {
        Comparator<City> cityComparator = null;

        CityOrderByField field = citySearchCondition.getCityOrderByField();

        switch (citySearchCondition.getSortType()) {

            case SIMPLE: {
                cityComparator = CityComparatorComponent.getInstance().getComparatorForField(field);
                break;
            }
            case COMPLEX: {
                cityComparator = CityComparatorComponent.getInstance().getComplexComparator(field);
                break;
            }
        }

        if (cityComparator != null) {
            switch (citySearchCondition.getSortDirection()) {

                case ASC:
                    Collections.sort(cities, cityComparator);
                    break;
                case DESC:
                    Collections.sort(cities, cityComparator.reversed());
                    break;
            }
        }
    }
}

