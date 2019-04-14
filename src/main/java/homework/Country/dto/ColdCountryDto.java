package homework.Country.dto;

import homework.Common.Business.BaseDto;

public class ColdCountryDto extends BaseDto<Long>{

    private String telephoneCode;
    private boolean polarNight;


    public ColdCountryDto() {
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
