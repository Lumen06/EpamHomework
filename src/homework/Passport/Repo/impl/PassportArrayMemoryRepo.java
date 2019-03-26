package homework.Passport.Repo.impl;

import homework.Common.Solutions.Utils.Utils.ArrayUtils;
import homework.Common.Solutions.Utils.Utils.StringUtils;
import homework.Passport.Repo.PassportRepo.PassportRepo;
import homework.Passport.domain.Passport;
import homework.Passport.search.PassportSearchCondition;
import homework.Storage.SequenceGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static homework.Storage.Storage.passports;


public class PassportArrayMemoryRepo implements PassportRepo {

    private PassportSortingComponent passportSortingComponent = new PassportSortingComponent();
    private int passportIndex = -1;


    @Override
    public void add(Passport passport) {
        if (passportIndex == passports.length - 1) {
            Passport[] newPassports = new Passport[(int) (passportIndex * 1.5)];
            System.arraycopy(passports, 0, newPassports, 0, passports.length);
            passports = newPassports;
        }

        passportIndex++;
        passport.setId(SequenceGenerator.nextValue());
        passports[passportIndex] = passport;

    }

    @Override
    public Passport findById(Long id) {
        Integer index = findPassportByIndex(id);
        if (index != null) {
            return passports[index];
        }

        return null;
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


                    int resultIndex = 0;

                    Passport[] newArrOfPassports = new Passport[passports.length];

                    for (Passport passport : passports) {

                        boolean found = true;

                        if (searchBySerial) {
                            found = passportSearchCondition.getSerial() == (passport.getSerial());
                        }
                        if (found && searchByNumber) {
                            found = passportSearchCondition.getNumber() == (passport.getNumber());
                        }
                        if (found) {
                            newArrOfPassports[resultIndex] = passport;
                            resultIndex++;
                        }
                    }
                    if (resultIndex > 0) {
                        Passport[] allFoundedPassports = new Passport[resultIndex];
                        System.arraycopy(newArrOfPassports, 0, allFoundedPassports, 0, resultIndex);

                        List<Passport> resultListOfPassports = new ArrayList<>(Arrays.asList(allFoundedPassports));

                        if (resultListOfPassports.size() > 0 && passportSearchCondition.needSorting()) {
                            passportSortingComponent.sorting(resultListOfPassports, passportSearchCondition);
                        }
                        return resultListOfPassports;
                    } else {
                        return Collections.emptyList();
                    }
                }
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        Integer index = findPassportByIndex(id);
        deletePassportByIndex(index);
    }

    @Override
    public void printAll() {
        for (Passport passport : passports) {
            System.out.println(passport);

        }
    }


    public Integer findPassportByIndex(Long index) {
        for (int i = 0; i < passports.length; i++) {
            if (passports[i].getId().equals(index)) {
                return i;
            }
        }
        return null;
    }

    public void deletePassportByIndex(int index) {
        ArrayUtils.removeElement(passports, index);
        passportIndex--;
    }

    @Override
    public List<Passport> findAll() {
        return new ArrayList<>(Arrays.asList(passports));

    }
}
