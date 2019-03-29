package homework.Country.service.CounryService;

import homework.Common.Business.Exceptions.TravelAgencyUncheckedException;
import homework.Common.Solutions.Utils.BaseService.BaseService;
import homework.Country.domain.BaseCountry.Country;
import homework.Country.search.CountrySearchCondition;

import java.util.List;

public interface CountryService extends BaseService<Country, Long> {

    List<? extends Country> search(CountrySearchCondition countrySearchCondition);

    void removeAllCitiesFromCountry(Long countyId) throws TravelAgencyUncheckedException;


}
