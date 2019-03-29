package homework.City.Repo.impl;


import homework.City.domain.City;
import homework.City.search.CityOrderByField;

import java.util.*;

import static homework.City.search.CityOrderByField.NAME;
import static homework.City.search.CityOrderByField.POPULATION;
import static homework.Common.Solutions.Utils.BaseRepo.CommonComparatorHolder.getComparatorForNullableLongs;
import static homework.Common.Solutions.Utils.BaseRepo.CommonComparatorHolder.getComparatorForNullableStrings;

public class CityComparatorComponent {


    private static final CityComparatorComponent INSTANCE = new CityComparatorComponent();
    private static Map<CityOrderByField, Comparator<City>> comparatorsByFields = new HashMap<>();
    private static Set<CityOrderByField> fieldComparePriorityOrder = new LinkedHashSet<>(Arrays.asList(NAME, POPULATION));


    static {
        comparatorsByFields.put(NAME, getCityComparatorByName());
        comparatorsByFields.put(POPULATION, getCityComparatorByPopulation());
    }

    private CityComparatorComponent() {
    }


    public static Comparator<City> getCityComparatorByName() {
        return new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return getComparatorForNullableStrings().compare(o1.getName(), o2.getName());
            }
        };
    }

    public static Comparator<City> getCityComparatorByPopulation() {
        return new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return getComparatorForNullableLongs().compare(o1.getPopulation(), o2.getPopulation());
            }
        };
    }

    public static Comparator<City> getComparatorForField(CityOrderByField field) {
        return comparatorsByFields.get(field);
    }

    public static CityComparatorComponent getInstance() {
        return INSTANCE;
    }

    public static Comparator<City> getComplexComparator(CityOrderByField field) {
        return new Comparator<City>() {
            Comparator<City> comparator = getComparatorForField(field);

            int result = 0;

            @Override

            public int compare(City o1, City o2) {
                if (comparator != null) {
                    result = comparator.compare(o1, o2);
                } else for (CityOrderByField otherfields : fieldComparePriorityOrder) {
                    if (!otherfields.equals(field)) {
                        result = getComparatorForField(otherfields).compare(o1, o2);
                    }
                    if (result != 0) {
                        break;
                    }
                }
                return result;
            }
        };
    }
}
