package homework.Passport.Repo.impl;

import homework.Common.Solutions.Utils.Utils.ArrayUtils;
import homework.Common.Solutions.Utils.Utils.OptionalUtils;
import homework.Common.Solutions.Utils.Utils.StringUtils;
import homework.Passport.Repo.PassportRepo.PassportRepo;
import homework.Passport.domain.Passport;
import homework.Passport.search.PassportSearchCondition;
import homework.Storage.SequenceGenerator;

import java.util.*;
import java.util.stream.IntStream;

import static homework.Storage.Storage.countries;
import static homework.Storage.Storage.passports;


public class PassportArrayMemoryRepo implements PassportRepo {

    private PassportSortingComponent passportSortingComponent = new PassportSortingComponent();
    private int passportIndex = -1;

    @Override
    public void insert(Collection<Passport> items) {

    }

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
    public Optional<Passport> findById(Long id) {
        return findPassportByIndex(id).map(x -> passports[x]);

    }

    @Override
    public void update(Passport passport) {

    }

    @Override
    public List<Passport> search(PassportSearchCondition passportSearchCondition) {


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



    @Override
    public void deleteById(Long id) {
        findPassportByIndex(id).ifPresent(this::deletePassportByIndex);

    }

    @Override
    public void printAll() {
        for (Passport passport : passports) {
            System.out.println(passport);

        }
    }


    public Optional<Integer> findPassportByIndex(Long index) {
        OptionalInt optionalInt = IntStream.range(0, countries.length).filter(i ->
                countries[i] != null && Long.valueOf(index).equals(countries[i].getId())
        ).findAny();

        return OptionalUtils.valueOf(optionalInt);
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
