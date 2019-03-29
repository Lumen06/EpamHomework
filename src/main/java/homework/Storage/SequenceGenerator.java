package homework.Storage;

public final class SequenceGenerator {

    private static long sequenceValue = 0;

    public static long nextValue() {
        return ++sequenceValue;
    }

}
