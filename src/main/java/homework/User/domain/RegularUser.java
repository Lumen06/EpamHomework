package homework.User.domain;

import homework.Passport.domain.Passport;

public class RegularUser extends User {

    private String job;
    private int earnings;

    public RegularUser(String name, String lastName, Passport passport, Race race, String job, int earnings) {
        super(name, lastName, passport, race);
        this.job = job;
        this.earnings = earnings;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getEarnings() {
        return earnings;
    }

    public void setEarnings(int earnings) {
        this.earnings = earnings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegularUser that = (RegularUser) o;

        if (earnings != that.earnings) return false;
        return job != null ? job.equals(that.job) : that.job == null;
    }

    @Override
    public int hashCode() {
        int result = job != null ? job.hashCode() : 0;
        result = 31 * result + earnings;
        return result;
    }

    @Override
    public String toString() {
        return "RegularUser{" +
                "job='" + job + '\'' +
                ", earnings=" + earnings +
                '}';
    }
}
