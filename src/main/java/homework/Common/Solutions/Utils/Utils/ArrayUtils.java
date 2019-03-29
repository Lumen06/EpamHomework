package homework.Common.Solutions.Utils.Utils;

public final class ArrayUtils {

    private ArrayUtils() {
    }

    public static void removeElement(Object[] array, int index) {
        System.arraycopy(array, index + 1, array, index, array.length - 1 - index);
    }

}
