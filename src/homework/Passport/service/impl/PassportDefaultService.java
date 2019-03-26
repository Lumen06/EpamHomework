package homework.Passport.service.impl;

import homework.Passport.Repo.PassportRepo.PassportRepo;
import homework.Passport.domain.Passport;
import homework.Passport.Repo.impl.PassportArrayMemoryRepo;
import homework.Passport.search.PassportSearchCondition;
import homework.Passport.service.PassportService.PassportService;

import java.util.List;

public class PassportDefaultService implements PassportService {

    private final PassportRepo passportRepo;

    public PassportDefaultService(PassportRepo passportRepo) {
        this.passportRepo = passportRepo;
    }

    @Override
    public void add(Passport passport) {
        if (passport != null) {
            passportRepo.add(passport);
        }
    }

    @Override
    public Passport findById(Long id) {
        if (id != null) {
            return passportRepo.findById(id);
        }
        return null;
    }

    @Override
    public void delete(Passport passport) {
        passportRepo.deleteById(passport.getId());
    }

    @Override
    public List<Passport> search(PassportSearchCondition passportSearchCondition) {
        return passportRepo.search(passportSearchCondition);
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            passportRepo.deleteById(id);
        }
    }

    @Override
    public void printAll() {
        passportRepo.printAll();
    }

    @Override
    public void update(Passport passport) {
        if (passport != null) {
            passportRepo.update(passport);
        }

    }

    @Override
    public List<Passport> findAll() {
        return passportRepo.findAll();
    }
}
