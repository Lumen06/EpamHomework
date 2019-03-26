package homework.User.service.impl;

import homework.Common.Business.Exceptions.TravelAgencyUncheckedException;
import homework.Order.Repo.OrderRepo.OrderRepo;
import homework.Order.service.OrderService.OrderService;
import homework.Passport.domain.Passport;
import homework.Passport.service.PassportService.PassportService;
import homework.User.Repo.UserRepo.UserRepo;
import homework.User.domain.User;
import homework.User.exception.DeleteUserException;
import homework.User.exception.UserExceptionMeta;
import homework.User.search.UserSearchCondition;
import homework.User.service.UserService.UserService;

import java.util.List;

public class UserDefaultService implements UserService {

    private final UserRepo userRepo;
    private final PassportService passportService;
    private final OrderService orderService;
    private final OrderRepo orderRepo;


    public UserDefaultService(UserRepo userRepo, PassportService passportService, OrderService orderService, OrderRepo orderRepo) {
        this.userRepo = userRepo;
        this.passportService = passportService;
        this.orderService = orderService;
        this.orderRepo = orderRepo;
    }

    @Override
    public void add(User user) {
        if (user != null) {
            userRepo.add(user);
        }
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public void delete(User user) {
        if (user != null) {
            this.deleteById(user.getId());

        }
    }

    @Override
    public List<User> search(UserSearchCondition userSearchCondition) {
        return userRepo.search(userSearchCondition);
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            boolean noOrders = orderRepo.countByUser(id) == 0;

            if (noOrders) {
                removePassportFromUser(id);
                userRepo.deleteById(id);
            } else {
                throw new DeleteUserException(UserExceptionMeta.DELETE_USER_CONSTRAINT_ERROR);
            }
        }
    }

    @Override
    public void printAll() {
        userRepo.printAll();
    }

    @Override
    public void update(User user) {
        if (user != null) {
            userRepo.update(user);
        }
    }

    @Override
    public void removePassportFromUser(Long userId) throws TravelAgencyUncheckedException {
        User user = userRepo.findById(userId);

        if (user != null) {
            passportService.deleteById(user.getPassport().getId());
        }
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }
}

