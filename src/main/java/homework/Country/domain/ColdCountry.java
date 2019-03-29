package homework.Country.domain;

import homework.Country.domain.BaseCountry.Country;

public final class ColdCountry extends Country {

    private String telephoneCode;
    private boolean polarNight;


    public ColdCountry() {
    }

    public String getTelephoneCode() {
        return telephoneCode;
    }

    public void setTelephoneCode(String telephoneCode) {
        this.telephoneCode = telephoneCode;
    }

    public boolean isPolarNight() {
        return polarNight;
    }

    public void setPolarNight(boolean polarNight) {
        this.polarNight = polarNight;
    }
}
