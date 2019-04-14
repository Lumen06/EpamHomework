package homework.User.dto;

import homework.Passport.domain.Passport;
import homework.User.domain.Race;

public class UserDto {

    private String name;
    private String lastName;
    private Passport passport;
    private Race race;

    public UserDto() {}

    public Race getRace() {
        return race;
    }

    public String getName() {
        return name;
    }

    public String getlastName() {
        return lastName;
    }

    public Passport getPassport() {
        return passport;
    }

    @Override
    public String toString() {
        return "Имя: " + name + " Фамилия: " + lastName + " Данные паспорта: " + passport;
    }
}
