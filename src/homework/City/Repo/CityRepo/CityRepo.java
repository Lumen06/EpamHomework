package homework.City.Repo.CityRepo;

import homework.City.domain.City;
import homework.City.search.CitySearchCondition;
import homework.Common.Solutions.Utils.BaseRepo.BaseRepo;

import java.util.List;

public interface CityRepo extends BaseRepo<City, Long> {

    List<City> search(CitySearchCondition searchCondition);


}
