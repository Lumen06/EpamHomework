package homework.User.domain;

import homework.Passport.domain.Passport;

public class VipUser extends User {

    public VipUser(String name, String lastName, Passport passport) {
        super(name, lastName, passport);
    }
}
