package homework.Common.Solutions.Utils.BaseRepo;

import java.util.Comparator;

public final class CommonComparatorHolder {

    private static Comparator<String> comparatorForNullableStrings = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            if (o1 == null && o2 == null) {
                return 0;
            } else if (o1 != null) {
                return o1.compareTo(o2);
            }
            return -1;
        }

    };

    private static Comparator<Integer> comparatorForNullableIntegers = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 == null && o2 == null) {
                return 0;
            } else if (o1 != null) {
                return o1 - o2;
            }
            return -1;
        }
    };

    private static Comparator<Long> comparatorForNullableLongs = new Comparator<Long>() {
        @Override
        public int compare(Long o1, Long o2) {
            if (o1 == null && o2 == null) {
                return 0;
            } else if (o1 != null) {
                return o1.intValue() - o2.intValue();
            }
            return -1;
        }
    };

    private CommonComparatorHolder() {
    }

    public static Comparator<String> getComparatorForNullableStrings() {
        return comparatorForNullableStrings;
    }


    public static Comparator<Long> getComparatorForNullableLongs() {
        return comparatorForNullableLongs;
    }

    public static Comparator<Integer> getComparatorForNullableIntegers() {
        return comparatorForNullableIntegers;
    }
}
