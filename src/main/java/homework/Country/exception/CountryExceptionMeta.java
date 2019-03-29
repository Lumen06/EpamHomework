package homework.Country.exception;

public enum CountryExceptionMeta {
    DELETE_COUNTRY_CONSTRAINT_ERROR(1, "Error while delete country!");

    private int code;
    private String description;

    CountryExceptionMeta(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
