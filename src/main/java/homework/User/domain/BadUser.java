package homework.User.domain;

import homework.Passport.domain.Passport;

public class BadUser extends User {

    private int agression;

    public BadUser(String name, String lastName, Passport passport, Race race, int agression) {
        super(name, lastName, passport, race);
        this.agression = agression;
    }

    public int getAgression() {
        return agression;
    }

    public void setAgression(int agression) {
        this.agression = agression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BadUser badUser = (BadUser) o;

        return agression == badUser.agression;
    }

    @Override
    public int hashCode() {
        return agression;
    }

    @Override
    public String toString() {
        return "BadUser{" +
                "agression=" + agression +
                '}';
    }
}
