package homework.Passport.search;

import homework.Common.Business.Search.BaseSearchCondition;

public class PassportSearchCondition extends BaseSearchCondition<Long> {

    private int serial;
    private int number;
    private PassportOrderingByField passportOrderingByField;

    public PassportOrderingByField getPassportOrderingByField() {
        return passportOrderingByField;
    }

    public void setPassportOrderingByField(PassportOrderingByField passportOrderingByField) {
        this.passportOrderingByField = passportOrderingByField;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean needSearchByPassport() {
        return true;
    }
}
