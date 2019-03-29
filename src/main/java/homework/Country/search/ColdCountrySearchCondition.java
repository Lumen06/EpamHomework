package homework.Country.search;

public class ColdCountrySearchCondition {

    private String telephoneCode;
    private Boolean polarNight;

    public boolean searchByTelephoneCode() {
        return telephoneCode != null;
    }

    public boolean searchByPolarNight() {
        return polarNight != null;
    }

    public String getTelephoneCode() {
        return telephoneCode;
    }

    public void setTelephoneCode(String telephoneCode) {
        this.telephoneCode = telephoneCode;
    }

    public Boolean getPolarNight() {
        return polarNight;
    }

    public void setPolarNight(Boolean polarNight) {
        this.polarNight = polarNight;
    }
}
