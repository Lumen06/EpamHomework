package homework.Country.Repo.impl;

import homework.Country.domain.BaseCountry.Country;
import homework.Country.search.CountryOrderByField;

import java.util.*;

import static homework.Common.Solutions.Utils.BaseRepo.CommonComparatorHolder.getComparatorForNullableLongs;
import static homework.Common.Solutions.Utils.BaseRepo.CommonComparatorHolder.getComparatorForNullableStrings;
import static homework.Country.search.CountryOrderByField.COUNTRY_NAME;
import static homework.Country.search.CountryOrderByField.NUMBER_OF_COUNTRIES;

public class CountryComparatorComponent {

    private static final CountryComparatorComponent INSTANCE = new CountryComparatorComponent();
    private static Map<CountryOrderByField, Comparator<Country>> comparatorsByFields = new HashMap<>();
    private static Set<CountryOrderByField> fieldComparePriorityOrder = new LinkedHashSet<>(Arrays.asList(COUNTRY_NAME, NUMBER_OF_COUNTRIES));


    static {
        comparatorsByFields.put(COUNTRY_NAME, getCountryComparatorByName());
        comparatorsByFields.put(NUMBER_OF_COUNTRIES, getCountryComparatorByNumberOfCities());
    }

    private CountryComparatorComponent() {
    }


    public static Comparator<Country> getCountryComparatorByName() {
        return new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return getComparatorForNullableStrings().compare(o1.getCountryName(), o2.getCountryName());
            }
        };
    }

    public static Comparator<Country> getCountryComparatorByNumberOfCities() {
        return new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return getComparatorForNullableLongs().compare((long) o1.getCities().length, (long) o2.getCities().length);
            }
        };
    }

    public static Comparator<Country> getComparatorForField(CountryOrderByField field) {
        return comparatorsByFields.get(field);
    }

    public static CountryComparatorComponent getInstance() {
        return INSTANCE;
    }

    public static Comparator<Country> getComplexComparator(CountryOrderByField field) {
        return new Comparator<Country>() {
            Comparator<Country> comparator = getComparatorForField(field);

            int result = 0;

            @Override

            public int compare(Country o1, Country o2) {
                if (comparator != null) {
                    result = comparator.compare(o1, o2);
                } else for (CountryOrderByField otherfields : fieldComparePriorityOrder) {
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
