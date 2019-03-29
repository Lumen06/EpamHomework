package homework.User.domain;

import homework.Common.Business.domain.BaseDomain;
import homework.Passport.domain.Passport;

public class User extends BaseDomain {

    private String name;
    private String lastName;
    private Passport passport;
    private Race race;

    public User(String name, String lastName, Passport passport) {
        this.name = name;
        this.lastName = lastName;
        this.passport = passport;
    }


    public User(String name, String lastName, Passport passport, Race race) {
        this.name = name;
        this.lastName = lastName;
        this.passport = passport;
        this.race = race;
    }


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
