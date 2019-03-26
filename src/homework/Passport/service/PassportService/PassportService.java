package homework.Passport.service.PassportService;

import homework.Common.Solutions.Utils.BaseService.BaseService;
import homework.Passport.domain.Passport;
import homework.Passport.search.PassportSearchCondition;

import java.util.List;

public interface PassportService extends BaseService<Passport, Long> {

    List<Passport> search(PassportSearchCondition passportSearchCondition);


}
