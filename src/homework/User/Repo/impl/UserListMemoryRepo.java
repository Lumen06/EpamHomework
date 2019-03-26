package homework.User.Repo.impl;

import homework.Common.Solutions.Utils.Utils.StringUtils;
import homework.Storage.SequenceGenerator;
import homework.User.domain.User;
import homework.User.search.UserSearchCondition;
import homework.User.Repo.UserRepo.UserRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static homework.Storage.Storage.users;
import static homework.Storage.Storage.usersList;

public class UserListMemoryRepo implements UserRepo {

    private UserSortingComponent userSortingComponent = new UserSortingComponent();

    @Override
    public void add(User user) {

        user.setId(SequenceGenerator.nextValue());
        usersList.add(user);
    }

    @Override
    public User findById(Long id) {
        return findUserById(id);

    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<User> search(UserSearchCondition userSearchCondition) {
        {
            if (userSearchCondition.getId() != null) {
                return Collections.singletonList(findById(userSearchCondition.getId()));

            } else {

                boolean searchByName = StringUtils.isNotBlank(userSearchCondition.getName());

                boolean searchByLastName = StringUtils.isNotBlank(userSearchCondition.getLastName());

                boolean searchByPassport = StringUtils.isNotBlank(userSearchCondition.getPassport().getSerial() + userSearchCondition.getPassport().getNumber() + "");

                boolean searchByRace = StringUtils.isNotBlank(userSearchCondition.getRace() + "");

                List<User> foundedUsers = new ArrayList<>();

                for (User user : users) {

                    boolean found = true;

                    if (searchByName) {
                        found = userSearchCondition.getName().equals(user.getName());
                    }
                    if (found && searchByLastName) {
                        found = userSearchCondition.getLastName().equals(user.getlastName());
                    }
                    if (found && searchByPassport) {
                        found = userSearchCondition.getPassport().equals(user.getPassport());
                    }
                    if (found && searchByRace) {
                        found = userSearchCondition.getRace().equals(user.getRace());
                    }
                    if (found) {
                        foundedUsers.add(user);
                    }
                    if (foundedUsers.size() > 0 && userSearchCondition.needSorting()) {
                        userSortingComponent.sorting(foundedUsers, userSearchCondition);
                    }
                }
                return foundedUsers;
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        User user = findUserById(id);
        if (user != null) {
            usersList.remove(user);
        }
    }

    @Override
    public void printAll() {
        for (User User : usersList) {
            System.out.println(User);

        }
    }

    public User findUserById(long id) {
        for (User user : usersList) {
            if (Long.valueOf(id).equals(user.getId())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return usersList;
    }
}
