package homework.User.exception;

import homework.Common.Business.Exceptions.TravelAgencyUncheckedException;

public class DeleteUserException extends TravelAgencyUncheckedException {

    public DeleteUserException(String message, int code) {
        super(message, code);
    }

    public DeleteUserException(UserExceptionMeta exceptionMeta) {
        super(exceptionMeta.getMessage(), exceptionMeta.getCode());
    }
}
