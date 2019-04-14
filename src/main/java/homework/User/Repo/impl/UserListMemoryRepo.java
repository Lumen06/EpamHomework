package homework.User.Repo.impl;

import homework.Common.Solutions.Utils.Utils.StringUtils;
import homework.Storage.SequenceGenerator;
import homework.User.domain.User;
import homework.User.search.UserSearchCondition;
import homework.User.Repo.UserRepo.UserRepo;

import java.util.*;

import static homework.Storage.Storage.countriesList;
import static homework.Storage.Storage.users;
import static homework.Storage.Storage.usersList;

public class UserListMemoryRepo implements UserRepo {

    private UserSortingComponent userSortingComponent = new UserSortingComponent();

    @Override
    public void insert(Collection<User> items) {

    }

    @Override
    public void add(User user) {

        user.setId(SequenceGenerator.nextValue());
        usersList.add(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return findUserById(id);

    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<User> search(UserSearchCondition userSearchCondition) {


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


    @Override
    public void deleteById(Long id) {
        Optional<User> user = findUserById(id);
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

    public Optional<User> findUserById(long id) {
        return usersList.stream().filter(user -> Long.valueOf(id).equals(user.getId())).findAny();

    }

    @Override
    public List<User> findAll() {
        return usersList;
    }
}
