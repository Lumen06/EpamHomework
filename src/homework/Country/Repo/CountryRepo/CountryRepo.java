package homework.Country.Repo.CountryRepo;

import homework.Common.Solutions.Utils.BaseRepo.BaseRepo;
import homework.Country.domain.BaseCountry.Country;
import homework.Country.search.CountrySearchCondition;

import java.util.List;

public interface CountryRepo extends BaseRepo<Country, Long> {

    List<Country> search(CountrySearchCondition searchCondition);

}
