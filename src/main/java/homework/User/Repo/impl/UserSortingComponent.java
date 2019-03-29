package homework.User.Repo.impl;

import homework.User.domain.User;
import homework.User.search.UserOrderingByField;
import homework.User.search.UserSearchCondition;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserSortingComponent {
    public void sorting(List<User> countries, UserSearchCondition UserSearchCondition) {
        Comparator<User> UserComparator = null;

        UserOrderingByField field = UserSearchCondition.getUserOrderingByField();

        switch (UserSearchCondition.getSortType()) {

            case SIMPLE: {
                UserComparator = UserComparatorComponent.getInstance().getComparatorForField(field);
                break;
            }
            case COMPLEX: {
                UserComparator = UserComparatorComponent.getInstance().getComplexComparator(field);
                break;
            }
        }

        if (UserComparator != null) {
            switch (UserSearchCondition.getSortDirection()) {

                case ASC:
                    Collections.sort(countries, UserComparator);
                    break;
                case DESC:
                    Collections.sort(countries, UserComparator.reversed());
                    break;
            }
        }
    }
}
