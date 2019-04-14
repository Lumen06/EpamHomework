package homework.Passport.dto;

import homework.Passport.domain.Passport;

public class PassportDto {

    private int serial;
    private int number;

    public PassportDto() {}

    public int getSerial() {
        return serial;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Cерия: " + serial + " Номер: " + number;
    }
}
