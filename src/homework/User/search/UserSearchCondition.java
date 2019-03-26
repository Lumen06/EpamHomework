package homework.User.search;

import homework.Common.Business.Search.BaseSearchCondition;
import homework.Passport.domain.Passport;
import homework.User.domain.Race;

public class UserSearchCondition extends BaseSearchCondition<Long> {

    private String name;
    private String lastName;
    private Passport passport;
    private Race race;
    private UserOrderingByField userOrderingByField;

    public UserOrderingByField getUserOrderingByField() {
        return userOrderingByField;
    }

    public void setUserOrderingByField(UserOrderingByField userOrderingByField) {
        this.userOrderingByField = userOrderingByField;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public boolean needSearchByUser() {
        return true;
    }
}
