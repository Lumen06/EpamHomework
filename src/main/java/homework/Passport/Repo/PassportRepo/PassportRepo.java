package homework.Passport.Repo.PassportRepo;

import homework.Common.Solutions.Utils.BaseRepo.BaseRepo;
import homework.Passport.domain.Passport;
import homework.Passport.search.PassportSearchCondition;

import java.util.List;

public interface PassportRepo extends BaseRepo<Passport, Long> {

    List<Passport> search(PassportSearchCondition searchCondition);

}
