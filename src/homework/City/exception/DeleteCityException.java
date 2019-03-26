package homework.City.exception;

import homework.Common.Business.Exceptions.TravelAgencyUncheckedException;

public class DeleteCityException extends TravelAgencyUncheckedException {

    public DeleteCityException(CityExceptionMeta exceptionMeta) {
        super(exceptionMeta.getDescription(), exceptionMeta.getCode());
    }


}
