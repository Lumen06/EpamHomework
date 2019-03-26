package homework.Passport.Repo.impl;

import homework.Common.Solutions.Utils.Utils.StringUtils;
import homework.Passport.Repo.PassportRepo.PassportRepo;
import homework.Passport.domain.Passport;
import homework.Passport.search.PassportSearchCondition;
import homework.Storage.SequenceGenerator;

import static homework.Storage.Storage.passports;
import static homework.Storage.Storage.passportsList;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PassportListMemoryRepo implements PassportRepo {

    private PassportSortingComponent passportSortingComponent = new PassportSortingComponent();

    @Override
    public void add(Passport passport) {

        passport.setId(SequenceGenerator.nextValue());
        passportsList.add(passport);
    }

    @Override
    public Passport findById(Long id) {
        return findPassportById(id);

    }

    @Override
    public void update(Passport passport) {

    }

    @Override
    public List<Passport> search(PassportSearchCondition passportSearchCondition) {
        {
            {
                if (passportSearchCondition.getId() != null) {
                    return Collections.singletonList(findById(passportSearchCondition.getId()));

                } else {

                    boolean searchBySerial = StringUtils.isNotBlank(passportSearchCondition.getSerial() + "");

                    boolean searchByNumber = StringUtils.isNotBlank(passportSearchCondition.getNumber() + "");

                    List<Passport> foundedPassports = new ArrayList<>();

                    for (Passport passport : passports) {

                        boolean found = true;

                        if (searchBySerial) {
                            found = passportSearchCondition.getSerial() == (passport.getSerial());
                        }
                        if (found && searchByNumber) {
                            found = passportSearchCondition.getNumber() == (passport.getNumber());
                        }
                        if (found) {
                            foundedPassports.add(passport);
                        }
                    }

                    if (foundedPassports.size() > 0 && passportSearchCondition.needSorting()) {
                        passportSortingComponent.sorting(foundedPassports, passportSearchCondition);
                    }

                    return foundedPassports;
                }
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        Passport Passport = findPassportById(id);
        if (Passport != null) {
            passportsList.remove(Passport);
        }
    }

    @Override
    public void printAll() {
        for (Passport Passport : passportsList) {
            System.out.println(Passport);

        }
    }

    public Passport findPassportById(long id) {
        for (Passport Passport : passportsList) {
            if (Long.valueOf(id).equals(Passport.getId())) {
                return Passport;
            }
        }
        return null;

    }

    @Override
    public List<Passport> findAll() {
        return passportsList;
    }
}
