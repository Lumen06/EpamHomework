package homework.Passport.Repo.impl;

import homework.Passport.domain.Passport;
import homework.Passport.search.PassportOrderingByField;
import homework.Passport.search.PassportSearchCondition;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PassportSortingComponent {

    public void sorting(List<Passport> countries, PassportSearchCondition PassportSearchCondition) {
        Comparator<Passport> PassportComparator = null;

        PassportOrderingByField field = PassportSearchCondition.getPassportOrderingByField();

        switch (PassportSearchCondition.getSortType()) {

            case SIMPLE: {
                PassportComparator = PassportComparatorComponent.getInstance().getComparatorForField(field);
                break;
            }
            case COMPLEX: {
                PassportComparator = PassportComparatorComponent.getInstance().getComplexComparator(field);
                break;
            }
        }

        if (PassportComparator != null) {
            switch (PassportSearchCondition.getSortDirection()) {

                case ASC:
                    Collections.sort(countries, PassportComparator);
                    break;
                case DESC:
                    Collections.sort(countries, PassportComparator.reversed());
                    break;
            }
        }
    }

}
