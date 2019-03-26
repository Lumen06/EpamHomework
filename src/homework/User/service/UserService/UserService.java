package homework.User.service.UserService;

import homework.Common.Business.Exceptions.TravelAgencyUncheckedException;
import homework.Common.Solutions.Utils.BaseService.BaseService;
import homework.User.domain.User;
import homework.User.search.UserSearchCondition;

import java.util.List;

public interface UserService extends BaseService<User, Long> {

    List<User> search(UserSearchCondition userSearchCondition);

    void removePassportFromUser(Long userId) throws TravelAgencyUncheckedException;


}
