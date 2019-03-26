package homework.Country.Repo.impl;

import homework.Country.domain.BaseCountry.Country;
import homework.Country.search.CountryOrderByField;
import homework.Country.search.CountrySearchCondition;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class CountrySortingComponent {

    public void sorting(List<Country> countries, CountrySearchCondition CountrySearchCondition) {
        Comparator<Country> CountryComparator = null;

        CountryOrderByField field = CountrySearchCondition.getCountryOrderByField();

        switch (CountrySearchCondition.getSortType()) {

            case SIMPLE: {
                CountryComparator = CountryComparatorComponent.getInstance().getComparatorForField(field);
                break;
            }
            case COMPLEX: {
                CountryComparator = CountryComparatorComponent.getInstance().getComplexComparator(field);
                break;
            }
        }

        if (CountryComparator != null) {
            switch (CountrySearchCondition.getSortDirection()) {

                case ASC:
                    Collections.sort(countries, CountryComparator);
                    break;
                case DESC:
                    Collections.sort(countries, CountryComparator.reversed());
                    break;
            }
        }
    }
}
