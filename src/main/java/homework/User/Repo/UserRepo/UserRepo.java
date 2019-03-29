package homework.User.Repo.UserRepo;

import homework.Common.Solutions.Utils.BaseRepo.BaseRepo;
import homework.User.domain.User;
import homework.User.search.UserSearchCondition;

import java.util.List;

public interface UserRepo extends BaseRepo<User, Long> {


    List<User> search(UserSearchCondition searchCondition);

}
