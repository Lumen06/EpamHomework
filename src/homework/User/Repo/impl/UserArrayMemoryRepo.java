package homework.User.Repo.impl;

import homework.Common.Solutions.Utils.Utils.ArrayUtils;
import homework.Common.Solutions.Utils.Utils.StringUtils;
import homework.Passport.Repo.impl.PassportArrayMemoryRepo;
import homework.Storage.SequenceGenerator;
import homework.User.Repo.UserRepo.UserRepo;
import homework.User.domain.User;
import homework.User.search.UserSearchCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static homework.Storage.Storage.users;

public class UserArrayMemoryRepo implements UserRepo {

    private UserSortingComponent userSortingComponent = new UserSortingComponent();
    private int userIndex = -1;
    private PassportArrayMemoryRepo passportMemoryRepo = new PassportArrayMemoryRepo();


    @Override
    public void add(User user) {
        if (userIndex == users.length - 1) {
            User[] newUsers = new User[(int) (userIndex * 1.5)];
            System.arraycopy(users, 0, newUsers, 0, users.length);
            users = newUsers;
        }
        userIndex++;
        user.setId(SequenceGenerator.nextValue());
        users[userIndex] = user;


    }

    @Override
    public User findById(Long id) {
        Integer index = findUserByIndex(id);
        if (index != null) {
            return users[index];
        }

        return null;
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

                int resultIndex = 0;

                User[] newArrOfUsers = new User[users.length];

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
                        newArrOfUsers[resultIndex] = user;
                        resultIndex++;
                    }
                }
                if (resultIndex > 0) {
                    User[] allFoundedUsers = new User[resultIndex];
                    System.arraycopy(newArrOfUsers, 0, allFoundedUsers, 0, resultIndex);

                    List<User> resultListOfUsers = new ArrayList<>(Arrays.asList(allFoundedUsers));

                    if (resultListOfUsers.size() > 0 && userSearchCondition.needSorting()) {
                        userSortingComponent.sorting(resultListOfUsers, userSearchCondition);
                    }
                    return resultListOfUsers;
                } else {
                    return Collections.emptyList();
                }
            }
        }
    }


    @Override
    public void update(User user) {

    }


    @Override
    public void deleteById(Long id) {
        Integer index = findUserByIndex(id);
        deleteUserByIndex(index);
    }

    @Override
    public void printAll() {
        for (User user : users) {
            System.out.println(user);
        }
    }


    public Integer findUserByIndex(long index) {
        for (int i = 0; i < users.length; i++) {
            if (users[i].getId().equals(index)) {
                return i;
            }
        }
        return null;

    }


    public void deleteUserByIndex(int index) {
        ArrayUtils.removeElement(users, index);
        userIndex--;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(Arrays.asList(users));
    }
}
