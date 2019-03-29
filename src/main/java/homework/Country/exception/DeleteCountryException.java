package homework.Country.exception;

import homework.Common.Business.Exceptions.TravelAgencyUncheckedException;

public class DeleteCountryException extends TravelAgencyUncheckedException {

    public DeleteCountryException(CountryExceptionMeta exceptionMeta) {
        super(exceptionMeta.getDescription(), exceptionMeta.getCode());
    }
}
