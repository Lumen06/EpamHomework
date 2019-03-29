package homework.Common.Business.Exceptions;

public class TravelAgencyCheckedException extends Exception {

    int code;

    public TravelAgencyCheckedException(String message, int code) {
        super(message);
        this.code = code;
    }
}
