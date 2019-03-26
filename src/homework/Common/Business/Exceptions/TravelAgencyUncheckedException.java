package homework.Common.Business.Exceptions;

public class TravelAgencyUncheckedException extends RuntimeException {

    int code;

    public TravelAgencyUncheckedException(String message, int code) {
        super(message);
        this.code = code;
    }
}
