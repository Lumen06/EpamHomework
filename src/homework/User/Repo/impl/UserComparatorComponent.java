package homework.User.Repo.impl;

import homework.User.domain.User;
import homework.User.search.UserOrderingByField;

import java.util.*;

import static homework.User.search.UserOrderingByField.LAST_NAME;
import static homework.User.search.UserOrderingByField.NAME;
import static homework.Common.Solutions.Utils.BaseRepo.CommonComparatorHolder.getComparatorForNullableStrings;

public class UserComparatorComponent {
    private static final UserComparatorComponent INSTANCE = new UserComparatorComponent();
    private static Map<UserOrderingByField, Comparator<User>> comparatorsByFields = new HashMap<>();
    private static Set<UserOrderingByField> fieldComparePriorityUser = new LinkedHashSet<>(Arrays.asList(NAME, LAST_NAME));


    static {
        comparatorsByFields.put(NAME, getUserComparatorByName());
        comparatorsByFields.put(LAST_NAME, getUserComparatorByLastName());
    }

    private UserComparatorComponent() {
    }


    public static Comparator<User> getUserComparatorByName() {
        return new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return getComparatorForNullableStrings().compare(o1.getName(), o2.getName());
            }
        };
    }

    public static Comparator<User> getUserComparatorByLastName() {
        return new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return getComparatorForNullableStrings().compare(o1.getlastName(), o2.getlastName());
            }
        };
    }


    public static Comparator<User> getComparatorForField(UserOrderingByField field) {
        return comparatorsByFields.get(field);
    }

    public static UserComparatorComponent getInstance() {
        return INSTANCE;
    }

    public static Comparator<User> getComplexComparator(UserOrderingByField field) {
        return new Comparator<User>() {
            Comparator<User> comparator = getComparatorForField(field);

            int result = 0;

            @Override

            public int compare(User o1, User o2) {
                if (comparator != null) {
                    result = comparator.compare(o1, o2);
                } else for (UserOrderingByField otherfields : fieldComparePriorityUser) {
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
