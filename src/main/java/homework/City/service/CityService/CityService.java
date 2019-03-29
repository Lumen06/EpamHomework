package homework.City.service.CityService;

import homework.City.domain.City;
import homework.City.search.CitySearchCondition;
import homework.Common.Solutions.Utils.BaseService.BaseService;

import java.util.List;

public interface CityService extends BaseService<City, Long> {

    List<City> search(CitySearchCondition citySearchCondition);


}
