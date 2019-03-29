package homework.User.exception;

public enum UserExceptionMeta {
    DELETE_USER_CONSTRAINT_ERROR(3, "Error while delete country!");

    int code;
    String message;

    UserExceptionMeta(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
