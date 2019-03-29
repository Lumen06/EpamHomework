package homework.Passport.Repo.impl;

import homework.Passport.domain.Passport;
import homework.Passport.search.PassportOrderingByField;

import java.util.*;

import static homework.Common.Solutions.Utils.BaseRepo.CommonComparatorHolder.getComparatorForNullableIntegers;
import static homework.Passport.search.PassportOrderingByField.NUMBER;
import static homework.Passport.search.PassportOrderingByField.SERIAL;


public class PassportComparatorComponent {

    private static final PassportComparatorComponent INSTANCE = new PassportComparatorComponent();
    private static Map<PassportOrderingByField, Comparator<Passport>> comparatorsByFields = new HashMap<>();
    private static Set<PassportOrderingByField> fieldComparePriorityPassport = new LinkedHashSet<>(Arrays.asList(SERIAL, NUMBER));


    static {
        comparatorsByFields.put(SERIAL, getPassportComparatorByNumber());
        comparatorsByFields.put(NUMBER, getPassportComparatorBySerial());
    }

    private PassportComparatorComponent() {
    }


    public static Comparator<Passport> getPassportComparatorByNumber() {
        return new Comparator<Passport>() {
            @Override
            public int compare(Passport o1, Passport o2) {
                return getComparatorForNullableIntegers().compare(o1.getSerial(), o2.getSerial());
            }
        };
    }

    public static Comparator<Passport> getPassportComparatorBySerial() {
        return new Comparator<Passport>() {
            @Override
            public int compare(Passport o1, Passport o2) {
                return getComparatorForNullableIntegers().compare(o1.getNumber(), o2.getNumber());
            }
        };
    }

    public static Comparator<Passport> getComparatorForField(PassportOrderingByField field) {
        return comparatorsByFields.get(field);
    }

    public static PassportComparatorComponent getInstance() {
        return INSTANCE;
    }

    public static Comparator<Passport> getComplexComparator(PassportOrderingByField field) {
        return new Comparator<Passport>() {
            Comparator<Passport> comparator = getComparatorForField(field);

            int result = 0;

            @Override

            public int compare(Passport o1, Passport o2) {
                if (comparator != null) {
                    result = comparator.compare(o1, o2);
                } else for (PassportOrderingByField otherfields : fieldComparePriorityPassport) {
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
